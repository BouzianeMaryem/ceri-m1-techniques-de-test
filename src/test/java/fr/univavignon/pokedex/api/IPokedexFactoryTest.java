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
    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    void setUp() {
        // Préparation des mocks
        pokedex = mock(IPokedex.class);
        // Correction: Déclaration correcte des Pokémon dans la méthode setUp
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        doReturn(pokedex).when(pokedexFactory).createPokedex(metadataProvider, pokemonFactory);

        // Configurer les mocks pour utiliser les Pokémon déclarés
        try {
            when(pokedex.addPokemon(bulbizarre)).thenReturn(0);
            when(pokedex.addPokemon(herbizarre)).thenReturn(1);
            when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
            when(pokedex.getPokemon(1)).thenReturn(herbizarre);
            when(pokedex.getPokemons()).thenReturn(Arrays.asList(bulbizarre, herbizarre));
            doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
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
        // Mise à jour pour utiliser les variables locales bulbizarre et herbizarre
        when(pokedex.size()).thenReturn(2);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        createdPokedex.addPokemon(bulbizarre);
        createdPokedex.addPokemon(herbizarre);

        assertEquals(2, createdPokedex.size(), "Taille du Pokedex doit être 2 après ajout de deux Pokémons.");
    }

    @Test
    void testPokemonsSortedByName() {
        // Utilisation correcte des variables locales bulbizarre et herbizarre dans le test
        List<Pokemon> expectedOrder = Arrays.asList(bulbizarre, herbizarre);
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(expectedOrder);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        List<Pokemon> sortedPokemons = createdPokedex.getPokemons(Comparator.comparing(Pokemon::getName));

        assertNotNull(sortedPokemons, "Liste triée des Pokémon ne doit pas être nulle.");
        assertEquals(2, sortedPokemons.size(), "Liste triée doit contenir deux éléments.");
        assertEquals("Bulbizarre", sortedPokemons.get(0).getName(), "Bulbizarre doit être premier.");
        assertEquals("Herbizarre", sortedPokemons.get(1).getName(), "Herbizarre doit être deuxième.");
    }


}
