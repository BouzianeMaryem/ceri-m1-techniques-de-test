package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        when(mockPokedexFactory.createPokedex(any(), any())).thenReturn(mockPokedex);
    }

    @Test
    public void ensureTrainerCreationIsCorrect() {
        String nomTrainer = "Misty";
        Team choixTeam = Team.VALOR;


        when(factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory)).thenReturn(expectTrainerMock);


        PokemonTrainer newTrain = factoryTrainerMock.createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        verify(factoryTrainerMock).createTrainer(nomTrainer, choixTeam, mockPokedexFactory);

        assertEquals(expectTrainerMock, newTrain, "le trainer creé doit matcher avec le trainer qu'on attend !!!");
    }
}
