package fr.univavignon.pokedex.api;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Comparator;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon pikachu;
    private Pokemon bulbasaur;

    @BeforeEach
    public void setup() {
        // Mock de l'interface IPokedex
        pokedex = mock(IPokedex.class);

        // Création de Pokémon avec le constructeur disponible
        pikachu = new Pokemon(0, "Pikachu", 55, 40, 90, 260, 35, 500, 50, 0.6);
        bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 45, 230, 30, 500, 50, 0.5);

        // Configuration des comportements simulés pour le mock de IPokedex
        try {
            when(pokedex.size()).thenReturn(2);
            when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(i -> {
                Pokemon p = i.getArgument(0);
                return p.getIndex(); // Retourne l'index simulé du Pokémon ajouté
            });
            when(pokedex.getPokemon(0)).thenReturn(pikachu);
            when(pokedex.getPokemons()).thenReturn(List.of(pikachu, bulbasaur));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(List.of(bulbasaur, pikachu));
            // Configuration pour lancer une exception PokedexException
            doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            // Exception gérée pour satisfaire le compilateur, non attendue en pratique ici
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPokemon() {
        // Test de l'ajout de Pokémon et vérification de l'index retourné
        int indexPikachu = pokedex.addPokemon(pikachu);
        assertEquals(0, indexPikachu);
    }

    @Test
    public void testGetSize() {
        // Test pour vérifier la taille du Pokedex
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        // Test pour récupérer un Pokémon par son index
        assertEquals(pikachu, pokedex.getPokemon(0));
    }

    @Test
    public void testGetPokemons() {
        // Test pour récupérer la liste de tous les Pokémon
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
    }

    @Test
    public void testGetPokemonThrowsException() {
        // Test pour vérifier le comportement lorsqu'un index invalide est utilisé
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));
    }

    @Test
    public void testGetPokemonsWithOrder() {
        // Test pour récupérer la liste de tous les Pokémon dans un ordre spécifique
        List<Pokemon> pokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertNotNull(pokemons);
        assertEquals("Bulbasaur", pokemons.get(0).getName());
        assertEquals("Pikachu", pokemons.get(1).getName());
    }
}
