package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    public void setUp() throws PokedexException {
        pokedex = mock(IPokedex.class);

        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        when(pokedex.size()).thenReturn(2);

        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedex.getPokemon(1)).thenReturn(herbizarre);

        when(pokedex.getPokemons()).thenReturn(List.of(bulbizarre, herbizarre));
        //Comparator
        when(pokedex.getPokemons(any(Comparator.class))).thenAnswer(invocation -> {
            Comparator<Pokemon> comp = invocation.getArgument(0, Comparator.class);
            List<Pokemon> sortedPokemons = new ArrayList<>(List.of(bulbizarre, herbizarre));
            sortedPokemons.sort(comp);
            return sortedPokemons;
        });


        when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(invocation -> {
            Pokemon pokemon = invocation.getArgument(0);
            if (pokemon.getName().equals("Bulbizarre")) return 0;
            else if (pokemon.getName().equals("Herbizarre")) return 1;
            else return -1;
        });

    }
// test size
    @Test
    public void testSize() {
        assertEquals(2, pokedex.size());
    }
// test add pokemon
    @Test
    public void testAddPokemon() {
        // Test de l'ajout de Bulbizarre
        int idxBulbizarre = pokedex.addPokemon(bulbizarre);
        assertEquals(0, idxBulbizarre);

        // Test de l'ajout de Herbizarre
        int idxHerbizarre = pokedex.addPokemon(herbizarre);
        assertEquals(1, idxHerbizarre);
    }
    //test on peut pas ajouter des pokemons si la liste est full
    // c à d : on 151 pokemons
    @Test
    public void testCannotAddPokemonWhenPokedexIsFull() throws PokedexException  {
        // Pokedex plein
        when(pokedex.size()).thenReturn(151);
        Pokemon extraPokemon = new Pokemon(152, "TestPokemon", 100, 100, 100, 1000, 100, 5000, 5, 100);

        // PokedexException est lancée
        assertThrows(PokedexException.class, () -> {
            pokedex.addPokemon(extraPokemon);
        }, "PokedexException, si le Pokedex est plein.");

    }


    // test getpokemon()
    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(bulbizarre, pokedex.getPokemon(0));
        assertEquals(herbizarre, pokedex.getPokemon(1));
    }
    //test getPokemon si index est invalid
    @Test
    public void testGetPokemonAvecIndexInvalid() throws PokedexException  {
        when(pokedex.getPokemon(-2)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = -2;

        assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(invalidIndex);
        }, "si invalid indx lance PodexException");



    }

//test getpokemons
    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.containsAll(List.of(bulbizarre, herbizarre)));
    }


    //test order
@Test
public void testGetPokemonsOrderParNom() {
    Comparator<Pokemon> comparator = Comparator.comparing(Pokemon::getName);
    List<Pokemon> pokemons = pokedex.getPokemons(comparator);
    assertNotNull(pokemons);
    assertEquals(2, pokemons.size());
    assertEquals("Bulbizarre", pokemons.get(0).getName());
    assertEquals("Herbizarre", pokemons.get(1).getName());
}
}
