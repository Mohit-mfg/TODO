package com.example.todo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.Interface.On_item_click_listener;
import com.example.todo.Model.TODO_Details;
import com.example.todo.R;
import com.example.todo.databinding.RecycleViewBinding;

import java.util.List;

public class RecycleView_Adapter extends RecyclerView.Adapter<RecycleView_Adapter.My_ViewHolder> {
    Context context;
    List<TODO_Details> todo_details;

    public RecycleView_Adapter(Context context, List<TODO_Details> todo_details, On_item_click_listener on_item_click_listener) {
        this.context = context;
        this.todo_details = todo_details;
        this.on_item_click_listener = on_item_click_listener;
    }

    On_item_click_listener on_item_click_listener;

    public RecycleView_Adapter(Context context, List<TODO_Details> todo_details) {
        this.context = context;
        this.todo_details = todo_details;
    }

    @NonNull
    @Override
    public My_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(context).inflate(R.layout.recycle_view,parent,false);
//        My_ViewHolder my_viewHolder=new My_ViewHolder(view);

        RecycleViewBinding binding = RecycleViewBinding.inflate(LayoutInflater.from(context), parent, false);
        My_ViewHolder my_viewHolder = new My_ViewHolder(binding);

        return my_viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull My_ViewHolder holder, int position) {

        holder.binding.tvTitleId.setText(todo_details.get(position).getTitle());
        holder.binding.tvDiscriptionId.setText(todo_details.get(position).getDiscription());
        holder.binding.tvDateId.setText(todo_details.get(position).getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                on_item_click_listener.onItemClick(todo_details,position);
            }
        });








    }

    @Override
    public int getItemCount() {
        return todo_details.size();
    }


    public class My_ViewHolder extends RecyclerView.ViewHolder{

//        TextView tv_title,tv_discription,tv_date;
        RecycleViewBinding binding;

//        public My_ViewHolder(@NonNull View itemView) {
//            super(itemView);
//                tv_title = itemView.findViewById(R.id.tv_title_id);
//                tv_discription = itemView.findViewById(R.id.tv_discription_id);
//                tv_date = itemView.findViewById(R.id.tv_date_id);
//
//        }
        public My_ViewHolder(@NonNull RecycleViewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
//            tv_title = itemView.findViewById(R.id.tv_title_id);
//            tv_discription = itemView.findViewById(R.id.tv_discription_id);
//            tv_date = itemView.findViewById(R.id.tv_date_id);

        }
    }
}
