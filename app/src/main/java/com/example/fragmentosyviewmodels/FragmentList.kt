package com.example.fragmentosyviewmodels

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    class MainActivity : AppCompatActivity()

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView: RecyclerView = v.findViewById<RecyclerView>(R.id.recyclerview)
        var adaptador = TeamFortRecyclerViewAdapter(this.teamFort.getCaractera)
        adaptador.click = { position, caracter ->
            run {
                //se selecciona el dragón
                this.teamFort.getAndSetselected = caracter
                val fm: FragmentManager = parentFragmentManager
                fm.commit {
                    //replace(R.id.fragmentContainerView, DetailFragment.newInstance())
                    addToBackStack("replacement")
                }

            }
        }
        val layoutManager = LinearLayoutManager(this.context)
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