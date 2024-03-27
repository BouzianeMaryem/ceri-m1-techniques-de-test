package fr.univavignon.pokedex.api;

import java.util.HashMap;
import java.util.Map;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

    private final Map<String, PokemonTrainer> trainers = new HashMap<>();
    private final IPokemonMetadataProvider metadataProvider;
    private final IPokemonFactory pokemonFactory;

    public PokemonTrainerFactory(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        this.metadataProvider = metadataProvider;
        this.pokemonFactory = pokemonFactory;
    }

    @Override
    public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
        if (name == null || team == null || pokedexFactory == null) {
            throw new IllegalArgumentException("la creation d'un trainer avec des params null , doit lever une exception !!!");
        }
        if (trainers.containsKey(name)) {
            throw new IllegalStateException("Trainer existe deja !!");
        }
        IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        PokemonTrainer newTrainer = new PokemonTrainer(name, team, pokedex);
        trainers.put(name, newTrainer);
        return newTrainer;
    }
}
