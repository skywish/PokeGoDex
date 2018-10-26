package com.example.skywish.pokegodex.models;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.util.SortedList;

import com.example.skywish.pokegodex.R;

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

    private final int number; //index number in resources, pokedex number - 1
    private final String pokemonId;
    private final String type;
    private final String type2;
    private final int baseAttack;
    private final int baseDefense;
    private final int baseStamina;
    private int maxCP;
    //    private final int maxHP;
    private final String evolutionId;
    private final int candyToEvolve;
    private final String parentPokemonId;
    private final int kmBuddyDistance;
    private final String rarity;



    public Pokemon(int number, String pokemonId, String type, String type2, int baseAttack,
                   int baseDefense, int baseStamina, String evolutionId, int candyEvolutionCost,
                   String parentPokemonId, int kmBuddyDistance, String rarity) {
        this.number = number;
        this.pokemonId = pokemonId;
        this.type = type;
        this.type2 = type2;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseStamina = baseStamina;
        this.evolutionId = evolutionId;
        this.candyToEvolve = candyEvolutionCost;
        this.parentPokemonId = parentPokemonId;
        this.kmBuddyDistance = kmBuddyDistance;
        this.maxCP = calculateMaxCP(baseAttack, baseDefense, baseStamina);
        this.rarity = rarity;
    }

    private int calculateMaxCP(int baseAttack, int baseDefense, int baseStamina) {
        return (int) ((baseAttack + 15) * Math.sqrt(baseDefense + 15) * Math.sqrt(baseStamina + 15) * (Math.pow(0.7903001, 2))) / 10;
    }



    private void calculateMaxHP(int baseStamina) {
        // HP = (Base Stam + Stam IV) * Lvl(CPScalar)
        //  Stamina = Floor(HP * 1.75 + 50)
    }

    public int getNumber() {
        return number;
    }

    public String getPokemonId() {
        return pokemonId;
    }

    public String getType() {
        return type;
    }

    public String getType2() {
        return type2;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseStamina() {
        return baseStamina;
    }

    public void setMaxCP(int maxCP) {
        this.maxCP = maxCP;
    }

    public int getMaxCP() {
        return maxCP;
    }

    public String getEvolutionId() {
        return evolutionId;
    }

    public int getCandyToEvolve() {
        return candyToEvolve;
    }

    public String getParentPokemonId() {
        return parentPokemonId;
    }

    public int getKmBuddyDistance() {
        return kmBuddyDistance;
    }
}
