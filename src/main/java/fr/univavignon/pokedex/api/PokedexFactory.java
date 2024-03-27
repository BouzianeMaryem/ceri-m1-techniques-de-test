package fr.univavignon.pokedex.api;
/**
 * Pokemon factory. Implementation of IPodexFactory
 *
 * @author Maryem Bouziane
 */
public class PokedexFactory implements IPokedexFactory {
    @Override
    public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
        return new Pokedex(metadataProvider, pokemonFactory);
    }
}