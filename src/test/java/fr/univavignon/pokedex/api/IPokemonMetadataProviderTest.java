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
       // je teste les metadonnees suivantes:
       int indexReal = 133;
       String nomReal = "Aquali";
       int attaqueReal = 186;
       int defenseReal = 168;
       int enduranceReal = 260;

       PokemonMetadata metadataAquiliReal = new PokemonMetadata(indexReal, nomReal, attaqueReal, defenseReal, enduranceReal);
       when(metadataProvider.getPokemonMetadata(indexReal)).thenReturn(metadataAquiliReal);

       PokemonMetadata metadataAquiliTest = metadataProvider.getPokemonMetadata(indexReal);

       // Vérification
       assertNotNull(metadataAquiliTest, "Les metadonnes ne doivent pas être nulles !!!");
       assertEquals(indexReal, metadataAquiliTest.getIndex(), "L'index  est incorrect !!!");
       assertEquals(nomReal, metadataAquiliTest.getName(), "Le nom est incorrect !!!");
       assertEquals(attaqueReal, metadataAquiliTest.getAttack(), "La valeur d'attaque est incorrecte!!!");
       assertEquals(defenseReal, metadataAquiliTest.getDefense(), "La valeur de défense  est incorrecte !!!");
       assertEquals(enduranceReal, metadataAquiliTest.getStamina(), "La valeur d'endurance est incorrecte !!!");
   }


}
