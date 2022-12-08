package br.ufc.quixada.kotlinzada

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_list.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        rvTodo.adapter = todoAdapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val todoTitle = etAdd.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                etAdd.text.clear()
            }
        }

        imageView.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            todoAdapter.deleteTodos();

        }
    }
}