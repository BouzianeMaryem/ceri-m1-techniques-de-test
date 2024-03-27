package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;
    private final Map<String, PokemonTrainer> trainers = new HashMap<>();

    public PokemonTrainerFactory(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        validateParameters(name, team, pokedexFactory);
        if (trainers.containsKey(name)) {
            throw new IllegalStateException("Trainer existe deja !!");
        }
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        PokemonTrainer trainer = new PokemonTrainer(name, team, pokedex);
        trainers.put(name, trainer);
        return trainer;
    }

    private void validateParameters(String name, Team team, IPokedexFactory pokedexFactory) {
        if (name == null || team == null || pokedexFactory == null) {
            throw new IllegalArgumentException("Les paramètres de création du trainer ne doivent pas être nuls.");
        }
    }
}
