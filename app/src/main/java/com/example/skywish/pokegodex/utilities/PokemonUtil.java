package com.example.skywish.pokegodex.utilities;

public class PokemonUtil {
    public static int calculateMaxCP(int baseAttack, int baseDefense, int baseStamina) {
        return (int) ((baseAttack + 15) * Math.sqrt(baseDefense + 15) * Math.sqrt(baseStamina + 15) * (Math.pow(0.7903001, 2))) / 10;
    }
}
