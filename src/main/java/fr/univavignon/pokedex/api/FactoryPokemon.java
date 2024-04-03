package fr.univavignon.pokedex.api;


public class FactoryPokemon implements IPokemonFactory {


    @Override
    public Pokemon createPokemon(final int index, final int cp, final int hp,
                                 final int dust,
                                 final int candy) {
        int iv = 1;
        PokemonMetadata pokemonMetadata;
        try {
            pokemonMetadata = new PokemonMetadataProvider().getPokemonMetadata(index);
        } catch (PokedexException pe) {
            System.err.println("Error PokemonMetadataProvider !!!");
            return null;
        }
        Pokemon pokemon =  new Pokemon(index, pokemonMetadata.getName(),
                pokemonMetadata.getAttack(), pokemonMetadata.getDefense(),
                pokemonMetadata.getStamina(), cp, hp, dust, candy, iv);

        return pokemon ;
    }
}