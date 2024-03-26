package fr.univavignon.pokedex.api;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    @Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        switch (index) {
            case 0:
                return new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
            case 133:
                return new PokemonMetadata(133, "Aquali", 186, 168, 260);
            default:
                throw new PokedexException("Invalid index");
        }
    }
}
