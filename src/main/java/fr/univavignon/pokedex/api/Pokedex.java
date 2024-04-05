package fr.univavignon.pokedex.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Pokedex implements IPokedex {

    private List<Pokemon> pokemonList;
    private IPokemonMetadataProvider metadataProvider;
    private IPokemonFactory pokemonFactory;

    public Pokedex(final IPokemonMetadataProvider vmetadataProvider,
                   final IPokemonFactory vpokemonFactory) {
        this.metadataProvider = vmetadataProvider;
        this.pokemonFactory = vpokemonFactory;
        this.pokemonList = new ArrayList<>();
    }

    @Override
    public int size() {

        return pokemonList.size();

    }

    @Override
    public int addPokemon(final Pokemon pokemon) {
        int indxLastElement;
        if (pokemon == null) {
            return -1;
        }
        pokemonList.add(pokemon);
        indxLastElement = pokemonList.size() - 1;
        return indxLastElement;
    }

    @Override
    public Pokemon getPokemon(final int id) throws PokedexException {

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
    public Pokemon createPokemon(final int index,
                                 final int cp, final int hp,
                                 final  int dust,
                                 final int candy) {

        return pokemonFactory.createPokemon(index,
                cp, hp, dust, candy);
    }

    @Override
    public PokemonMetadata getPokemonMetadata(final int indx)
            throws PokedexException {

        return metadataProvider.getPokemonMetadata(indx);
    }
    @Override
    public List<Pokemon> getPokemons(final Comparator<Pokemon> comparator) {

        List<Pokemon> listOrdonnee = new ArrayList<>(pokemonList);

        Collections.sort(listOrdonnee, comparator);

        return listOrdonnee;
    }


}
