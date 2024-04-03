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
        int indxLastElement ;

        if (pokemon == null) {
            return -1;
        }

        pokemonList.add(pokemon);
        indxLastElement = pokemonList.size() - 1;

        return indxLastElement;
    }

    @Override
    public Pokemon getPokemon(int id) throws PokedexException {

        int lowExcepted = 0;
        int highExcepted = pokemonList.size();

        if (id >= lowExcepted && id < highExcepted) {
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

        Collections.sort(listOrdonnee, comparator);

        return listOrdonnee;
    }



}
