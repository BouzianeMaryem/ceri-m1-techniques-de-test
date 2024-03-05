package fr.univavignon.pokedex.api;
import static org.mockito.ArgumentMatchers.argThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
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
    public void getPokemonMetadata_IndexInvalideTest() throws PokedexException {
        // mockito pour lancer PokedexException si idx >150 ou > 0
        Mockito.when(metadataProvider.getPokemonMetadata(argThat(idx -> idx < 0 || idx > 150)))
                .thenThrow(new PokedexException("Index invalide"));

        // Test pour idx > 150
        int idxSuperieur = 155;
        Exception exptSup = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(idxSuperieur);
        }, "si l'index > 150 : PokedexException doit être lancée !!!");
        assertTrue(exptSup.getMessage().contains("Index invalide"),
                "Le message pour idx sup doit contenir 'Index invalide'");

        // Test pour idx < 0
        int idxInferieur = -1;
        Exception exptInf = assertThrows(PokedexException.class, () -> {
            metadataProvider.getPokemonMetadata(idxInferieur);
        }, "si l'index < 0 : PokedexException doit être lancée !!!");
        assertTrue(exptInf.getMessage().contains("Index invalide"),
                "Le message pour idx inf doit contenir 'Index invalide'");
    }



}
