package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class IPokemonMetadataProviderTest {

    private IPokemonMetadataProvider metadataProvider;

    @BeforeEach
    public void setUp() {
        metadataProvider = Mockito.mock(IPokemonMetadataProvider.class);
    }
//pour que je teste un index Valid
    //avec le pokemon Bulbizarre
    @Test
    public void getPokemonMetadata_AvecValidIndexBulbizarre() throws PokedexException {
        Mockito.when(metadataProvider.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
        PokemonMetadata metadata = metadataProvider.getPokemonMetadata(0);
        assertNotNull(metadata);
        assertEquals("Bulbizarre", metadata.getName());
    }
    //avec le pokemon Aquali
    // ici, je teste  plus de parametres
    @Test
    public void getPokemonMetadata_AvecValidIndexAquali() throws PokedexException {
        int indexReal = 133;
        String nomReal = "Aquali";
        int attaqueReal = 186;
        int defenseReal = 168;
        int enduranceReal = 260;

        PokemonMetadata metadataAqualiReal = new PokemonMetadata(indexReal, nomReal, attaqueReal, defenseReal, enduranceReal);
        when(metadataProvider.getPokemonMetadata(indexReal)).thenReturn(metadataAqualiReal);

        PokemonMetadata metadataAqualiTest = metadataProvider.getPokemonMetadata(indexReal);

        assertNotNull(metadataAqualiTest);
        assertEquals(indexReal, metadataAqualiTest.getIndex());
        assertEquals(nomReal, metadataAqualiTest.getName());
        assertEquals(attaqueReal, metadataAqualiTest.getAttack());
        assertEquals(defenseReal, metadataAqualiTest.getDefense());
        assertEquals(enduranceReal, metadataAqualiTest.getStamina());
    }

}
