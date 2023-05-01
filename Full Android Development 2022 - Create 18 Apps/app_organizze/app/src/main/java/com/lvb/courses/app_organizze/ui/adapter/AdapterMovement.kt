package com.lvb.courses.app_organizze.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lvb.courses.app_organizze.R
import com.lvb.courses.app_organizze.databinding.AdapterMovementsBinding
import com.lvb.courses.app_organizze.model.Movement

/**
 * Created by Jamilton Damasceno
 */
class AdapterMovement(movements: List<Movement>, context: Context) :
    RecyclerView.Adapter<AdapterMovement.MyViewHolder>() {
    private var movements: List<Movement>
    var context: Context

    init {
        this.movements = movements
        this.context = context
    }

    inner class MyViewHolder(val binding: AdapterMovementsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            AdapterMovementsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movement = movements[position]
        holder.binding.apply {
            textAdapterTitle.text = movement.description
            textAdapterValue.text = "${movement.value}"
            textAdapterCategory.text = movement.category
            textAdapterValue.setTextColor(context.resources.getColor(R.color.colorAccentRevenue))

            if (movement.type == "d") {
                textAdapterValue.setTextColor(context.resources.getColor(R.color.colorAccent))
                textAdapterValue.text = "-".plus(movement.value)
            }

        }
    }

    override fun getItemCount(): Int {
        return movements.size
    }

}