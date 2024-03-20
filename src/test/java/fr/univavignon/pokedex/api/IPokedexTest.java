package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import org.mockito.Mockito;
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
    // c à d : on doit pas dépasser 151 pokemons
    @Test
    public void testCannotAddPokemonWhenPokedexIsFull() {
        when(pokedex.size()).thenReturn(151);
        Pokemon testPok = new Pokemon(152, "Bulbul", 100, 100, 100, 1000, 100, 5000, 5, 100);
        int idxSupp = pokedex.addPokemon(testPok);
        assertEquals(-1, idxSupp, "le pokedex est plein , impossible d'ajouter un pokemon !!!");
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
    //et aussi PokemonComparators

    //test par nom
    @Test
    public void testGetPokemonsOrderParNom() {
        PokemonComparators nameComparator = PokemonComparators.NAME;

        List<Pokemon> expectedPokemonsSortedByName = Arrays.asList(bulbizarre, herbizarre);
        Mockito.doReturn(expectedPokemonsSortedByName).when(pokedex).getPokemons(nameComparator);
        List<Pokemon> pokemonsSortedByName = pokedex.getPokemons(nameComparator);

        assertNotNull(pokemonsSortedByName);
        assertEquals(2, pokemonsSortedByName.size());

        assertEquals("Bulbizarre", pokemonsSortedByName.get(0).getName());
        assertEquals("Herbizarre", pokemonsSortedByName.get(1).getName());
    }


// test par index
@Test
public void testGetPokemonsOrderParIndex() {
    PokemonComparators indexComparator = PokemonComparators.INDEX;

    List<Pokemon> expectedPokemonsSortedByIndex = Arrays.asList(bulbizarre, herbizarre);
    Mockito.doReturn(expectedPokemonsSortedByIndex).when(pokedex).getPokemons(indexComparator);

    List<Pokemon> pokemonsSortedByIndex = pokedex.getPokemons(indexComparator);

    assertNotNull(pokemonsSortedByIndex);
    assertEquals(2, pokemonsSortedByIndex.size());

    assertEquals("Bulbizarre", pokemonsSortedByIndex.get(0).getName());
    assertEquals("Herbizarre", pokemonsSortedByIndex.get(1).getName());
}



    // test par CP
    @Test
    public void testGetPokemonsOrderParCP() {
        PokemonComparators cpComparator = PokemonComparators.CP;
        List<Pokemon> expectedPokemonsSortedByCP = Arrays.asList(bulbizarre, herbizarre);
        Mockito.doReturn(expectedPokemonsSortedByCP).when(pokedex).getPokemons(cpComparator);

        List<Pokemon> pokemonsSortedByCP = pokedex.getPokemons(cpComparator);

        assertNotNull(pokemonsSortedByCP);
        assertEquals(2, pokemonsSortedByCP.size());

        assertEquals(613, pokemonsSortedByCP.get(0).getCp());
        assertEquals(80, pokemonsSortedByCP.get(1).getCp());
    }


}