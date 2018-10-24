package com.example.skywish.pokegodex.Model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Pokemon {
    public enum Type {
        NORMAL,
        FIRE,
        WATER,
        GRASS,
        ELECTRIC,
        ICE,
        FIGHTING,
        POISON,
        GROUND,
        FLYING,
        PSYCHIC,
        BUG,
        ROCK,
        GHOST,
        DRAGON,
        DARK,
        STEEL,
        FAIRY,
    }

    private final String pokemonId;
    private final String type;
    private final String type2;
    private final int number; //index number in resources, pokedex number - 1
    private final int baseAttack;
    private final int baseDefense;
    private final int baseStamina;
    private final int maxCP;
//    private final int maxHP;
    private final int candyEvolutionCost;
    private final int kmBuddyDistance;

    public Pokemon(String pokemonId, String type, String type2, int number, int baseAttack, int baseDefense, int baseStamina, int candyEvolutionCost, int kmBuddyDistance) {
        this.pokemonId = pokemonId;
        this.type = type;
        this.type2 = type2;
        this.number = number;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseStamina = baseStamina;
        this.candyEvolutionCost = candyEvolutionCost;
        this.kmBuddyDistance = kmBuddyDistance;
        this.maxCP = calculateMaxCP(baseAttack, baseDefense, baseStamina);
    }

    private int calculateMaxCP(int baseAttack, int baseDefense, int baseStamina) {
        return (int) ((baseAttack + 15) * Math.sqrt(baseDefense + 15) * Math.sqrt(baseStamina + 15) * (Math.pow(0.7903001, 2))) / 10;
    }

    private void calculateMaxHP(int baseStamina) {
        //  Stamina = Floor(HP * 1.75 + 50)
    }
}
