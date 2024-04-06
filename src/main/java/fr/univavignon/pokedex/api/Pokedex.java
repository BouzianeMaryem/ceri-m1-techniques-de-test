package fr.univavignon.pokedex.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class Pokedex implements IPokedex {
    /**
     * Liste pour stocker les Pokémons.
     */
    private List<Pokemon> pokemonList;
    /**
     * Fournisseur de métadonnées pour les Pokémons.
     */
    private IPokemonMetadataProvider metadataProvider;
    /**
     * Factory pour créer les Pokémons.
     */

    private IPokemonFactory pokemonFactory;
    /**
     * Construit un nouveau Pokedex avec les
     * métadonnées fournisseurs et le Factory de Pokemon.
     *
     * @param metadataProvider Fournisseur de métadonnées pour les Pokémons.
     * @param pokemonFactory Factory pour créer des Pokémons.
     */
    public Pokedex(final IPokemonMetadataProvider metadataProvider,
                   final IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
        this.pokemonList = new ArrayList<>();
    }

    @Override
    public final int size() {

        return pokemonList.size();

    }

    @Override
    public final int addPokemon(final Pokemon pokemon) {

        int indxLastElement;
        if (pokemon == null) {
            return -1;
        }
        pokemonList.add(pokemon);
        indxLastElement = pokemonList.size() - 1;
        return indxLastElement;
    }

    @Override
    public final Pokemon getPokemon(final int id) throws PokedexException {

        int lowExcepted = 0;
        int highExcepted = pokemonList.size();

        if (id >= lowExcepted && id < highExcepted) {
            return pokemonList.get(id);
        } else {
            throw new PokedexException("invalid id !!!");
        }
    }

    @Override
    public final List<Pokemon> getPokemons() {

        return pokemonList;
    }
    @Override
    public final Pokemon createPokemon(final int index,
                                 final int cp, final int hp,
                                 final  int dust,
                                 final int candy) {

        return pokemonFactory.createPokemon(index,
                cp, hp, dust, candy);
    }

    @Override
    public final PokemonMetadata getPokemonMetadata(final int indx)
            throws PokedexException {

        return metadataProvider.getPokemonMetadata(indx);
    }
    @Override
    public final List<Pokemon> getPokemons(
            final Comparator<Pokemon> comparator) {

        List<Pokemon> listOrdonnee = new ArrayList<>(pokemonList);

        Collections.sort(listOrdonnee, comparator);

        return listOrdonnee;
    }


}
