package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonTrainerFactoryTest {

    @Mock
    private IPokedexFactory pokedexFactory;

    @Mock
    private IPokedex pokedex;

    @Mock
    private IPokemonTrainerFactory trainerFactory;

    @Mock
    private PokemonTrainer expectedTrainer;

    @BeforeEach
    void setUp() {
        // Configurez le mock de pokedexFactory pour retourner un mock de pokedex
        lenient().when(pokedexFactory.createPokedex(any(), any())).thenReturn(pokedex);
    }

    @Test
    void testCreateTrainer() {
        String trainerName = "Ash";
        Team team = Team.MYSTIC;

        // Configuration du mock de trainerFactory pour retourner le mock expectedTrainer
        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(expectedTrainer);

        // Invocation de la méthode à tester
        PokemonTrainer createdTrainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);

        // Vérification que le mock de createTrainer a été appelé avec les bons arguments
        verify(trainerFactory).createTrainer(trainerName, team, pokedexFactory);

        // Assertion pour vérifier que le trainer retourné est bien le mock attendu
        assertSame(expectedTrainer, createdTrainer, "Le trainer créé doit être le même que le mock attendu.");
    }
}
