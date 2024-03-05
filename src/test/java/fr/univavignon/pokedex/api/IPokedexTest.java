import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Comparator;

public class IPokedexTest {

    private IPokedex pokedex;
    private PokemonMetadataProvider metadataProvider;
    private PokemonFactory pokemonFactory;

    @BeforeEach
    public void setUp() {
        metadataProvider = mock(PokemonMetadataProvider.class);
        pokemonFactory = mock(PokemonFactory.class);
        pokedex = new Pokedex(metadataProvider, pokemonFactory);
    }

    @Test
    public void testAddPokemon() {
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        Pokemon bulbizarre = mock(Pokemon.class);
        when(pokemonFactory.createPokemon(metadata, 613, 64, 4000, 4, 0.56)).thenReturn(bulbizarre);

        int index = pokedex.addPokemon(0, 613, 64, 4000, 4, 0.56);
        assertEquals(0, index);
    }

    @Test
    public void testGetSize() {
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
        PokemonMetadata metadata = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(metadata);

        Pokemon bulbizarre = mock(Pokemon.class);
        when(pokemonFactory.createPokemon(metadata, 613, 64, 4000, 4, 0.56)).thenReturn(bulbizarre);

        pokedex.addPokemon(0, 613, 64, 4000, 4, 0.56);

        assertEquals(bulbizarre, pokedex.getPokemon(0));
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
        assertEquals("Aquali", pokemons.get(0).getName());
        assertEquals("Bulbizarre", pokemons.get(1).getName());
    }
}
