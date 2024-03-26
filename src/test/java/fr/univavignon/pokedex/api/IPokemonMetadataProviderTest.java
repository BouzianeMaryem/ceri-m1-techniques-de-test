package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        when(metadataProvider.getPokemonMetadata(150)).thenReturn(new PokemonMetadata(150, "lastPok", 100, 100, 100));
        when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));
        when(metadataProvider.getPokemonMetadata(155)).thenThrow(new PokedexException("Invalid index"));
        when(metadataProvider.getPokemonMetadata(-1)).thenThrow(new PokedexException("Invalid index"));
    }

    @Test
    public void getPokemonMetadata_ValidIndexBulbizarre() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals("Bulbizarre", metadata.getName());
    }

    @Test
    public void getPokemonMetadata_ValidIndexAquali() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(133);
        assertNotNull(metadata);
        assertEquals("Aquali", metadata.getName());
    }

    @Test
    public void getPokemonMetadata_InvalidIndexAboveRange() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(155));
    }

    @Test
    public void getPokemonMetadata_InvalidIndexBelowRange() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(-1));
    }

    @Test
    public void getPokemonMetadata_ValidIndexlastPok() throws PokedexException {
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(150);
        assertNotNull(metadata);
        assertEquals("lastPok", metadata.getName());
    }
}
