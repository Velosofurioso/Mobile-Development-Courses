package com.lvb.courses.app_task_list.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lvb.courses.app_task_list.databinding.TaskListBinding
import com.lvb.courses.app_task_list.model.Task

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {

    inner class TaskListViewHolder(val binding: TaskListBinding) :
        RecyclerView.ViewHolder(binding.root)

    var taskNames: MutableList<Task> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder(
            TaskListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.binding.txtTaskName.apply {
            text = taskNames[position].name
        }
    }

    override fun getItemCount(): Int = taskNames.size

}