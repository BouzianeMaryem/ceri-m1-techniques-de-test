package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class IPokedexTest {
    private IPokedex pokedex;
    private IPokedex pokedex2;
    private IPokemonMetadataProvider pokemonMetadataProvider;
    private IPokemonFactory pokemonFactory;
    private IPokemonFactory pokemonFactory2;

    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    public void setUp() throws PokedexException {
        pokemonMetadataProvider = mock(IPokemonMetadataProvider.class);
        pokemonFactory = mock(IPokemonFactory.class);
        pokemonFactory2 = new FactoryPokemon();
        pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);
        pokedex2 = new Pokedex(pokemonMetadataProvider, pokemonFactory2);
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
        assertEquals(0, pokedex2.size());

        pokedex2.addPokemon(bulbizarre);
        assertEquals(1, pokedex2.size());

        pokedex2.addPokemon(herbizarre);
        assertEquals(2, pokedex2.size());
    }
    @Test
    public void testGetPokemonMetadataWithException() throws PokedexException {
        assertEquals(pokedex2.getPokemonMetadata(1), pokemonMetadataProvider.getPokemonMetadata(1));
    }
    @Test
    public void testGetPokemons() {
        pokedex2.addPokemon(bulbizarre);
        pokedex2.addPokemon(herbizarre);
        List<Pokemon> pokemons = pokedex2.getPokemons();
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.contains(bulbizarre));
        assertTrue(pokemons.contains(herbizarre));
    }

//test creation pokemon
    //valid params
    @Test
    public void testCreatePokemonValidParams() {
        Pokemon createdPokemon = pokedex2.createPokemon(0, 613, 64, 4000, 4);
        assertNotNull(createdPokemon);
    }

    //invalid params :
    @Test
    public void testCreatePokemonInvalidParams() {

        Pokemon pokemonInvalidAllParams = pokedex2.createPokemon(-1000, -1000, -1000, -1000, -1000);

        Assertions.assertNull(pokemonInvalidAllParams);

        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getHp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getDust());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getIv());
    }

    @Test
    void testInvalidNegativeIndxReturnNull() {

        Pokemon pokemonNegativeIndex = pokedex2.createPokemon(-1, 1632, 201, 4000, 5);

        Assertions.assertNull(pokemonNegativeIndex);

        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getIv());


    }

    @Test
    void testInvalidIndxSup150ReturnNull() {

        Pokemon pokemonSupIndex = pokedex2.createPokemon(200, 1632, 201, 4000, 5);

        Assertions.assertNull(pokemonSupIndex);


        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getIv());

    }

//verification metaDonnees
    @Test
    public void testGetPokemonMetadata() throws PokedexException {
        //test meta data 1
        PokemonMetadata metadata1 = pokedex2.getPokemonMetadata(0);
        assertNotNull(metadata1);
        assertEquals("Bulbizarre", metadata1.getName());

        //test meta data 2
        PokemonMetadata metadata2 = pokedex2.getPokemonMetadata(1);
        assertNotNull(metadata2);
        assertEquals("Herbizarre", metadata2.getName());

    }

    @Test
    public void testAddPokemonNull() {

        assertEquals(-1, pokedex2.addPokemon(null));
    }

    @Test
    public void testGetPokemonInvalidNegativeIndex() {
        assertThrows(PokedexException.class, () -> pokedex2.getPokemon(-6));
    }
    @Test
    public void testGetPokemonInvalidSup150Index() {
        assertThrows(PokedexException.class, () -> pokedex2.getPokemon(500));
    }
    @Test
    public void testAddAndGetPokemon() throws PokedexException {
        int indexBulbizarre = pokedex2.addPokemon(bulbizarre);
        assertEquals(0, indexBulbizarre);

        Pokemon fetchedBulbizarre = pokedex2.getPokemon(indexBulbizarre);
        assertEquals(bulbizarre.getName(), fetchedBulbizarre.getName());

        int indexHerbizarre = pokedex2.addPokemon(herbizarre);
        assertEquals(1, indexHerbizarre);

        Pokemon fetchedHerbizarre = pokedex2.getPokemon(indexHerbizarre);
        assertEquals(herbizarre.getName(), fetchedHerbizarre.getName());
    }


//test recuperation meta data
    @Test
    public void testLaRecuperationDesMetadonneesIndxZero() throws PokedexException {
        assertEquals(pokedex2.getPokemonMetadata(0), pokemonMetadataProvider.getPokemonMetadata(0));
    }

    @Test
    public void testLaRecuperationDesMetadonneesIndxUn() throws PokedexException {
        assertEquals(pokedex2.getPokemonMetadata(1), pokemonMetadataProvider.getPokemonMetadata(1));
    }

    //test order
    //et aussi PokemonComparators

    //test par nom
    @Test
    public void testTriPokemonsParNom() {
        pokedex2.addPokemon(bulbizarre);
        pokedex2.addPokemon(herbizarre);

        List<Pokemon> pokemonsTriesNom = pokedex2.getPokemons(PokemonComparators.NAME);

        assertEquals("Bulbizarre", pokemonsTriesNom.get(0).getName());
        assertEquals("Herbizarre", pokemonsTriesNom.get(1).getName());
    }

    //test par Index
    @Test
    public void testTriPokemonsParIndex() {
        pokedex2.addPokemon(bulbizarre);
        pokedex2.addPokemon(herbizarre);

        List<Pokemon> pokemonsTriesIndex = pokedex2.getPokemons(PokemonComparators.INDEX);

        assertEquals("Bulbizarre", pokemonsTriesIndex.get(0).getName());
        assertEquals("Herbizarre", pokemonsTriesIndex.get(1).getName());
    }

    //test par CP
    @Test
    public void testTriPokemonsParCp() {
        pokedex2.addPokemon(bulbizarre);
        pokedex2.addPokemon(herbizarre);

        List<Pokemon> pokemonsTriesCp = pokedex2.getPokemons(PokemonComparators.CP);

        assertEquals("Herbizarre", pokemonsTriesCp.get(0).getName());
        assertEquals("Bulbizarre", pokemonsTriesCp.get(1).getName());
    }

}
