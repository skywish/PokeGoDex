import numpy as np
import os
import json
import re
import math


def main():
    # <string name="search_hint">Search Pokemon name or number</string>
    # private final int number; //index number in resources, pokedex number - 1
    # private final String pokemonId;
    # private final int image;
    # private final String type;
    # private final String type2;
    # private final int baseAttack;
    # private final int baseDefense;
    # private final int baseStamina;
    # private final int maxCP;
    # //    private final int maxHP;
    # private final String evolutionId;
    # private final int candyEvolutionCost;
    # private final String parentPokemonId;
    # private final int kmBuddyDistance;
    # private final String rarity;
    a = 'POKEMON_RARITY_LEGENDARY'
    # "rarity": "POKEMON_RARITY_LEGENDARY",
    m = a.split('_')[2].capitalize()
    print(m)
    new_pokemons = []
    with open("Pokemon.json") as f:
        data = json.load(f)
    for row in data['Pokemon']:
        pokemon = dict()
        m = re.search(r"\d+", row['templateId'])
        pokemon['number'] = int(m.group())
        sp = row['templateId'].split('_')
        if len(sp) == 3:
            name = sp[2].capitalize()
        elif sp[3].capitalize() == "Normal":
            continue
        else:
            name = sp[2].capitalize() + "(" + sp[3].capitalize() + ")"
        pokemon['pokemonId'] = name
        pokemon['type'] = row['pokemonSettings']['type'].split('_')[2].capitalize()
        if 'type2' not in row['pokemonSettings']:
            pokemon['type2'] = ""
        else:
            pokemon['type2'] = row['pokemonSettings']['type2'].split('_')[2].capitalize()
        pokemon['baseAttack'] = row['pokemonSettings']['stats']['baseAttack']
        pokemon['baseDefense'] = row['pokemonSettings']['stats']['baseDefense']
        pokemon['baseStamina'] = row['pokemonSettings']['stats']['baseStamina']
        if 'evolutionIds' not in row['pokemonSettings']:
            pokemon['evolutionId'] = ""
        else:
            pokemon['evolutionId'] = row['pokemonSettings']['evolutionIds'][0].capitalize()
        if 'candyToEvolve' not in row['pokemonSettings']:
            pokemon['candyToEvolve'] = int(-1)
        else:
            pokemon['candyToEvolve'] = row['pokemonSettings']['candyToEvolve']
        if 'parentPokemonId' not in row['pokemonSettings']:
            pokemon['parentPokemonId'] = ""
        else:
            pokemon['parentPokemonId'] = row['pokemonSettings']['parentPokemonId'].capitalize()
        pokemon['kmBuddyDistance'] = row['pokemonSettings']['kmBuddyDistance']
        if 'rarity' not in row['pokemonSettings']:
            pokemon['rarity'] = "Normal"
        else:
            pokemon['rarity'] = row['pokemonSettings']['rarity'].split('_')[2].capitalize()
        pokemon['maxcp'] = cal_cp(pokemon['baseAttack'], pokemon['baseDefense'], pokemon['baseStamina'])
        new_pokemons.append(pokemon)
    with open("format_pokemon.json", 'w') as f:
        json.dump(new_pokemons, f)


def cal_cp(attack, defense, stamina):
    return int(((attack + 15) * math.sqrt(defense + 15) * math.sqrt(stamina + 15) * (math.pow(0.7903001, 2))) / 10)


if __name__ == '__main__':
    main()
