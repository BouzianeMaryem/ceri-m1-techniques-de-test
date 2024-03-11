package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonFactoryTest {

    @Mock
    private IPokemonFactory pokemonFactory;

    @BeforeEach
    void setUp() {
        doReturn(new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0))
                .when(pokemonFactory).createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    void testCreationOfPokemonWithValidParameters() {
        Pokemon createdPokemon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);

        // verifications
        assertNotNull(createdPokemon, "The created Pokémon should not be null.");
        assertEquals(133, createdPokemon.getIndex(), "The Pokémon index does not match the expected value.");
        assertEquals("Aquali", createdPokemon.getName(), "The Pokémon name does not match the expected value.");
        assertEquals(2729, createdPokemon.getCp(), "The Pokémon CP does not match the expected value.");
        assertEquals(202, createdPokemon.getHp(), "The Pokémon HP does not match the expected value.");
    }

    @Test
    void testCreationOfPokemonWithInvalidParametersShouldReturnNull() {
        assertNull(pokemonFactory.createPokemon(-1, 25, 25, 25, 25), "Creating a Pokémon with invalid parameters should return null.");
    }


    @Test
    void testPokemonHpIsCorrectlySet() {
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 118, 118, 90, 613, 64, 4000, 4, 0.5);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1.0);

        assertEquals(bulbizarre.getHp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getHp(), "The HP value should match for Bulbizarre.");
        assertEquals(aquali.getHp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getHp(), "The HP value should match for Aquali.");
        assertNotEquals(aquali.getHp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getHp(), "The HP value should not match when comparing different Pokémon.");
    }
    @Test
    void testPokemonCandyIsCorrectlySet() {
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 118, 118, 90, 613, 64, 4000, 4, 0.5);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1.0);

        assertEquals(bulbizarre.getCandy(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCandy(), "The Candy value should match for Bulbizarre.");
        assertEquals(aquali.getCandy(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getCandy(), "The Candy value should match for Aquali.");
        assertNotEquals(aquali.getCandy(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCandy(), "The Candy value should not match when comparing different Pokémon.");
    }
    @Test
    void testPokemonDustIsCorrectlySet() {
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 118, 118, 90, 613, 64, 4000, 4, 0.5);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1.0);

        assertEquals(bulbizarre.getDust(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getDust(), "The Dust value should match for Bulbizarre.");
        assertEquals(aquali.getDust(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getDust(), "The Dust value should match for Aquali.");
        assertNotEquals(aquali.getDust(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getDust(), "The Dust value should not match when comparing different Pokémon.");
    }
    @Test
    void testPokemonCpIsCorrectlySet() {
        Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 118, 118, 90, 613, 64, 4000, 4, 0.5);
        Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1.0);

        assertEquals(bulbizarre.getCp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCp(), "The CP value should match for Bulbizarre.");
        assertEquals(aquali.getCp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getCp(), "The CP value should match for Aquali.");
        assertNotEquals(aquali.getCp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCp(), "The CP value should not match when comparing different Pokémon.");
    }
}
