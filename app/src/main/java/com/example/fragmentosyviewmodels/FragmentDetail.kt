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

/**
 * FragmentDetail es un fragmento responsable de mostrar informacion sobre el caracter seleccionado.
 * Incluye campos para el nombre, la clase, la descripcion y la health del caracter.
 * Los usuarios tambien pueden navegar a la pantalla de editar para modificar el caracter seleccionado.
 */
class FragmentDetail : Fragment() {
    private lateinit var v: View
    private val teamFort: TeamFort by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_detail, container, false)
        // Actualiza el UI con los detalles seleccionados
        this.update()
        this.teamFort.selected?.let {
            v.findViewById<TextView>(R.id.name).text = it.name
            v.findViewById<TextView>(R.id.clas).text = it.clas
            v.findViewById<TextView>(R.id.description).text = it.description
            v.findViewById<TextView>(R.id.health).text = it.health.toString()
        }
        v.findViewById<Button>(R.id.edit).setOnClickListener {
            val fm: FragmentManager = parentFragmentManager

            fm.commit {
                replace(R.id.fragmentContainerView, FragmentEdit.newInstance())
                addToBackStack("replacement")
            }
        }
        // Solo funcionara si el dispositivo esta en modo landscape
        if (resources.getBoolean(R.bool.land)) {
            v.findViewById<Button>(R.id.edit).setOnClickListener {
                val fm: FragmentManager = parentFragmentManager
                fm.commit {
                    replace(R.id.detailfragmentContainerView, FragmentEdit.newInstance())
                    addToBackStack("replacement")
                }
            }
        }

        return v
    }
    /**
     * Actualiza la interfaz de usuario con los detalles del caracter seleccionado
     */
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