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
    void testExceptionWhenAccessingPokemonWithInvalidIndex() {
        // Configurer le Pokédex pour lancer une exception lors de la tentative de récupération d'un Pokémon avec un index négatif
        when(pokedex.getPokemon(-1)).thenThrow(new PokedexException("Index out of bounds"));

        // Créer un Pokédex via la factory en utilisant les mocks préparés
        IPokedex testedPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Vérifier qu'une exception est correctement lancée pour un index invalide
        PokedexException exception = assertThrows(PokedexException.class, () -> testedPokedex.getPokemon(-1),
                "Un accès avec un index invalide devrait générer une PokedexException spécifiant que l'index est hors limites.");
        assertEquals("Index out of bounds", exception.getMessage(), "Le message de l'exception devrait indiquer que l'index est hors des limites permises.");
    }

    @Test
    void verifyPokedexCapacityIncreasesOnPokemonAddition() {
        // Simuler l'ajout de Pokémon au Pokédex pour qu'ils retournent des indices uniques
        when(pokedex.addPokemon(any(Pokemon.class))).thenReturn(0, 1); // Utilisation de thenReturn avec plusieurs valeurs pour simplifier
        // Définir la taille attendue du Pokédex après les ajouts
        when(pokedex.size()).thenReturn(2);

        // Créer un nouveau Pokédex à l'aide de la factory fournissant les dépendances mockées
        IPokedex testPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        // Ajouter deux Pokémon au Pokédex créé et vérifier que la taille reflète ces ajouts
        testPokedex.addPokemon(pikachu);
        testPokedex.addPokemon(bulbasaur);

        // Confirmer que la taille du Pokédex correspond au nombre de Pokémon ajoutés
        int expectedSize = 2;
        int actualSize = testPokedex.size();
        assertEquals(expectedSize, actualSize, "Le Pokédex devrait compter exactement 2 Pokémon suite aux ajouts.");
    }
    @Test
    void testPokemonsSortedByName() {
        List<Pokemon> expectedOrder = Arrays.asList(bulbasaur, herbizarre);
        when(pokedex.getPokemons(any(Comparator.class))).thenReturn(expectedOrder);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        List<Pokemon> sortedPokemons = createdPokedex.getPokemons(Comparator.comparing(Pokemon::getName));

        assertNotNull(sortedPokemons, "Liste triée des Pokémon ne doit pas être nulle.");
        assertEquals(2, sortedPokemons.size(), "Liste triée doit contenir deux éléments.");
        assertEquals("Bulbasaur", sortedPokemons.get(0).getName(), "Bulbasaur doit être premier.");
        assertEquals("Herbizarre", sortedPokemons.get(1).getName(), "Herbizarre doit être deuxième.");
    }


}
