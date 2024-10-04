package com.example.recycle_view.Recycle_view

import android.app.Dialog
import android.content.Context
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycle_view.R
import com.example.recycle_view.databinding.ActivityRecyclerBinding



class Recycler : AppCompatActivity(), Recycler_adapter.onClick {
    lateinit var binding: ActivityRecyclerBinding
    lateinit var recyclerAdapter: Recycler_adapter

    var studentlist = arrayListOf<Entity>()
    lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        database=MyDatabase.getInstance(this)
        binding.but.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.data_entry1)


            val btnAdd = dialog.findViewById<Button>(R.id.btnadd2)
            val editText1 = dialog.findViewById<EditText>(R.id.edtadd1)
            val editText2 = dialog.findViewById<EditText>(R.id.edtadd2)




            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            dialog.show()


            btnAdd.setOnClickListener {
                if (editText1?.text.isNullOrEmpty()) {
                    editText1?.error = "Value Required"
                } else if (editText2?.text.isNullOrEmpty()) {
                    editText2?.error = "Value Required"
                } else {

                    val adddedStudent = Entity(





                        title = editText1.text.toString(),
                        description = editText2.text.toString(),

                    )

                     database.myInterface().indertTodo(adddedStudent)

                   getList()
                    recyclerAdapter.notifyDataSetChanged()
                    Toast.makeText(this, "new item added in list", Toast.LENGTH_SHORT).show()


                        dialog.dismiss()
                    }
                }
            }






        database.myInterface().indertTodo(Entity(title = "my title", description = "My description"))


        recyclerAdapter= Recycler_adapter(studentlist,this)
        binding.recycler.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recycler.adapter=recyclerAdapter
        getList()
    }


    override fun delete(position: Int) {
        AlertDialog.Builder(this).apply {
            setTitle("Do you want to delete")
            setPositiveButton("Yes") { _, _, ->
                database.myInterface().deleteTodo(studentlist[position])
                getList()
                recyclerAdapter.notifyDataSetChanged()

            }
            setNegativeButton("No") { _, _, ->
              //  Toast.makeText(this, "new item added in list", Toast.LENGTH_SHORT).show()

            }
            setCancelable(false)
            show()
        }
    }
//
//        database.myInterface().deleteTodo(studentlist[position])
//        getList()
//        recyclerAdapter.notifyDataSetChanged()
//        Toast.makeText(this, "delete :${studentlist[position]}", Toast.LENGTH_SHORT).show()




    override fun update(position: Int) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.data_entry_layout)

        val btn = dialog.findViewById<Button>(R.id.btn2)
        val edtText1 = dialog.findViewById<EditText>(R.id.edtf1)
        val edtText2 = dialog.findViewById<EditText>(R.id.edtf2)
     //   val edtText3 = dialog.findViewById<EditText>(R.id.edtf3)


        val currentStudent = studentlist[position]
        edtText1.setText(currentStudent.title.toString())
        edtText2.setText(currentStudent.description.toString())


        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.show()


        btn?.setOnClickListener {
            if (edtText1?.text.isNullOrEmpty()) {
                edtText1?.error = "Value required"
            } else if (edtText2?.text.isNullOrEmpty()) {
                edtText2?.error = "Value required"
            }
//            else if (edtText3?.text.isNullOrEmpty()) {
//                edtText3?.error = "Value required"
//            }
            else {
                val updatedStudent = Entity(
                    id = currentStudent.id,
                    title = edtText1.text.toString(),
                    description = edtText2.text.toString(),
                    //subject = edtText3.text.toString()
                )


                database.myInterface().updateTodo(updatedStudent)


                getList()
                recyclerAdapter.notifyDataSetChanged()

                dialog.dismiss()
                Toast.makeText(this, "Updated: ${edtText1.text.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun add(position: Int) {
//      database.myInterface().indertTodo(Entity(title = "my new title", description = "My new description"))
//
//
//        recyclerAdapter.notifyDataSetChanged()
    }

    private fun getList(){
        studentlist.clear()
        studentlist.addAll(database.myInterface().getlist())
    }
    }












