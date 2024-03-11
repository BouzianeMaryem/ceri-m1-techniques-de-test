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
    public void verifyTrainerCreatedIsCorrect() {
        String nomTrainer = "Lance";
        Team choixTeam = Team.VALOR;

        lenient().when(factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory)).thenReturn(expectTrainerMock);

        PokemonTrainer newTrainer = factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        verify(factoryTrainerMock).createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        assertEquals(expectTrainerMock, newTrainer, "Le tariner  créé doit correspondre à l'instance attendue!!");
    }
    @Test
    public void testHandlingOfDuplicateTrainerCreation() {
        String trainerName = "Misty";
        Team team = Team.VALOR;

        when(factoryTrainerMock.createTrainer(eq(trainerName), eq(team), eq(mockPokedexFactory)))
                .thenReturn(expectTrainerMock).thenThrow(new IllegalStateException("Trainer existe deja !!"));

        PokemonTrainer firstCreation = factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory);
        assertSame(expectTrainerMock, firstCreation, "doit retourner le premier trainer créé !!");

        assertThrows(IllegalStateException.class, () -> {
            factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory);
        }, "La recréation d'un trainer existant doit echouer !!");
    }

    @Test
    public void testTrainerInformationConsistency() {
        String trainerName = "Brock";
        Team team = Team.MYSTIC;

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, mockPokedex);
        when(factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory)).thenReturn(trainer);

        PokemonTrainer createdTrainer = factoryTrainerMock.createTrainer(trainerName, team, mockPokedexFactory);

        assertAll("les infos du trainer doivent etre coherentes !!!",
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
        }, "la creation d'un trainer avec des params null , doit lever une exception !!!");
    }


}
