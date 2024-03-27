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
    public void testTrainerInformationConsistency() {
        String trainerName = "Mimi";
        Team team = Team.VALOR;

        PokemonTrainer trainer = new PokemonTrainer(trainerName, team, pokedex);
        when(trainerFactory.createTrainer(trainerName, team, pokedexFactory)).thenReturn(trainer);

        PokemonTrainer createdTrainer = trainerFactory.createTrainer(trainerName, team, pokedexFactory);
        assertAll("les infos doivent etre consistants!!!",
                () -> assertEquals(trainerName, createdTrainer.getName(), "name !!!"),
                () -> assertEquals(team, createdTrainer.getTeam(), "Team!!!"),
                () -> assertSame(pokedex, createdTrainer.getPokedex(), "Pokedex!!!")
        );
    }
    @Test
    public void testCreateTrainerReturnsExpectedTrainer() {
        when(trainerFactory.createTrainer(anyString(), any(Team.class), any(IPokedexFactory.class))).thenReturn(expectedMerly);
        PokemonTrainer Merly = trainerFactory.createTrainer("Merly", Team.MYSTIC, pokedexFactory);
        assertSame(expectedMerly, Merly, "doit creer le trainer expected!!!");
    }



}
