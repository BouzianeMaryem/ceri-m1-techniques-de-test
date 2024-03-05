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
        // comportement qu'on veut de createPokemon du mock.
        doReturn(new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 1.0))
                .when(pokemonFactory).createPokemon(anyInt(), anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    void testCreatePokemon() {
        Pokemon createdPokemon = pokemonFactory.createPokemon(133, 2729, 202, 5000, 4);

        // verifications
        assertNotNull(createdPokemon, "Pokémon doit etre non null !!! ");
        assertTrue(createdPokemon.getIndex() == 133, "idx ne correspond pas à celui attendu !!!");
        assertTrue(createdPokemon.getName().equals("Aquali"), "Le nom est different !!!");
        assertTrue(createdPokemon.getCp() == 2729, "Le cp est different !!!");
        assertTrue(createdPokemon.getHp() == 202, " HP est different !!!");
    }
}
