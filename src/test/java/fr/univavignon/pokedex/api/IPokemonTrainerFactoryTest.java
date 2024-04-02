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
    private PokemonTrainer expectedMerly;



    @BeforeEach
    void setUp() {

        lenient().when(pokedexFactory.createPokedex(any(), any())).thenReturn(pokedex);
    }

    @Test
    public void testCreateTrainer() {
        IPokemonMetadataProvider metadataProvider = mock(IPokemonMetadataProvider.class);
        IPokemonFactory pokemonFactory = mock(IPokemonFactory.class);
        IPokedexFactory pokedexFactory = mock(IPokedexFactory.class);

        PokemonTrainerFactory trainerFactory = new PokemonTrainerFactory(metadataProvider, pokemonFactory);
        String name = "Mona";
        Team team = Team.VALOR;

        when(pokedexFactory.createPokedex(metadataProvider, pokemonFactory)).thenReturn(mock(IPokedex.class));
        PokemonTrainer trainer = trainerFactory.createTrainer(name, team, pokedexFactory);

        assertNotNull(trainer);
        assertEquals(name, trainer.getName());
        assertEquals(team, trainer.getTeam());
    }
    @Test
    public void TrainerInformationCoherencetest() {
        String nom = "Mimi";
        Team team = Team.VALOR;

        PokemonTrainer trainerCoherence = new PokemonTrainer(nom, team, pokedex);
        when(trainerFactory.createTrainer(nom, team, pokedexFactory)).thenReturn(trainerCoherence);

        PokemonTrainer createdTrainerCoherence = trainerFactory.createTrainer(nom, team, pokedexFactory);
        assertAll("les infos doivent etre coherentes!!!",
                () -> assertEquals(nom, createdTrainerCoherence.getName(), "name !!!"),
                () -> assertEquals(team, createdTrainerCoherence.getTeam(), "Team!!!"),
                () -> assertSame(pokedex, createdTrainerCoherence.getPokedex(), "Pokedex!!!")
        );
    }
    @Test
    public void trainerAttenduCreationTest() {
        when(trainerFactory.createTrainer(anyString(), any(Team.class), any(IPokedexFactory.class))).thenReturn(expectedMerly);
        PokemonTrainer Merly = trainerFactory.createTrainer("Merly", Team.MYSTIC, pokedexFactory);
        assertSame(expectedMerly, Merly, "doit creer le trainer expected!!!");
    }



}
