package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IPokedexFactoryTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    @Mock
    private IPokedexFactory pokedexFactory;

    private IPokedex pokedex;

    @BeforeEach
    void setUp() {
        // preparation des mocks
        pokedex = mock(IPokedex.class);

        doReturn(pokedex).when(pokedexFactory).createPokedex(eq(metadataProvider), eq(pokemonFactory));
    }

    @Test
    void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Vérification que le pokedex non null
        assertNotNull(createdPokedex, "Le pokedex créé ne doit pas être null");

        // verif pokedex cree == celui retourné par le factory
        assertSame(pokedex, createdPokedex, "Le pokedex créé doit être identique au mock retourné par createPokedex");

        // verif appel méthode de création  avec les bons paramètres
        verify(pokedexFactory, times(1)).createPokedex(eq(metadataProvider), eq(pokemonFactory));
    }
}
