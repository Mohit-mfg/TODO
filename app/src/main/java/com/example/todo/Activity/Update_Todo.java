package com.example.todo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.todo.Adapter.My_Adapter;
import com.example.todo.Model.Constant;
import com.example.todo.databinding.ActivityUpdateTodoBinding;

public class Update_Todo extends AppCompatActivity {
    ActivityUpdateTodoBinding binding;
    String title,discription,date,row_id;
    Context context=Update_Todo.this;
    My_Adapter my_adapter;
    Cursor cursor;

    int clickedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateTodoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        title=intent.getStringExtra(Constant.TITLE);
        discription=intent.getStringExtra(Constant.DISCRIPTION);
        date=intent.getStringExtra(Constant.DATE);
        row_id=intent.getStringExtra(Constant.ROW_ID);

        binding.etTitleId.setText(title);
        binding.etDiscriptionId.setText(discription);
        binding.etDateId.setText(date);

        binding.btnUpdateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cursor.moveToPosition(clickedPosition);

//                my_adapter.insertRecord(context,binding.etTitleId.getEditableText().toString(),binding.etDiscriptionId.getEditableText().toString(),binding.etDateId.getEditableText().toString());
//                startActivity(new Intent(context,MainActivity.class));
//                finish();
                my_adapter.updateRecord(context, row_id, binding.etTitleId.getEditableText().toString(),binding.etDiscriptionId.getEditableText().toString(),binding.etDateId.getEditableText().toString());
                startActivity(new Intent(context,MainActivity.class));
                finish();
            }
        });


    }
}