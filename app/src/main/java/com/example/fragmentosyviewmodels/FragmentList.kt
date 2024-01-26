package com.example.fragmentosyviewmodels

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Fragmento que muestra la lista de carcteres en el RecyclerView,
 * Tambien maneja los clics del usuario, y al rotar el dispositivo.
 */
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
        // Maneja el click en un caracter y compruba si el dispositivo esta en modo landscape.
        adaptador.click = { position, caracter ->
            run{
                this.teamFort.getAndSetselected = caracter
                val fm: FragmentManager = parentFragmentManager

                // Si esta en landscape coje el detailfragmentContainerView y lo actualiza,
                // si no abre el nuevo FragmentDetail en una nueva ventana
                if (!resources.getBoolean(R.bool.land)) {
                    fm.commit {
                        replace(R.id.fragmentContainerView, FragmentDetail.newInstance())
                        addToBackStack("replacement")
                    }
                } else {
                    val contenedor = v.findViewById<FragmentContainerView>(R.id.detailfragmentContainerView)
                    val fragmentManager = childFragmentManager
                    var fragment = fragmentManager.findFragmentById(contenedor?.id ?: -1)
                    if(fragment!=null && fragment is FragmentDetail){
                        fragment.update()
                    }

                }
            }
        }
        // Listener para añadir nuevos caracteres
        v.findViewById<Button>(R.id.addNewClas).setOnClickListener {
            val fm: FragmentManager = parentFragmentManager
            fm.commit {
                replace(R.id.fragmentContainerView, FragmentEdit.newInstance())
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