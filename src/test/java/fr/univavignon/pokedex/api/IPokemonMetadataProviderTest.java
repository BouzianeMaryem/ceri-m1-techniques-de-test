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
    public void getPokemonMetadata_AvecValidIndexBulbizarre() throws PokedexException {
        // je teste les metadonnees suivantes:
        int indexReal = 133;
        String nomReal = "Aquali";
        int attaqueReal = 186;
        int defenseReal = 168;
        int enduranceReal = 260;

        // Configuration de mock pour Aquali
        Mockito.when(metadataProvider.getPokemonMetadata(133)).thenReturn(new PokemonMetadata(indexReal, nomReal, attaqueReal, defenseReal, enduranceReal));

        PokemonMetadata metadataAquili = metadataProvider.getPokemonMetadata(133);

        // Vérification
        assertNotNull(metadataAquili, "Les metadonnes ne doivent pas être nulles !!!");
        assertEquals(indexReal, metadataAquili.getIndex(), "L'index est incorrect !!!");
        assertEquals(nomReal, metadataAquili.getName(), "Le nom est incorrect !!!");
        assertEquals(attaqueReal, metadataAquili.getAttack(), "La valeur d'attaque est incorrecte!!!");
        assertEquals(defenseReal, metadataAquili.getDefense(), "La valeur de défense est incorrecte !!!");
        assertEquals(enduranceReal, metadataAquili.getStamina(), "La valeur d'endurance est incorrecte !!!");
    }


}
