package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonFactoryTest {

    @Mock
    private IPokemonFactory pokemonFactory;

    private Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1000);
    private Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

    @BeforeEach
    void setUp() {
        lenient().when(pokemonFactory.createPokemon(eq(133), eq(2729), eq(202), eq(5000), eq(4))).thenReturn(aquali);
        lenient().when(pokemonFactory.createPokemon(eq(0), eq(613), eq(64), eq(4000), eq(4))).thenReturn(bulbizarre);
        lenient().when(pokemonFactory.createPokemon(eq(-1), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(null);
    }


    @Test
    void testCreationDePokemonAvecParametresValides() {
        Pokemon pokemonCree = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);

        assertNotNull(pokemonCree, "Le Pokémon créé ne devrait pas être nul.");
        assertEquals(aquali.getIndex(), pokemonCree.getIndex(), "L'index du Pokémon ne correspond pas à la valeur attendue !!!");
        assertEquals(aquali.getName(), pokemonCree.getName(), "Le nom du Pokémon ne correspond pas à la valeur attendue !!!");
        assertEquals(aquali.getCp(), pokemonCree.getCp(), "Le CP du Pokémon ne correspond pas à la valeur attendue !!! ");
        assertEquals(aquali.getHp(), pokemonCree.getHp(), "Les HP du Pokémon ne correspondent pas à la valeur attendue !!! ");
    }

    @Test
    void testCreationDePokemonAvecParametresInvalidesDevraitRenvoyerNull() {
        assertNull(pokemonFactory.createPokemon(-1, 25, 25, 25, 25), "Pokemon avec invalides params doit return null !!!");
    }

    @Test
    void testLeHpDuPokemonEstCorrectementDefini() {
        assertEquals(bulbizarre.getHp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getHp(), "La valeur des HP devrait correspondre pour Bulbizarre.");
        assertEquals(aquali.getHp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getHp(), "La valeur des HP devrait correspondre pour Aquali.");
    }

    @Test
    void testLeCandyDuPokemonEstCorrectementDefini() {
        assertEquals(bulbizarre.getCandy(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCandy(), "La valeur des bonbons devrait correspondre pour Bulbizarre.");
        assertEquals(aquali.getCandy(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getCandy(), "La valeur des bonbons devrait correspondre pour Aquali.");
    }

    @Test
    void testLeCpDuPokemonEstCorrectementDefini() {
        assertEquals(bulbizarre.getCp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCp(), "La valeur du CP devrait correspondre pour Bulbizarre.");
        assertEquals(aquali.getCp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getCp(), "La valeur du CP devrait correspondre pour Aquali.");
    }

    @Test
    void testLaDustDuPokemonEstCorrectementDefinie() {
        assertEquals(bulbizarre.getDust(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getDust(), "La valeur de la poussière devrait correspondre pour Bulbizarre.");
        assertEquals(aquali.getDust(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getDust(), "La valeur de la poussière devrait correspondre pour Aquali.");
    }
}
