package com.example.td1.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.td1.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class VagueListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val adapter = VagueAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vague_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.vague_recyclerview)
        recyclerView.layoutManager = this@VagueListFragment.layoutManager
        recyclerView.adapter = this@VagueListFragment.adapter


    val vagueList : ArrayList<Vague> = arrayListOf<Vague>().apply {

        add(Vague("Nazaré"))
        add(Vague("Bellara"))
        add(Vague("Mavrik"))
        add(Vague("Jaws"))
        add(Vague("Pipeline"))
        add(Vague("Teahupoo"))

    }

    adapter.updateList(vagueList)
    }
}