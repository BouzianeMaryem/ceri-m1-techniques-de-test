package fr.univavignon.pokedex.api;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class IPokemonMetadataProviderTest {

    private final PokemonMetadata bulbizarre = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
    private final PokemonMetadata aquali = new PokemonMetadata(133, "Aquali", 186, 168, 260);
    private final IPokemonMetadataProvider pokemonMetadataProvider = new PokemonMetadataProvider();

    @Test
    public void getPokemonMetadata_AvecValidIndexBulbizarre() throws PokedexException {

        PokemonMetadata fetchedBulbizarre = pokemonMetadataProvider.getPokemonMetadata(0);

        assertEquals(bulbizarre.getIndex(), fetchedBulbizarre.getIndex());
        assertEquals(bulbizarre.getName(), fetchedBulbizarre.getName());
        assertEquals(bulbizarre.getAttack(), fetchedBulbizarre.getAttack());
        assertEquals(bulbizarre.getDefense(), fetchedBulbizarre.getDefense());
        assertEquals(bulbizarre.getStamina(), fetchedBulbizarre.getStamina());

    }

    // Test pour un index valide avec le Pokémon Aquali
    @Test
    public void getPokemonMetadata_AvecValidIndexAquali() throws PokedexException {

        PokemonMetadata fetchedAquali = pokemonMetadataProvider.getPokemonMetadata(133);

        assertEquals(aquali.getIndex(), fetchedAquali.getIndex());
        assertEquals(aquali.getName(), fetchedAquali.getName());
        assertEquals(aquali.getAttack(), fetchedAquali.getAttack());
        assertEquals(aquali.getDefense(), fetchedAquali.getDefense());
        assertEquals(aquali.getStamina(), fetchedAquali.getStamina());
    }

    //pour que je teste un index Invalid
    // sup à 150
    @Test
    public void getPokemonMetadata_invalidIndexSup150() throws PokedexException {

        Exception sup150Exception = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(155);
        });

        assertEquals("Attention: invalid index: 155!!!", sup150Exception.getMessage());
    }

    // inf à 0
    @Test
    public void getPokemonMetadata_invalidIndexInfZero() throws PokedexException {

        Exception InfZeroException = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(-6);
        });

        assertEquals("Attention: invalid index: -6!!!", InfZeroException.getMessage());

    }

    @Test
    public void getPokemonMetadata_invalidIndexNull() throws PokedexException {

        Exception IndxNullException = assertThrows(PokedexException.class, () -> {
            pokemonMetadataProvider.getPokemonMetadata(23);
        });

        assertEquals("Attention: je ne trouve pas de metadonnees pour l'index 23!!!", IndxNullException.getMessage());
    }

}
