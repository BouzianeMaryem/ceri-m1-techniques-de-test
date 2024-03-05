import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Comparator;

public class IPokedexTest {

    private Pokedex pokedex;
    private Pokemon pokemon1;
    private Pokemon pokemon2;

    @BeforeEach
    public void setUp() {
        pokedex = mock(Pokedex.class);

        pokemon1 = new Pokemon(0, "Pikachu", 55, 40, 90, 260, 35, 500, 50, 0.6);
        pokemon2 = new Pokemon(1, "Bulbasaur", 45, 49, 45, 230, 30, 500, 50, 0.5);

        try {
            when(pokedex.size()).thenReturn(2);
            when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(i -> {
                Pokemon p = i.getArgument(0);
                return p.getIndex();
            });
            when(pokedex.getPokemon(0)).thenReturn(pokemon1);
            when(pokedex.getPokemons()).thenReturn(List.of(pokemon1, pokemon2));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(List.of(pokemon2, pokemon1));
            doThrow(new PokedexException("Invalid index")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddPokemon() {
        int indexPikachu = pokedex.addPokemon(pokemon1);
        assertEquals(0, indexPikachu);
    }

    @Test
    public void testGetSize() {
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(pokemon1, pokedex.getPokemon(0));
    }

    @Test
    public void testGetPokemons() {
        List<Pokemon> pokemons = pokedex.getPokemons();
        assertNotNull(pokemons);
        assertEquals(2, pokemons.size());
    }

    @Test
    public void testGetPokemonThrowsException() {
        assertThrows(PokedexException.class, () -> pokedex.getPokemon(-1));
    }

    @Test
    public void testGetPokemonsWithOrder() {
        List<Pokemon> pokemons = pokedex.getPokemons(Comparator.comparing(Pokemon::getName));
        assertNotNull(pokemons);
        assertEquals("Bulbasaur", pokemons.get(0).getName());
        assertEquals("Pikachu", pokemons.get(1).getName());
    }
}
