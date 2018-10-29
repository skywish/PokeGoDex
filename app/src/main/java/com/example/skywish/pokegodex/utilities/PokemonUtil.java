package com.example.skywish.pokegodex.utilities;

import com.example.skywish.pokegodex.models.Pokemon;

import java.util.List;
import java.util.Map;

public class PokemonUtil {

    public static Map<String, Pokemon> pokemonMap;

    public static int calculateMaxCP(int baseAttack, int baseDefense, int baseStamina) {
            return (int) ((baseAttack + 15) * Math.sqrt(baseDefense + 15) * Math.sqrt(baseStamina + 15) * (Math.pow(0.7903001, 2))) / 10;
    }
}
