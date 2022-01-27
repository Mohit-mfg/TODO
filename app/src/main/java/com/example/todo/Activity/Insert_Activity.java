package com.example.todo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todo.Adapter.My_Adapter;
import com.example.todo.databinding.ActivityInsertBinding;

public class Insert_Activity extends AppCompatActivity {

    ActivityInsertBinding binding;
    Context context=Insert_Activity.this;
    My_Adapter my_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        my_adapter = new My_Adapter(context);
        my_adapter.openDatabase();

        binding.btnInsertId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                my_adapter.insertRecord(context,binding.etTitleId.getEditableText().toString(),binding.etDiscriptionId.getEditableText().toString(),binding.etDateId.getEditableText().toString());
                startActivity(new Intent(context,MainActivity.class));
                finish();
            }
        });

    }
}