package com.example.todo_jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_jetpack.R
import com.example.todo_jetpack.listener.ItemClickListener
import com.example.todo_jetpack.models.Todo
import kotlinx.android.synthetic.main.item_layout_todo.view.*

class TodoAdapter(var todos: List<Todo>, var listener: ItemClickListener) :
    RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_todo,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.text.text = todo.text
        holder.status.isChecked = todo.done

        holder.status.setOnCheckedChangeListener { buttonView, isChecked ->
            todo.done = isChecked
            listener.onClick(position, todo)
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.tvText;
        val status = view.cbDoneStatus;
    }

    fun updateDate(updates: List<Todo>) {
        todos = updates
        notifyDataSetChanged()
    }

}