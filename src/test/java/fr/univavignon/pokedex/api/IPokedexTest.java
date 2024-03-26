package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Comparator;
import java.util.List;

public class IPokedexTest {

    private IPokedex pokedex;
    private IPokemonMetadataProvider metadataProviderMock;
    private IPokemonFactory pokemonFactoryMock;
    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    public void setUp() throws PokedexException {
        metadataProviderMock = mock(IPokemonMetadataProvider.class);
        pokemonFactoryMock = mock(IPokemonFactory.class);
        pokedex = new Pokedex(metadataProviderMock, pokemonFactoryMock);

        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4)).thenReturn(bulbizarre);
        when(pokemonFactoryMock.createPokemon(1, 80, 80, 1500, 20)).thenReturn(herbizarre);

        // Simulating metadata retrieval
        when(metadataProviderMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        when(metadataProviderMock.getPokemonMetadata(1)).thenReturn(new PokemonMetadata(1, "Herbizarre", 60, 62, 63));

        // Adding Pokemon to Pokedex
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(herbizarre);
    }

    @Test
    public void testSize() {
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);
        int indexAquali  = pokedex.addPokemon(aquali );
        assertEquals(2, indexAquali);
        assertEquals(3, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(bulbizarre, pokedex.getPokemon(0));
        assertEquals(herbizarre, pokedex.getPokemon(1));
    }

    @Test
    public void testGetPokemonWithInvalidIndex() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(2));
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.containsAll(List.of(bulbizarre, herbizarre)));
    }

    @Test
    public void testGetPokemonsWithSorting() {
        Comparator<Pokemon> comparator = Comparator.comparing(Pokemon::getIndex);
        List<Pokemon> sortedPokemons = pokedex.getPokemons(comparator);
        assertNotNull(sortedPokemons);
        assertEquals(2, sortedPokemons.size());
        assertEquals(bulbizarre, sortedPokemons.get(0));
        assertEquals(herbizarre, sortedPokemons.get(1));
    }
}
