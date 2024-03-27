package fr.univavignon.pokedex.api;

/**
 * Pokemon factory. Implementation of IPokemonFactory.
 *
 * @author Khaoula Otmani
 */
public class PokemonFactory implements IPokemonFactory {


    @Override
    public Pokemon createPokemon(final int index, final int cp, final int hp,
                                 final int dust,
                                 final int candy) {
        PokemonMetadata pokemonMetadata;
        try {
            pokemonMetadata =
                    new PokemonMetadataProvider().getPokemonMetadata(index);
        } catch (PokedexException pe) {
            return null;
        }
        int iv = 1;

        return new Pokemon(index, pokemonMetadata.getName(),
                pokemonMetadata.getAttack(), pokemonMetadata.getDefense(),
                pokemonMetadata.getStamina(), cp, hp, dust, candy, iv);
    }
}