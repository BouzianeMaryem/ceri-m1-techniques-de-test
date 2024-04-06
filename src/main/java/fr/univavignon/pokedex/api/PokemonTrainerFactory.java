package fr.univavignon.pokedex.api;

/**
 * Classe factory pour créer des instances de {@link PokemonTrainer}.
 * Utilise les Pokémons factory les métadonnées fournisseurs
 * pour créer les dresseurs et leurs pokédex associés.
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    /**
     * Fournisseur de métadonnées de Pokémon.
     */
    private final IPokemonMetadataProvider metadataProvider;

    /**
     * Factory pour créer les Pokémons.
     */
    private final IPokemonFactory pokemonFactory;

    /**
     * Construit un nouveau PokemonTrainerFactory avec
     * les métadonnées fournisseurs et le Factory de Pokemon.
     *
     * @param metadataProvider Le fournisseur de métadonnées de Pokémon.
     * @param pokemonFactory La factory pour créer les Pokémons.
     */
    public PokemonTrainerFactory(
            final IPokemonMetadataProvider metadataProvider,
            final IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    /**
     * Crée un nouveau dresseur Pokémon avec un nom,
     * une équipe et une factory de pokédex spécifiés.
     *
     * @param name Le nom du dresseur.
     * @param team L'équipe du dresseur.
     * @param pokedexFactory La factory pour créer un pokédex.
     * @return un nouveau {@link PokemonTrainer}.
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
