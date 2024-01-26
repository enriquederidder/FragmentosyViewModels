package com.example.fragmentosyviewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit

class FragmentDetail : Fragment() {
    private lateinit var v: View
    private val teamFort: TeamFort by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_detail, container, false)
        this.update()
        this.teamFort.selected?.let {
            v.findViewById<TextView>(R.id.name).text = it.name
            v.findViewById<TextView>(R.id.clas).text = it.clas.toString()
            v.findViewById<TextView>(R.id.description).text = it.description.toString()
            v.findViewById<TextView>(R.id.health).text = it.health.toString()
        }
        v.findViewById<Button>(R.id.edit).setOnClickListener {
            val fm: FragmentManager = parentFragmentManager

            fm.commit {
                replace(R.id.fragmentContainerView, FragmentEdit.newInstance())
                addToBackStack("replacement")
            }
            true
        }
        // Inflate the layout for this fragment
        return v //inflater.inflate(R.layout.fragment_detail, container, false)
    }

    fun update() {
        this.teamFort.selected?.let {
            v.findViewById<TextView>(R.id.name).text = it.name
            v.findViewById<TextView>(R.id.clas).text = it.clas
            v.findViewById<TextView>(R.id.description).text = it.description
            v.findViewById<TextView>(R.id.health).text = it.health.toString()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentDetail().apply {

            }
    }
}