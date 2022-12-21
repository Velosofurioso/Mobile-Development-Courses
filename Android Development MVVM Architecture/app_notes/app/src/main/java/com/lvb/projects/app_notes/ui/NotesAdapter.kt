package com.lvb.projects.app_notes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lvb.projects.app_notes.R
import com.lvb.projects.app_notes.data.Note

class NotesViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bindView(item: Note) {
        val tvNote = view.findViewById<TextView>(R.id.tv_notes)
        with(view) {
            tvNote.text = item.text
        }
    }
}

class NotesAdapter(private val data: MutableList<Note> = mutableListOf()): RecyclerView.Adapter<NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) = holder.bindView(data[position])

    override fun getItemCount(): Int = data.size

    fun add(item: Note) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun add(item: List<Note>) {
        data.clear()
        data.addAll(item)
        notifyDataSetChanged()
    }

    fun remove(item: Note) {
        data.remove(item)
        notifyDataSetChanged()
    }

}