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

    private Pokemon bulbizarre;
    private Pokemon aquali;

    @BeforeEach
    void setUp() {
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 0.56);
        aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0);

        lenient().when(pokemonFactory.createPokemon(eq(0), eq(613), eq(64), eq(4000), eq(4))).thenReturn(bulbizarre);
        lenient().when(pokemonFactory.createPokemon(eq(133), eq(2729), eq(202), eq(5000), eq(4))).thenReturn(aquali);
        lenient().when(pokemonFactory.createPokemon(eq(-1), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(null);
    }

    @Test
    void testCreationDePokemonAvecParametresValides() {
        Pokemon pokemonCree1 = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        //bulbizarre
        assertNotNull(pokemonCree1, "Le pokemon créé ne doit pas être nul!!");
        assertEquals(bulbizarre.getIndex(), pokemonCree1.getIndex(), "L'index du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getName(), pokemonCree1.getName(), "Le nom du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getAttack(), pokemonCree1.getAttack(), "L'attaque du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getDefense(), pokemonCree1.getDefense(), "La défense du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getStamina(), pokemonCree1.getStamina(), "L'endurance du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getCp(), pokemonCree1.getCp(), "Le CP du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getHp(), pokemonCree1.getHp(), "Le HP du pokemon est incorrect !!!");
        assertEquals(bulbizarre.getDust(), pokemonCree1.getDust(), "La poussière du pokemon est incorrect !!!!!");
        assertEquals(bulbizarre.getCandy(), pokemonCree1.getCandy(), "Le candy du pokemon est incorrect !!!");

        //aquali
        Pokemon pokemonCree2 = pokemonFactory.createPokemon(0, 2729, 202, 5000, 4);

        assertNotNull(pokemonCree2, "Le pokemon créé ne doit pas être nul!!");
        assertEquals(aquali.getIndex(), pokemonCree2.getIndex(), "L'index du pokemon est incorrect !!!!!");
        assertEquals(aquali.getName(), pokemonCree2.getName(), "Le nom du pokemon est incorrect !!!!!");
        assertEquals(aquali.getAttack(), pokemonCree2.getAttack(), "L'attaque du pokemon est incorrect !!!!!");
        assertEquals(aquali.getDefense(), pokemonCree2.getDefense(), "La défense du pokemon est incorrect !!!!!");
        assertEquals(aquali.getStamina(), pokemonCree2.getStamina(), "L'endurance du pokemon est incorrect !!!!!");
        assertEquals(aquali.getCp(), pokemonCree2.getCp(), "Le CP du pokemon est incorrect !!!!!");
        assertEquals(aquali.getHp(), pokemonCree2.getHp(), "Le HP du pokemon est incorrect !!!");
        assertEquals(aquali.getDust(), pokemonCree2.getDust(), "La poussière du pokemon est incorrect !!!!!");
        assertEquals(aquali.getCandy(), pokemonCree2.getCandy(), "Le candy du pokemon est incorrect !!!");

    }

    @Test
    void testCreationDePokemonAvecParametresInvalidesdoitRenvoyerNull() {
        assertNull(pokemonFactory.createPokemon(-1, 10000, 10000, 10000, 10000), "un pokemon cree avec des params inamides doit envoyer null !!!");
    }


    @Test
    void testLeHpDuPokemonEstCorrectementDefini() {
        assertEquals(bulbizarre.getHp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getHp(), "Le HP doit correspondre à celui de  Bulbizarre!!");
        assertEquals(aquali.getHp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 4).getHp(), "Le HP doit correspondre à celui de  Aquali!!");
    }

    @Test
    void testLeCandyDuPokemonEstCorrectementDefini() {
        assertEquals(bulbizarre.getCandy(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCandy(), "Le candy doit correspondre à celui de  Bulbizarre!!");
        assertEquals(aquali.getCandy(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 4).getCandy(), "Le candy doit correspondre à celui de  Aquali!!");
    }

    @Test
    void testLeCpDuPokemonEstCorrectementDefini() {
        assertEquals(bulbizarre.getCp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCp(), "Le CP doit correspondre à celui de  Bulbizarre!!");
        assertEquals(aquali.getCp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 4).getCp(), "Le CP doit correspondre à celui de  Aquali!!");
    }

    @Test
    void testLaDustDuPokemonEstCorrectementDefinie() {
        assertEquals(bulbizarre.getDust(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getDust(), "la poussière doit correspondre à celui de  Bulbizarre!!");
        assertEquals(aquali.getDust(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 4).getDust(), "la poussière doit correspondre à celui de  Aquali!!");
    }
}
