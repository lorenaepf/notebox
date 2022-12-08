package br.ufc.quixada.kotlinzada

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_list.view.*

class TodoAdapter(private val todos: MutableList<Todo> ) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo_list,
            parent,
            false))
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrike(titleExample: TextView, isChecked :  Boolean) {
        if(isChecked){
            titleExample.paintFlags = titleExample.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            titleExample.paintFlags = titleExample.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            titleExample.text = curTodo.title
            checkBox.isChecked = curTodo.isChecked
            toggleStrike(titleExample,curTodo.isChecked)
            checkBox.setOnCheckedChangeListener {  _, isChecked ->
                toggleStrike(titleExample, isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }

        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }



}