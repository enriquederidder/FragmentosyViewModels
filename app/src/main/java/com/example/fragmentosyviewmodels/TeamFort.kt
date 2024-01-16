package com.example.fragmentosyviewmodels

import androidx.lifecycle.ViewModel
import com.example.fragmentosyviewmodels.models.Caracter

class TeamFort : ViewModel() {

    private var caracters: MutableList<Caracter> = mutableListOf()
    var selected: Caracter? = null

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
                200
            )
        )
        this.caracters.add(
            Caracter(
                "Sniper",
                "Support",
                "Kills other players, from long range",
                200
            )
        )
        this.caracters.add(
            Caracter(
                "Heavy",
                "Offense",
                "Has alot of healt, but is slow",
                350
            )
        )
    }

    val getCaractera: List<Caracter>
        get() = caracters.toList()
    var getAndSetselected: Caracter?
        get() = selected
        set(item) {
            selected = item
        }
}
