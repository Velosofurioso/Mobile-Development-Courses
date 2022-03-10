package com.lvb.courses.app_guests.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.lvb.courses.app_guests.databinding.FragmentPresentBinding
import com.lvb.courses.app_guests.service.constants.GuestConstants
import com.lvb.courses.app_guests.view.adapter.GuestAdapter
import com.lvb.courses.app_guests.view.listener.GuestListener
import com.lvb.courses.app_guests.viewmodel.GuestsViewModel

class PresentFragment : Fragment(), GuestListener {

    private lateinit var mGuestsViewModel : GuestsViewModel
    private val mAdapter : GuestAdapter = GuestAdapter()

    private var _binding: FragmentPresentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mGuestsViewModel = ViewModelProvider(this)[GuestsViewModel::class.java]

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // RecyclerView

        // 1 - Get the recycler on xml
        val recycler = binding.recyclerPresents

        // 2 - Define a layout
        recycler.layoutManager = LinearLayoutManager(context)

        // 3 - Define a adapter
        recycler.adapter = mAdapter

        mAdapter.attachListener(this)

        observer()


        return root
    }

    override fun onResume() {
        super.onResume()
        mGuestsViewModel.load(GuestConstants.FILTER.PRESENT)
    }

    private fun observer() {
        mGuestsViewModel.guestList.observe(viewLifecycleOwner) {
            mAdapter.updateGuests(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(id: Int) {
        val intent = Intent(context, GuestFormActivity::class.java)
        intent.putExtra(GuestConstants.GUEST_ID, id)

        startActivity(intent)
    }

    override fun onDelete(id: Int) {
        mGuestsViewModel.delete(id)
        mGuestsViewModel.load(GuestConstants.FILTER.PRESENT)
    }
}