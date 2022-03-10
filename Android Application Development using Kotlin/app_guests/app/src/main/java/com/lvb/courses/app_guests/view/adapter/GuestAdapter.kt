package com.lvb.courses.app_guests.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lvb.courses.app_guests.R
import com.lvb.courses.app_guests.service.model.GuestModel
import com.lvb.courses.app_guests.view.listener.GuestListener
import com.lvb.courses.app_guests.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList : List<GuestModel> = arrayListOf()
    private lateinit var mListener: GuestListener

    // Create the element (Widget/View Only UI without data)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest, parent, false)
        return GuestViewHolder(item, mListener)
    }

    // Put together the data and UI
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mGuestList[position])
    }

    // get the size that the recycler view needs for the list
    override fun getItemCount(): Int {
        return mGuestList.size
    }

    fun updateGuests(list : List<GuestModel>) {
        mGuestList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: GuestListener) {
        mListener = listener
    }
}