package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class IPokedexFactoryTest {
    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    @Mock
    private IPokedexFactory pokedexFactory;

    private IPokedex pokedex;
    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    void setUp() throws PokedexException {
        pokedex = mock(IPokedex.class);
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        doReturn(pokedex).when(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);
        try {
            when(pokedex.addPokemon(bulbizarre)).thenReturn(0);
            when(pokedex.addPokemon(herbizarre)).thenReturn(1);
            when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
            when(pokedex.getPokemon(1)).thenReturn(herbizarre);
            when(pokedex.getPokemons()).thenReturn(Arrays.asList(bulbizarre, herbizarre));
            doThrow(new PokedexException("Invalid idx")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(createdPokedex, "pokedex ne doit pas etre null !!!");
        assertSame(pokedex, createdPokedex, "pokedex doit etre  identique au mock retourne par createPokedex !!!");
        verify(pokedexFactory, times(1)).createPokedex(eq(metadataProvider), eq(pokemonFactory));
    }

    @Test
    void testGetPokemonInvalidIndexException() throws PokedexException {
        doThrow(new PokedexException("Invalid idx")).when(pokedex).getPokemon(-1);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertThrows(PokedexException.class, () -> createdPokedex.getPokemon(-1),
                "PokedexException doit etre lever si indx invalide !!!");
    }

    @Test
    void testPokedexSizeAfterAddingPokemons() throws PokedexException {
        when(pokedex.size()).thenReturn(2);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        createdPokedex.addPokemon(bulbizarre);
        createdPokedex.addPokemon(herbizarre);

        assertEquals(2, createdPokedex.size(), "apres l'ajout de 2 pokemons la taille du pokedex doit egal à 2 !!!");
    }



}
