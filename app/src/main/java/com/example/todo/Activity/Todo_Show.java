package com.example.todo.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.todo.Model.Constant;
import com.example.todo.databinding.ActivityTodoShowBinding;

public class Todo_Show extends AppCompatActivity {
    ActivityTodoShowBinding binding;
    Context context=Todo_Show.this;
    String title,discription,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTodoShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent=getIntent();
        title=intent.getStringExtra(Constant.TITLE);
        discription=intent.getStringExtra(Constant.DISCRIPTION);
        date=intent.getStringExtra(Constant.DATE);

        binding.tvTitleGetId.setText(title);
        binding.tvDiscriptionGetId.setText(discription);
        binding.tvDateGetId.setText(date);


    }
}