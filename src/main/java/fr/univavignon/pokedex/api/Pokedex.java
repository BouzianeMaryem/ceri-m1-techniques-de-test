package fr.univavignon.pokedex.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Pokedex implements IPokedex {

    private List<Pokemon> pokemonList;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
        this.pokemonList = new ArrayList<>();
    }

    @Override
    public int size() {
        return pokemonList.size();
    }

    @Override
    public int addPokemon(Pokemon pokemon) {
        if (pokemon != null) {
            pokemonList.add(pokemon);
            int indxLastElement = pokemonList.size() - 1;
            return indxLastElement;
        } else {
            return -1;
        }
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {
        if (id >= 0 && id < pokemonList.size()) {
            return pokemonList.get(id);
        } else {
            throw new PokedexException("invalid id !!!");
        }
    }

    @Override
    public List<Pokemon> getPokemons() {
        return pokemonList;
    }
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata( int indx) throws PokedexException {
        return metadataProvider.getPokemonMetadata(indx);
    }
    @Override
    public List<Pokemon> getPokemons(Comparator<Pokemon> comparator) {
        List<Pokemon> listOrdonnee = new ArrayList<>(pokemonList);
        listOrdonnee.sort(comparator);
        return listOrdonnee;
    }



}
