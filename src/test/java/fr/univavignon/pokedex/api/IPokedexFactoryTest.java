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
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);
        doReturn(pokedex).when(pokedexFactory).createPokedex(eq(metadataProvider), eq(pokemonFactory));
    }
    //creation de Podex
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
    //lancer exception invalid index

    @Test
    void testGetPokemonInvalidIndexException() throws PokedexException {
        doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertThrows(PokedexException.class, () -> createdPokedex.getPokemon(-1),
                "Accès à un index invalide doit lever une PokedexException.");
    }

    @Test
    void testPokedexSizeAfterAddingPokemons() throws PokedexException {
        when(pokedex.addPokemon(any(Pokemon.class))).thenReturn(0).thenReturn(1);
        when(pokedex.size()).thenReturn(2);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        createdPokedex.addPokemon(pikachu);
        createdPokedex.addPokemon(bulbasaur);

        assertEquals(2, createdPokedex.size(), "Taille du Pokedex doit être 2 après ajout de deux Pokémons.");
    }

    @Test
    void testPokemonsSortedByName() {
        List<Pokemon> expectedOrder = Arrays.asList(bulbasaur, pikachu);
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(expectedOrder);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        List<Pokemon> sortedPokemons = createdPokedex.getPokemons(Comparator.comparing(Pokemon::getName));

        assertNotNull(sortedPokemons, "Liste triée des Pokémon ne doit pas être nulle.");
        assertEquals(2, sortedPokemons.size(), "Liste triée doit contenir deux éléments.");
        assertEquals("Bulbasaur", sortedPokemons.get(0).getName(), "Bulbasaur doit être premier.");
        assertEquals("Pikachu", sortedPokemons.get(1).getName(), "Pikachu doit être deuxième.");
    }
}
