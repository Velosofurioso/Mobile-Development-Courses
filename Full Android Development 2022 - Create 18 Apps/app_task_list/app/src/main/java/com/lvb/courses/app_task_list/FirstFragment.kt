package com.lvb.courses.app_task_list

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvb.courses.app_task_list.databinding.FragmentFirstBinding
import com.lvb.courses.app_task_list.helper.DAO.tasksDAO.TasksDAO
import com.lvb.courses.app_task_list.model.Task
import com.lvb.courses.app_task_list.ui.adapter.TaskListAdapter
import com.lvb.courses.app_task_list.ui.addTask.AddTaskActivity
import com.lvb.courses.app_task_list.util.RecyclerItemClickListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val _taskListAdapter by lazy { TaskListAdapter() }

    private var tasks = mutableListOf<Task>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        //Add click event
        binding.recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                binding.recyclerView,
                recycleViewClickCallback()
            )
        )

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        setupRecycleView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun recycleViewClickCallback(): RecyclerItemClickListener.OnItemClickListener {
        return object : RecyclerItemClickListener.OnItemClickListener {

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {}

            override fun onItemClick(view: View?, position: Int) {
                val taskSelected = tasks[position]

                val intent = Intent(activity, AddTaskActivity::class.java)
                intent.putExtra("taskSelected", taskSelected)

                startActivity(intent)

            }

            override fun onLongItemClick(view: View?, position: Int) {
                val taskSelected = tasks[position]
                val alertDialog = AlertDialog.Builder(context)

                alertDialog.setTitle(getString(R.string.title_confirm_delete))
                alertDialog.setMessage(getString(R.string.confirm_delete, taskSelected.name))

                alertDialog.setPositiveButton(
                    getString(R.string.yes),
                    alertBuilderCallback(taskSelected)
                )
                alertDialog.setNegativeButton(getString(R.string.cancel), null)

                alertDialog.create()
                alertDialog.show()
            }

        }
    }


    private fun alertBuilderCallback(taskSelected: Task): DialogInterface.OnClickListener {

        return DialogInterface.OnClickListener { _, _ ->
            val taskDao = TasksDAO(requireContext())
            if (taskDao.delete(taskSelected)) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.delete_operation_success),
                    Toast.LENGTH_SHORT
                ).show()
                setupRecycleView()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.delete_operation_failed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun setupRecycleView() {

        //List tasks
        val taskDao = TasksDAO(requireContext())
        tasks = taskDao.list()

        // Configure adapter
        _taskListAdapter.taskNames = tasks

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = _taskListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
        }

    }

}