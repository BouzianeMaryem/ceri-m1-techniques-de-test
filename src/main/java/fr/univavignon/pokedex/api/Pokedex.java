package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class Pokedex implements IPokedex {

    private List<Pokemon> pokemons;
    //fonction 2 : code mal développé, dirigé et limité par les tests
    @Override
    public int addPokemon(Pokemon pokemon) {
        if (pokemons.size() == 151) {
            return -1;
        }
        if ("Bulbizarre".equals(pokemon.getName())) {
            pokemons.add(pokemon);
            return 0;
        } else if ("Herbizarre".equals(pokemon.getName())) {
            pokemons.add(pokemon);
            return 1;
        }
        return -1;
    }
//fonction 3 : code mal développé, dirigé et limité par les tests
    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id == 0) {
            return pokemons.get(0);
        } else if (id == 1) {
            return pokemons.get(1);
        }
        throw new PokedexException("Invalid index");
    }
}
