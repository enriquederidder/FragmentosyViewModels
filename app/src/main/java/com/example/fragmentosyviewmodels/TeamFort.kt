package com.example.fragmentosyviewmodels

import androidx.lifecycle.ViewModel
import com.example.fragmentosyviewmodels.models.Caracter

class TeamFort: ViewModel() {

    private var caracters: MutableList<Caracter> = mutableListOf()
    private var selected:Caracter? = null

    init {
        this.caracters.add(
            Caracter(
                "Scout",
                "Light",
                "Light fast but low healt poitns, " +
                        "great for flanking",
                150
            )
        )
        this.caracters.add(
            Caracter(
                "Medic",
                "Support",
                "Support class that provides health to other players",
                150
            )
        )
    }
    val getCaractera:List<Caracter>
        get() = caracters.toList()
    var getAndSetselected:Caracter?
        get()=selected
        set(item){ selected=item}
    }