package com.example.fragmentosyviewmodels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels

class FragmentEdit : Fragment() {
    private lateinit var v: View
    private val teamFort: TeamFort by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_edit, container, false)
        this.teamFort.selected?.let {
            v.findViewById<TextView>(R.id.editTextNombre).setText(it.name)
            v.findViewById<TextView>(R.id.editTextClas).setText(it.clas.toString())
            v.findViewById<TextView>(R.id.editTextDescription).setText(it.description.toString())
            v.findViewById<TextView>(R.id.editTextHealth).setText(it.health.toString())
        }

        return v //inflater.inflate(R.layout.fragment_edit, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentEdit().apply {

            }
    }
}