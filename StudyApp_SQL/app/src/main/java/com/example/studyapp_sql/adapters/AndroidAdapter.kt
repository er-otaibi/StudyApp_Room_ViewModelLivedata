package com.example.studyapp_sql.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapp.AndroidMaterials
import com.example.studyapp_sql.*
import com.example.studyapp_sql.fragments.AndroidFragment
import com.example.studyapp_sql.model.Lesson
import kotlinx.android.synthetic.main.item_row.view.*

class AndroidAdapter(private val android: AndroidFragment, private var listAndroid: List<AndroidMaterials>):  RecyclerView.Adapter<AndroidAdapter.ItemViewHolder>(){


    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var lesson = listAndroid[position]

        holder.itemView.apply {
            tvtitle.text = lesson.title
            tvdescription.text = lesson.description
            cvCard.setOnClickListener { CustomAlertDialog(android, lesson.title, lesson.details) }
            editBtn.setOnClickListener {
                var id = lesson.id
                var title = lesson.title
                var description = lesson.description
                var details = lesson.details
                android.editAlert(AndroidMaterials(id,title,description,details))

            }
            deleteBtn.setOnClickListener {
                var id = lesson.id
                var title = lesson.title
                var description = lesson.description
                var details = lesson.details
                android.deleteAlert(AndroidMaterials(id,title,description,details))
            }
        }
    }

    override fun getItemCount() = listAndroid.size


    fun update(lesson: ArrayList<AndroidMaterials>){
        this.listAndroid = lesson
        notifyDataSetChanged()
    }
}