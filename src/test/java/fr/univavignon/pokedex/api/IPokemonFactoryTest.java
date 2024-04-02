package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
class IPokemonFactoryTest {
    static IPokemonFactory pokemonFactory;
    static Pokemon expectedBulbizarre;
    static Pokemon expectedAquali;
    @BeforeAll
    static void setUp() {
        pokemonFactory = new FactoryPokemon();
        expectedBulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 1);
        expectedAquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1);



    }


    @Test
    void testCreateBulbizarre() {
        Pokemon bulbizarre = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        Assertions.assertNotNull(bulbizarre);
        Assertions.assertEquals(expectedBulbizarre.getCp(), bulbizarre.getCp());
        Assertions.assertEquals(expectedBulbizarre.getHp(), bulbizarre.getHp());
        Assertions.assertEquals(expectedBulbizarre.getDust(), bulbizarre.getDust());
        Assertions.assertEquals(expectedBulbizarre.getCandy(), bulbizarre.getCandy());
        Assertions.assertEquals(expectedBulbizarre.getIv(), bulbizarre.getIv());
    }

    @Test
    void testCreateAquali() {
        Pokemon aquali = pokemonFactory.createPokemon(133, 2729, 202, 5000, 5);
        Assertions.assertNotNull(aquali);
        Assertions.assertEquals(expectedAquali.getCp(), aquali.getCp());
        Assertions.assertEquals(expectedAquali.getHp(), aquali.getHp());
        Assertions.assertEquals(expectedAquali.getDust(), aquali.getDust());
        Assertions.assertEquals(expectedAquali.getCandy(), aquali.getCandy());
        Assertions.assertEquals(expectedAquali.getIv(), aquali.getIv());
    }
    @Test
    void testInvalidAllParamsReturnNull() {

        Pokemon pokemonInvalidAllParams = pokemonFactory.createPokemon(-1000, -1000, -1000, -1000, -1000);

        assertNull(pokemonInvalidAllParams);

        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getHp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getDust());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getIv());

    }

    @Test
    void testInvalidNegativeIndxReturnNull() {

        Pokemon pokemonNegativeIndex = pokemonFactory.createPokemon(-1, 1632, 201, 4000, 5);

        assertNull(pokemonNegativeIndex);

        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getIv());

    }

    @Test
    void testInvalidIndxSup150ReturnNull() {

        Pokemon pokemonSupIndex = pokemonFactory.createPokemon(200, 1632, 201, 4000, 5);

        assertNull(pokemonSupIndex);


        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getIv());

    }

    @Test
    void verifierLaCoherenceDesMetadonneesPokemon() {

        int pokemonIndex = 1;
        int CP = 50;
        int HP = 50;
        int Dust = 500;
        int Candy = 5;
        Pokemon pokemonCree = pokemonFactory.createPokemon(pokemonIndex, CP, HP, Dust, Candy);

        assertAll("Vérification de la cohérence des métadonnées du Pokémon créé",
                () -> assertEquals(pokemonIndex, pokemonCree.getIndex()),
                () -> assertEquals(CP, pokemonCree.getCp()),
                () -> assertEquals(HP, pokemonCree.getHp()),
                () -> assertEquals(Candy, pokemonCree.getCandy()),
                () -> assertEquals(Dust, pokemonCree.getDust())
        );
    }

}