package com.example.todo.Interface;

import com.example.todo.Model.TODO_Details;

import java.util.List;

public interface On_item_click_listener {
    void onItemClick(List<TODO_Details> list, int position);

}
