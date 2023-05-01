package com.lvb.courses.app_organizze.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.lvb.courses.app_organizze.config.FirebaseConfiguration
import com.lvb.courses.app_organizze.databinding.FragmentFirstBinding
import com.lvb.courses.app_organizze.model.Movement
import com.lvb.courses.app_organizze.model.User
import com.lvb.courses.app_organizze.ui.adapter.AdapterMovement
import com.lvb.courses.app_organizze.util.Base64Util
import com.lvb.courses.app_organizze.util.Constants
import java.text.DecimalFormat

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val databaseRef = FirebaseConfiguration.getFirebaseDB()
    private val firebaseAuth = FirebaseConfiguration.getFirebaseAuth()
    private lateinit var databaseUserRef: DatabaseReference
    private lateinit var databaseMovementRef: DatabaseReference
    private lateinit var valueEventListenerUser: ValueEventListener
    private lateinit var valueEventListenerMovements: ValueEventListener

    private var totalExpense: Double = 0.00
    private var totalRevenue: Double = 0.00
    private var resumeUser: Double = 0.00
    private var movements: MutableList<Movement> = arrayListOf()
    private lateinit var movement: Movement
    private lateinit var yearMonthSelected: String

    private lateinit var adapterMovement: AdapterMovement

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        adapterMovement = AdapterMovement(movements, requireContext())
        configureCalendarView()
        swipe()
        configureRecycleView()

        return binding.root

    }

    override fun onStart() {
        super.onStart()
        recoveryResumeInfo()
        recoveryMovements()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun configureCalendarView() = with(binding) {

        calendarView.setTitleMonths(Constants.MONTHS)

        val actualDate = calendarView.currentDate
        var monthSelected = String.format("%02d", actualDate.month)
        yearMonthSelected = "$monthSelected${actualDate.year}"

        calendarView.setOnMonthChangedListener { widget, date ->
            monthSelected = String.format("%02d", date.month)
            yearMonthSelected = "$monthSelected${date.year}"

            databaseMovementRef.removeEventListener(valueEventListenerMovements)
            recoveryMovements()
        }

    }

    private fun recoveryResumeInfo() {
        val userId = Base64Util.encodeBase64(firebaseAuth.currentUser?.email)
        databaseUserRef = databaseRef.child(Constants.FIREBASE_DB_USERS_NAME).child(userId)

        valueEventListenerUser = databaseUserRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)


                val decimalFormat = DecimalFormat("#.##")

                totalExpense = user?.totalExpense ?: 0.00
                totalRevenue = user?.totalRevenue ?: 0.00
                resumeUser = totalRevenue - totalExpense

                binding.textSalutation.text = "Hello, ${user?.name}"
                binding.textBalance.text = "$ ${decimalFormat.format(resumeUser)}"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onStop() {
        super.onStop()
        databaseUserRef.removeEventListener(valueEventListenerUser)
        databaseMovementRef.removeEventListener(valueEventListenerMovements)
    }

    private fun recoveryMovements() {
        val userId = Base64Util.encodeBase64(firebaseAuth.currentUser?.email)
        databaseMovementRef = databaseRef.child(Constants.FIREBASE_DB_MOVEMENT_NAME)
            .child(userId)
            .child(yearMonthSelected)

        valueEventListenerMovements =
            databaseMovementRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    movements.clear()

                    snapshot.children.forEach { data ->
                        val movement = data.getValue(Movement::class.java)
                        movement?.key = data.key
                        movement?.let {
                            movements.add(it)
                        }
                    }
                    adapterMovement.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun configureRecycleView() = with(binding) {
        rvMoviments.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterMovement
        }

    }

    private fun swipe() {
        val callback = object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val dragFlags = ItemTouchHelper.ACTION_STATE_IDLE
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                excludeMovement(viewHolder)
            }
        }

        ItemTouchHelper(callback).attachToRecyclerView(binding.rvMoviments)
    }

    private fun excludeMovement(viewHolder: RecyclerView.ViewHolder) {
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle("Exclude account's movement?")
        alertDialog.setMessage("Are you sure you want to delete the movement?")
        alertDialog.setCancelable(false)

        alertDialog.setPositiveButton("Confirm") { dialog, which ->
            val position = viewHolder.adapterPosition
            movement = movements[position]

            val userId = Base64Util.encodeBase64(firebaseAuth.currentUser?.email)
            databaseMovementRef = databaseRef.child(Constants.FIREBASE_DB_MOVEMENT_NAME)
                .child(userId)
                .child(yearMonthSelected)

            movement.key?.let { databaseMovementRef.child(it).removeValue() }
            adapterMovement.notifyItemRemoved(position)
            updateBalance()
        }

        alertDialog.setNegativeButton("Cancel") { _, _ ->
            Toast.makeText(context, "Operation Canceled", Toast.LENGTH_SHORT).show()
            adapterMovement.notifyDataSetChanged()

        }

        alertDialog.show()
    }

    private fun updateBalance() {

        val userId = Base64Util.encodeBase64(firebaseAuth.currentUser?.email)
        databaseUserRef = databaseRef.child(Constants.FIREBASE_DB_USERS_NAME).child(userId)

        if (movement.type == "r") {
            totalRevenue -= movement.value
            databaseUserRef.child(Constants.FIREBASE_DB_MOVEMENT_TOTAL_REVENUE)
                .setValue(totalRevenue)
        } else if (movement.type == "d") {
            totalExpense -= movement.value
            databaseUserRef.child(Constants.FIREBASE_DB_MOVEMENT_TOTAL_EXPENSE)
                .setValue(totalExpense)
        }

    }

}