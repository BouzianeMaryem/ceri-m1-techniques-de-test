package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokemonMetadataProviderTest {
    private final PokemonMetadata bulbizarre =
            new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
    private final PokemonMetadata aquali = new PokemonMetadata(133, "Aquali", 186, 168, 260);
    private final IPokemonMetadataProvider pokemonMetadataProvider =
            new PokemonMetadataProvider();

    @Test
    void shouldThrowPokedexExceptionForInvalidIndex() {
        Exception lowerBoundException = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(-1);
        });
        assertEquals("Pokemon's indexes are between 0 and 150.", lowerBoundException.getMessage());

        Exception upperBoundException = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(151);
        });
        assertEquals("Pokemon's indexes are between 0 and 150.", upperBoundException.getMessage());
    }

    @Test
    void testPokemonMetadataAttributes() throws PokedexException {
        PokemonMetadata fetchedBulbizarre = pokemonMetadataProvider.getPokemonMetadata(0);
        assertEquals(bulbizarre.getIndex(), fetchedBulbizarre.getIndex());
        assertEquals(bulbizarre.getName(), fetchedBulbizarre.getName());
        assertEquals(bulbizarre.getAttack(), fetchedBulbizarre.getAttack());
        assertEquals(bulbizarre.getDefense(), fetchedBulbizarre.getDefense());
        assertEquals(bulbizarre.getStamina(), fetchedBulbizarre.getStamina());

        PokemonMetadata fetchedAquali = pokemonMetadataProvider.getPokemonMetadata(133);
        assertEquals(aquali.getIndex(), fetchedAquali.getIndex());
        assertEquals(aquali.getName(), fetchedAquali.getName());
        assertEquals(aquali.getAttack(), fetchedAquali.getAttack());
        assertEquals(aquali.getDefense(), fetchedAquali.getDefense());
        assertEquals(aquali.getStamina(), fetchedAquali.getStamina());
    }
}
