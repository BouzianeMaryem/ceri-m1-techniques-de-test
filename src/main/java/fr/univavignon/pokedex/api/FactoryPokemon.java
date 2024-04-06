package fr.univavignon.pokedex.api;

/**
 * Factory to create Pokemon objects.
 * Implements IPokemonFactory to produce Pokemon.
 */
public final class FactoryPokemon implements IPokemonFactory {

    /**
     * Creates a new Pokemon with specified parameters.
     * @param index Pokemon's index in the Pokedex.
     * @param cp Pokemon's combat points.
     * @param hp Pokemon's health points.
     * @param dust Stardust required for upgrading.
     * @param candy Candies needed for evolving.
     * @return A new Pokemon object, or null if an error occurs.
     */
    @Override
    public Pokemon createPokemon(final int index, final int cp, final int hp,
                                 final int dust, final int candy) {
        int iv = 1;
        PokemonMetadata pokemonMetadata = null;
        try {
            PokemonMetadataProvider provider = new PokemonMetadataProvider();
            pokemonMetadata = provider.getPokemonMetadata(index);
        } catch (PokedexException pe) {
            System.err.println("Error PokemonMetadataProvider !!!");
            return null;
        }
        return new Pokemon(
                index,
                pokemonMetadata.getName(),
                pokemonMetadata.getAttack(),
                pokemonMetadata.getDefense(),
                pokemonMetadata.getStamina(),
                cp, hp, dust, candy, iv
        );
    }


}
