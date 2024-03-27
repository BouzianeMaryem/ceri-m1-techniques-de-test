package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {
    private IPokedex pokedex;
    private IPokemonMetadataProvider pokemonMetadataProvider;
    private IPokemonFactory pokemonFactory;

    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    public void setUp() throws PokedexException {
        pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);

        pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);

        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        when(pokemonFactory.createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt()))
                .thenReturn(bulbizarre)
                .thenReturn(herbizarre);

        when(pokemonMetadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        when(pokemonMetadataProvider.getPokemonMetadata(1)).thenReturn(new PokemonMetadata(1, "Herbizarre", 60, 62, 63));
    }


    @Test
    public void testSize() {
        assertEquals(0, pokedex.size());

        pokedex.addPokemon(bulbizarre);
        assertEquals(1, pokedex.size());

        pokedex.addPokemon(herbizarre);
        assertEquals(2, pokedex.size());
    }
    @Test
    public void testGetPokemonMetadataWithException() {
        int invalidIndx = 155;
        assertThrows(PokedexException.class, () -> pokedex.getPokemonMetadata(invalidIndx));
    }
    @Test
    public void testGetPokemons() {
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(herbizarre);
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.contains(bulbizarre));
        assertTrue(pokemons.contains(herbizarre));
    }


    @Test
    public void testCreatePokemon() {
        Pokemon createdPokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);
        assertNotNull(createdPokemon);
    }

    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        PokemonMetadata metadata = pokedex.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals("Bulbizarre", metadata.getName());
    }

    @Test
    public void testAddPokemonNull() {
        assertEquals(-1, pokedex.addPokemon(null));
    }

    @Test
    public void testGetPokemonInvalidIndex() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-6));
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(500));
    }

    @Test
    public void testAddAndGetPokemon() throws PokedexException {
        int indexBulbizarre = pokedex.addPokemon(bulbizarre);
        assertEquals(0, indexBulbizarre);

        Pokemon fetchedBulbizarre = pokedex.getPokemon(indexBulbizarre);
        assertEquals(bulbizarre.getName(), fetchedBulbizarre.getName());

        int indexHerbizarre = pokedex.addPokemon(herbizarre);
        assertEquals(1, indexHerbizarre);

        Pokemon fetchedHerbizarre = pokedex.getPokemon(indexHerbizarre);
        assertEquals(herbizarre.getName(), fetchedHerbizarre.getName());
    }

}
