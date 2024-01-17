package com.example.fragmentosyviewmodels

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.ListFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentList : Fragment() {

    private lateinit var v: View
    private val teamFort: TeamFort by activityViewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout first
        v = inflater.inflate(R.layout.fragment_list, container, false)

        val recyclerView: RecyclerView = v.findViewById(R.id.recyclerview)
        val adaptador = TeamFortRecyclerViewAdapter(this.teamFort.getCaractera)
        adaptador.click = { position, caracter ->
            this.teamFort.getAndSetselected = caracter
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, FragmentDetail.newInstance())
                addToBackStack("replacement")
            }
        }

        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adaptador
        return v
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentList().apply {

            }
    }
}