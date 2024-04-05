package fr.univavignon.pokedex.api;

/**
 * Factory pour créer des objets Pokemon.
 * Cette classe implémente IPokemonFactory et crée des Pokemon.
 */
public final class FactoryPokemon implements IPokemonFactory {

    /**
     * Crée un nouveau Pokémon avec les paramètres spécifiés.
     * @param index L'index du Pokémon dans le Pokédex.
     * @param cp Les points de combat du Pokémon.
     * @param hp Les points de vie du Pokémon.
     * @param dust La poussière d'étoile pour améliorer le Pokémon.
     * @param candy Les bonbons pour améliorer le Pokémon.
     * @return Un nouvel objet Pokémon, ou null si une erreur est survenue.
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
