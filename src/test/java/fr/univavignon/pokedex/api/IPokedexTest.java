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
    public void testGetPokemonMetadataWithException() throws PokedexException {
        assertEquals(pokedex.getPokemonMetadata(1), pokemonMetadataProvider.getPokemonMetadata(1));
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

//test creation pokemon
    //valid params
    @Test
    public void testCreatePokemonValidParams() {
        Pokemon createdPokemon = pokedex.createPokemon(0, 613, 64, 4000, 4);
        assertNotNull(createdPokemon);
    }

    //invalid params :
    @Test
    public void testCreatePokemonInvalidParams() {
        Pokemon pokemonInvalidAllParams = pokedex.createPokemon(-1000, -1000, -1000, -1000, -1000);

        assertNull(pokemonInvalidAllParams);

        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getHp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getDust());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getIv());
    }

    @Test
    void testInvalidNegativeIndxReturnNull() {

        Pokemon pokemonNegativeIndex = pokedex.createPokemon(-1, 1632, 201, 4000, 5);

        assertNull(pokemonNegativeIndex);

        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getIv());


    }

    @Test
    void testInvalidIndxSup150ReturnNull() {

        Pokemon pokemonSupIndex = pokedex.createPokemon(200, 1632, 201, 4000, 5);

        assertNull(pokemonSupIndex);


        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getIv());

    }

//verification metaDonnes
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        //test meta data 1
        PokemonMetadata metadata1 = pokedex.getPokemonMetadata(0);
        assertNotNull(metadata1);
        assertEquals("Bulbizarre", metadata1.getName());

        //test meta data 2
        PokemonMetadata metadata2 = pokedex.getPokemonMetadata(1);
        assertNotNull(metadata2);
        assertEquals("Herbizarre", metadata2.getName());

    }

    @Test
    public void testAddPokemonNull() {

        assertEquals(-1, pokedex.addPokemon(null));
    }

    @Test
    public void testGetPokemonInvalidNegativeIndex() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-6));
    }
    @Test
    public void testGetPokemonInvalidSup150Index() {
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


//test recuperation meta data
    @Test
    public void testLaRecuperationDesMetadonneesIndxZero() throws PokedexException {
        assertEquals(pokedex.getPokemonMetadata(0), pokemonMetadataProvider.getPokemonMetadata(0));
    }

    @Test
    public void testLaRecuperationDesMetadonneesIndxUn() throws PokedexException {
        assertEquals(pokedex.getPokemonMetadata(1), pokemonMetadataProvider.getPokemonMetadata(1));
    }

    //test order
    //et aussi PokemonComparators

    //test par nom
    @Test
    public void testTriPokemonsParNom() {
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(herbizarre);

        List<Pokemon> pokemonsTriesNom = pokedex.getPokemons(PokemonComparators.NAME);

        assertEquals("Bulbizarre", pokemonsTriesNom.get(0).getName());
        assertEquals("Herbizarre", pokemonsTriesNom.get(1).getName());
    }

    //test par Index
    @Test
    public void testTriPokemonsParIndex() {
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(herbizarre);

        List<Pokemon> pokemonsTriesIndex = pokedex.getPokemons(PokemonComparators.INDEX);

        assertEquals("Bulbizarre", pokemonsTriesIndex.get(0).getName());
        assertEquals("Herbizarre", pokemonsTriesIndex.get(1).getName());
    }

    //test par CP
    @Test
    public void testTriPokemonsParCp() {
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(herbizarre);

        List<Pokemon> pokemonsTriesCp = pokedex.getPokemons(PokemonComparators.CP);

        assertEquals("Herbizarre", pokemonsTriesCp.get(0).getName());
        assertEquals("Bulbizarre", pokemonsTriesCp.get(1).getName());
    }

}
