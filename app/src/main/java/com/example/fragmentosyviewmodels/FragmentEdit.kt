package com.example.fragmentosyviewmodels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fragmentosyviewmodels.models.Caracter

class FragmentEdit : Fragment() {
    private lateinit var v: View
    private val teamFort: TeamFort by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_edit, container, false)
        this.teamFort.selected?.let {
            v.findViewById<EditText>(R.id.editTextNombre).setText(it.name)
            v.findViewById<EditText>(R.id.editTextClas).setText(it.clas)
            v.findViewById<EditText>(R.id.editTextDescription).setText(it.description)
            v.findViewById<EditText>(R.id.editTextHealth).setText(it.health.toString())
        }

        v.findViewById<Button>(R.id.buttonSave).setOnClickListener {
            saveChanges()
        }
        v.findViewById<Button>(R.id.buttonNewClass).setOnClickListener {
            newCaracter()
        }

        return v //inflater.inflate(R.layout.fragment_edit, container, false)
    }

    private fun saveChanges() {
        // Actualiza el caracter eleigido con el conetenido editado

        teamFort.selected?.let { originalCharacter ->
            try {
                val editedCharacter = originalCharacter.copy(
                    name = v.findViewById<EditText>(R.id.editTextNombre).text.toString(),
                    clas = v.findViewById<EditText>(R.id.editTextClas).text.toString(),
                    description = v.findViewById<EditText>(R.id.editTextDescription).text.toString(),
                    health = v.findViewById<EditText>(R.id.editTextHealth).text.toString().toInt()
                )

                // Actuliza en el ViewModel
                val index = teamFort.caracters.indexOf(originalCharacter)
                if (index != -1) {
                    teamFort.caracters[index] = editedCharacter
                }
            } catch (e: NumberFormatException) {
                // TODO
            }
        }

        // Navigate back to the detail fragment
        parentFragmentManager.popBackStack()
        parentFragmentManager.popBackStack()

    }

    private fun newCaracter() {

        //v.findViewById<Button>(R.id.buttonSave).visibility = View.GONE
        val name = v.findViewById<EditText>(R.id.editTextNombre).text
        val clas = v.findViewById<EditText>(R.id.editTextClas).text
        val description = v.findViewById<EditText>(R.id.editTextDescription).text
        val health = v.findViewById<EditText>(R.id.editTextHealth)

        teamFort.caracters.add(
            Caracter(
                name.toString(),
                clas.toString(),
                description.toString(),
                health.text.toString().toInt()
            )
        )

        parentFragmentManager.popBackStack()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentEdit().apply {

            }
    }
}