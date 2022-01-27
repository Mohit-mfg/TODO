package com.example.todo.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.todo.Adapter.My_Adapter;
import com.example.todo.Adapter.RecycleView_Adapter;
import com.example.todo.Interface.On_item_click_listener;
import com.example.todo.Model.Constant;
import com.example.todo.Model.TODO_Details;
import com.example.todo.R;
import com.example.todo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemLongClickListener  {
    ActivityMainBinding binding;
    Context context=MainActivity.this;
    My_Adapter my_adapter;
    Cursor cursor;
    private int clickedPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        my_adapter = new My_Adapter(context);
        my_adapter.openDatabase();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        binding.recycleViewId.setLayoutManager(linearLayoutManager);

        registerForContextMenu(binding.recycleViewId);
//        binding.recycleViewId.setOnItemLongClickListener(context);




//        RecycleView_Adapter recycleView_adapter=new RecycleView_Adapter(context,getTodoList());
        RecycleView_Adapter recycleView_adapter=new RecycleView_Adapter(context, getTodoList(), new On_item_click_listener() {
            @Override
            public void onItemClick(List<TODO_Details> list, int position) {

                TODO_Details details=list.get(position);
                Intent intent=new Intent(context,Todo_Show.class);
                intent.putExtra(Constant.TITLE,details.getTitle());
                intent.putExtra(Constant.DISCRIPTION,details.getDiscription());
                intent.putExtra(Constant.DATE,details.getDate());
                startActivity(intent);

//                Toast.makeText(context, details.getTitle(), Toast.LENGTH_SHORT).show();


            }
        });

        binding.recycleViewId.setAdapter(recycleView_adapter);
        recycleView_adapter.notifyDataSetChanged();




    }


    private List<TODO_Details> getTodoList(){
        cursor = my_adapter.getAllRecords();
        List<TODO_Details> finalList = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String serNo = cursor.getString(0);
                String title = cursor.getString(1);
                String discription = cursor.getString(2);
                String date = cursor.getString(3);

                TODO_Details todo_details = new TODO_Details(title, discription, date);
                finalList.add(todo_details);
            } while (cursor.moveToNext());
        }
        return finalList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.todo_menu_option, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_insert_id:
                startActivity(new Intent(context,Insert_Activity.class));
                break;

            case R.id.menu_delete_all_id:
                    my_adapter.delete_All(context);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_option_menu,menu);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_menu_update_id:

                Toast.makeText(context, "case 1", Toast.LENGTH_SHORT).show();

                cursor.moveToPosition(clickedPosition);

                String rowId = cursor.getString(0);
                String title = cursor.getString(1);// title
                String discription = cursor.getString(2);// discription
                String date = cursor.getString(2);// date

                Intent intent=new Intent(context,Update_Todo.class);
                intent.putExtra(Constant.ROW_ID,rowId);
                intent.putExtra(Constant.ROW_ID,title);
                intent.putExtra(Constant.ROW_ID,discription);
                intent.putExtra(Constant.ROW_ID,date);

                startActivity(intent);

                break;

            case R.id.option_menu_delete_id:

                Toast.makeText(context, "case 2", Toast.LENGTH_SHORT).show();

                cursor.moveToPosition(clickedPosition);
                String colRow = cursor.getString(0);
                my_adapter.deleteRecord(colRow, context);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        finishAffinity();
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        clickedPosition=i;
        return false;
    }



}