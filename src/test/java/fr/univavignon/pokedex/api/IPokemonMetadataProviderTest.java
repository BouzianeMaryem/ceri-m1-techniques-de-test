package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(133, "Aquali", 186, 168, 260));
        doThrow(new PokedexException("Invalid index")).when(metadataProvider).getPokemonMetadata(argThat(arg -> arg != 0 && arg != 133));
    }


    @Test
    public void getPokemonMetadata_AvecValidIndexBulbizarre() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals("Bulbizarre", metadata.getName());
    }

    // Test pour un index valide avec le Pokémon Aquali
    @Test
    public void getPokemonMetadata_AvecValidIndexAquali() throws PokedexException {
        int indexReal = 133;
        String nomReal = "Aquali";
        int attaqueReal = 186;
        int defenseReal = 168;
        int enduranceReal = 260;

        when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(indexReal, nomReal, attaqueReal, defenseReal, enduranceReal));
        PokemonMetadata metadata2 = metadataProvider.getPokemonMetadata(133);

        // Vérifications
        assertNotNull(metadata2, "METADATA ne doit pas être null !!!");
        assertEquals("Aquali", metadata2.getName(), "Le nom doit être : Aquali !!!");
        assertEquals(186, metadata2.getAttack(), "Aquali attack doit être : 186 !!!");
        assertEquals(168, metadata2.getDefense(), "Aquali defense doit être : 168 !!!");
        assertEquals(260, metadata2.getStamina(), "Aquali endurance doit être : 260 !!!");
    }
    //pour que je teste un index Invalid
      // sup à 150
    @Test
    public void getPokemonMetadata_invalidIndexSup150() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(155)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = 155;
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "si invalid indx lance PodexException");
    }
    // inf à 0
    @Test
    public void getPokemonMetadata_invalidIndexInfZero() throws PokedexException {
        when(metadataProvider.getPokemonMetadata(-6)).thenThrow(new PokedexException("Invalid index"));
        int invalidIndex = -6;
        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "si invalid indx lance PodexException");
    }
    @Test
    public void getPokemonMetadata_ValidIndex_ReturnsMetadata() throws PokedexException {
        PokemonMetadata BulbizarreMetadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(BulbizarreMetadata);
        assertEquals("Bulbizarre", BulbizarreMetadata.getName());

        PokemonMetadata AqualiMetadata = metadataProvider.getPokemonMetadata(133);
        assertNotNull(AqualiMetadata);
        assertEquals("Aquali", AqualiMetadata.getName());
    }

    @Test
    public void getPokemonMetadata_InvalidIndex_ThrowsException() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(151));
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(-1));
    }

    @Test
    public void getPokemonMetadata_UninitializedIndex_ThrowsException() {
        assertThrows(PokedexException.class, () -> metadataProvider.getPokemonMetadata(100));
    }

}
