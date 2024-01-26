package com.example.fragmentosyviewmodels

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.fragmentosyviewmodels.models.Caracter
/**
 * FragmentEdit es un fragmento responsable para la edición y creación de nuevos personajes.
 * Proporciona elementos de interfaz de usuario para modificar los detalles del personaje,
 * como el nombre, la clase, la descripción y la salud.
 */
class FragmentEdit : Fragment() {
    private lateinit var v: View
    private val teamFort: TeamFort by activityViewModels()

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Rellenar los elementos de la interfaz de usuario con los
        // detalles del personaje seleccionado (si están disponibles)
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
                // TODO si introcduce un valor invalido
            }
        }

        // Navigate back to the detail fragment
        parentFragmentManager.popBackStack()
        parentFragmentManager.popBackStack()

    }

    @SuppressLint("ResourceType")
    private fun newCaracter() {

        // Guarda los detalles introducidos por el usuario
        //v.findViewById<Button>(R.id.buttonSave).visibility = View.GONE
        val name = v.findViewById<EditText>(R.id.editTextNombre).text
        val clas = v.findViewById<EditText>(R.id.editTextClas).text
        val description = v.findViewById<EditText>(R.id.editTextDescription).text
        val health = v.findViewById<EditText>(R.id.editTextHealth)

        // Crea un nuevo caracter con los detalles guardados
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