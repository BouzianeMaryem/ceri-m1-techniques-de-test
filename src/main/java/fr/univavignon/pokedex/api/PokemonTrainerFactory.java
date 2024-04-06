package fr.univavignon.pokedex.api;

/**
 * Factory class for creating {@link PokemonTrainer} instances.
 * Uses Pokemon factories and metadata providers
 * to create trainers and their associated Pokédexes.
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    /**
     * Pokemon metadata provider.
     */
    private final IPokemonMetadataProvider metadataProvider;

    /**
     * Factory for creating Pokemons.
     */
    private final IPokemonFactory pokemonFactory;

    /**
     * Constructs a new PokemonTrainerFactory with
     * the metadata provider and the Pokemon factory.
     *
     * @param metadataProvider The Pokemon metadata provider.
     * @param pokemonFactory The factory for creating Pokemons.
     */
    public PokemonTrainerFactory(
            final IPokemonMetadataProvider metadataProvider,
            final IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Creates a new Pokemon trainer with a specified name,
     * team, and Pokédex factory.
     *
     * @param name The trainer's name.
     * @param team The trainer's team.
     * @param pokedexFactory The factory for creating a Pokédex.
     * @return a new {@link PokemonTrainer}.
     */
    @Override
    public PokemonTrainer createTrainer(
            final String name,
            final Team team,
            final IPokedexFactory pokedexFactory) {
        return new PokemonTrainer(name, team,
                pokedexFactory.createPokedex(
                        metadataProvider,
                        pokemonFactory));
    }
}
