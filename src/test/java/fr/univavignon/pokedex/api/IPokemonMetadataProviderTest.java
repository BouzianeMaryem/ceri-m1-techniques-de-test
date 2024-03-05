package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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

        Mockito.when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(indexReal, nomReal, attaqueReal, defenseReal, enduranceReal));
        PokemonMetadata metadata2 = metadataProvider.getPokemonMetadata(133);
        //verification:
        assertNotNull(metadata2, "METADATA  ne doit pas être null !!!");
        assertEquals("Aquali", metadata2.getName(), "Le nom doit être : Aquali !!!");
        assertEquals(186, metadata2.getAttack(), " Aquali attack doit être : 186 !!!");
        assertEquals(168, metadata2.getDefense(), "Aquali defense doit être : 168 !!! ");
        assertEquals(260, metadata2.getStamina(), "Aquali endurance doit être : 260 !!!");
    }
    //pour que je teste un index Invalid
    @Test
    public void getPokemonMetadata_invalidIndex() throws PokedexException {

        when(metadataProvider.getPokemonMetadata(155)).thenThrow(new PokedexException("Invalid index"));


        int invalidIndex = 155;


        assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(invalidIndex);
        }, "A PokedexException should be thrown for an invalid index");
    }


}
