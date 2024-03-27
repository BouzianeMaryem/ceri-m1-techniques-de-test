package fr.univavignon.pokedex.api;

import java.util.Random;

public class PokemonFactory implements IPokemonFactory {

    private IPokemonMetadataProvider metadataProvider;

    public PokemonFactory(IPokemonMetadataProvider metadataProvider) {
        this.metadataProvider = metadataProvider;
    }

    @Override
    public Pokemon createPokemon(final int index, final int cp, final int hp,
                                 final int dust, final int candy) {
        PokemonMetadata metadata;
        try {
            metadata = metadataProvider.getPokemonMetadata(index);
        } catch (PokedexException e) {
            System.err.println("Attention: je ne trouve pas de metadonnees pour l'index " + index+ "!!!");
            return null;
        }.


        int iv = 25;

        return new Pokemon(index, metadata.getName(),
                metadata.getAttack(), metadata.getDefense(),
                metadata.getStamina(), cp, hp, dust, candy, iv);
    }
}
