package com.example.recycle_view.Recycle_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycle_view.R

class Recycler_adapter(var array: ArrayList<Entity>, var clickInterface: onClick) :
    RecyclerView.Adapter<Recycler_adapter.ViewHolder>() {
    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var name = view.findViewById<TextView>(R.id.tvStuName)
        var roll = view.findViewById<TextView>(R.id.tvRollno)
         val myclass = view.findViewById<TextView>(R.id.tvClass)
        var update = view.findViewById<Button>(R.id.btnUpdate)
        var delete = view.findViewById<Button>(R.id.btnDelete)
        var add = view.findViewById<Button>(R.id.btnAdd)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler_adapter.ViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.base_item_layout, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: Recycler_adapter.ViewHolder, position: Int) {
        holder.apply {
            name.setText(array[position].title)
            roll.setText(array[position].id.toString())
            myclass.setText(array[position].description.toString())
            update.setOnClickListener {

                clickInterface.update(position)
            }
            delete.setOnClickListener {
                clickInterface.delete(position)
            }
            add.setOnClickListener {
                clickInterface.add(position)
            }

        }
    }

    override fun getItemCount(): Int {
        return array.size
    }

    interface onClick {
        fun delete(position: Int)
        fun update(position: Int)
        fun add(position: Int)
    }

}








