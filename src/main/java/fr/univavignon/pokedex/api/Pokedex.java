package fr.univavignon.pokedex.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Pokedex: stores and provides Pokemon info.
 */
public class Pokedex implements IPokedex {
    /**
     * Stores Pokemons.
     */
    private List<Pokemon> pokemonList;
    /**
     * Provides Pokemon metadata.
     */
    private IPokemonMetadataProvider metadataProvider;
    /**
     * Creates Pokemons.
     */
    private IPokemonFactory pokemonFactory;
    /**
     * Constructs a Pokedex.
     *
     * @param metadataProvider Metadata provider.
     * @param pokemonFactory Pokemon creator.
     */
    public Pokedex(final IPokemonMetadataProvider metadataProvider,
                   final IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
        this.pokemonList = new ArrayList<>();
    }

    /**
     * Returns Pokedex size.
     *
     * @return Size of Pokedex.
     */
    @Override
    public final int size() {

        return pokemonList.size();

    }
    /**
     * Adds a Pokemon, returns its index.
     *
     * @param pokemon The Pokemon to add.
     * @return Index of the Pokemon, -1 if failed.
     */
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
    /**
     * Retrieves a Pokemon by ID.
     *
     * @param id The Pokemon's ID.
     * @return The Pokemon.
     * @throws PokedexException If ID is invalid.
     */
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
    /**
     * Gets all Pokemons.
     *
     * @return List of Pokemons.
     */
    @Override
    public final List<Pokemon> getPokemons() {

        return pokemonList;
    }
    /**
     * Creates a Pokemon.
     *
     * @param index Index.
     * @param cp CP.
     * @param hp HP.
     * @param dust Dust.
     * @param candy Candy.
     * @return New Pokemon.
     */
    @Override
    public final Pokemon createPokemon(final int index,
                                 final int cp, final int hp,
                                 final  int dust,
                                 final int candy) {

        return pokemonFactory.createPokemon(index,
                cp, hp, dust, candy);
    }
    /**
     * Gets Pokemon metadata by index.
     *
     * @param indx Pokemon index.
     * @return Metadata.
     * @throws PokedexException If no metadata.
     */
    @Override
    public final PokemonMetadata getPokemonMetadata(final int indx)
            throws PokedexException {

        return metadataProvider.getPokemonMetadata(indx);
    }
    /**
     * Sorts and returns Pokemons.
     *
     * @param comparator Sort criterion.
     * @return Sorted Pokemons.
     */
    @Override
    public final List<Pokemon> getPokemons(
            final Comparator<Pokemon> comparator) {

        List<Pokemon> listOrdonnee = new ArrayList<>(pokemonList);

        Collections.sort(listOrdonnee, comparator);

        return listOrdonnee;
    }


}
