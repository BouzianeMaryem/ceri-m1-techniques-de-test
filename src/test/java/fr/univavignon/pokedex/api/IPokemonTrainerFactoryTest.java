package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class IPokemonTrainerFactoryTest {

    @Mock
    private IPokedexFactory mockPokedexFactory;

    @Mock
    private IPokedex mockPokedex;

    @Mock
    private IPokemonTrainerFactory factoryTrainerMock;

    @Mock
    private PokemonTrainer expectTrainerMock;

    @BeforeEach
    void setUp() {
        lenient().when(mockPokedexFactory.createPokedex(any(), any())).thenReturn(mockPokedex);
    }

    @Test
    public void testHandlingOfDuplicateTrainerCreation() {
        String trainerName = "Misty";
        Team team = Team.VALOR;

        when(factoryTrainerMock.createTrainer(eq(trainerName), eq(team), eq(mockPokedexFactory)))
                .thenReturn(expectTrainerMock).thenThrow(new IllegalStateException("Trainer déjà existant."));

        PokemonTrainer firstCreation = factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory);
        assertSame(expectTrainerMock, firstCreation, "Le premier trainer créé doit être retourné.");

        assertThrows(IllegalStateException.class, () -> {
            factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory);
        }, "La tentative de recréation d'un trainer existant devrait échouer.");
    }

    @Test
    public void testTrainerInformationConsistency() {
        String trainerName = "Brock";
        Team team = Team.MYSTIC;

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, mockPokedex);
        when(factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory)).thenReturn(trainer);

        PokemonTrainer createdTrainer = factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory);

        assertAll("Trainer information should be consistent",
                () -> assertEquals(trainerName, createdTrainer.getName()),
                () -> assertEquals(team, createdTrainer.getTeam()),
                () -> assertSame(mockPokedex, createdTrainer.getPokedex())
        );
    }

    @Test
    public void testTrainerCreationWithNullParameters() {
        when(factoryTrainerMock.createTrainer(null, null, null)).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> {
            factoryTrainerMock.createTrainer(null, null, null);
        }, "La création d'un entraîneur avec des paramètres null devrait lancer une IllegalArgumentException.");
    }

    @Test
    public void verifyTrainerCreatedIsCorrect() {
        String nomTrainer = "Lance";
        Team choixTeam = Team.VALOR;

        lenient().when(factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory)).thenReturn(expectTrainerMock);

        PokemonTrainer newTrainer = factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        verify(factoryTrainerMock).createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        assertEquals(expectTrainerMock, newTrainer, "The created trainer should match the expected mock instance.");
    }
}
