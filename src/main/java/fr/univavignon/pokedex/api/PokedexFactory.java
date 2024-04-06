package fr.univavignon.pokedex.api;

/**
 * Builds Pokedexes.
 * Uses IPokemonMetadataProvider and IPokemonFactory
 * for gathering Pokemon info and creating them. Gathers
 * all essentials for Pokedex functionality.
 */

public class PokedexFactory implements IPokedexFactory {

    /**
     * Builds a Pokedex using provided components.
     *
     * @param metadataProvider Supplies Pokemon metadata.
     * @param pokemonFactory Creates Pokemon.
     * @return A ready-to-use IPokedex.
     */

    @Override
    public final IPokedex createPokedex(
            final IPokemonMetadataProvider metadataProvider,
            final IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}
