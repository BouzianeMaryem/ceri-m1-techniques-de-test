package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
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

        Comparator<Pokemon> comparator = Comparator.comparing(Pokemon::getName);
        when(pokedex.getPokemons(comparator)).thenReturn(List.of(bulbizarre, herbizarre));

        when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(invocation -> {
            Pokemon pokemon = invocation.getArgument(0);
            if (pokemon.getName().equals("Bulbizarre")) return 0;
            else if (pokemon.getName().equals("Herbizarre")) return 1;
            else return -1;
        });
        // config pour idx > 150
        when(pokedex.getPokemon(151)).thenThrow(new PokedexException("Index out of bounds"));
        doAnswer(invocation -> {
            Integer index = invocation.getArgument(0);
            if (index > 150) {
                throw new PokedexException("Index out of bounds");
            }
            return null;
        }).when(pokedex).getPokemon(anyInt())
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
    //test on peut pas ajouter des pokemons si la liste est full avec 151 pokemons
    @Test
    public void testCannotAddPokemonWhenPokedexIsFull() {
        //  Pokedex plein
        when(pokedex.size()).thenReturn(151);

        // si j'essaie d'ajouter un pokemon supp
        Pokemon extraPokemon = new Pokemon(152, "TestPokemon", 100, 100, 100, 1000, 100, 5000, 5, 100);
        int indexExtra = pokedex.addPokemon(extraPokemon);

        //peut pas etre ajouté
        assertEquals(-1, indexExtra, "Aucun Pokémon ne devrait être ajouté si le Pokédex est plein.");
    }

    // test getpokemon()
    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(bulbizarre, pokedex.getPokemon(0));
        assertEquals(herbizarre, pokedex.getPokemon(1));
    }
//test getpokemons
    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
        assertTrue(pokemons.containsAll(List.of(bulbizarre, herbizarre)));
    }
//test si index > 150
@Test
public void testCannotGetPokemonWithIndexGreaterThan150() {
    assertThrows(PokedexException.class, () -> {
        pokedex.getPokemon(151);
    }, " si  indx > 150: on lancePokedexException ");
}
}
