package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
class RocketPokemonFactoryTest {


    static IPokemonFactory rocketpokemonFactory;
    static Pokemon expectedBulbizarre;
    static Pokemon expectedAquali;
    static Pokemon expectedCoherence;


    @BeforeAll
    static void setUp() {
        rocketpokemonFactory = new RocketPokemonFactory();
        expectedBulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 1);
        expectedAquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1);
        expectedCoherence = new Pokemon(3, "Coherence", 127, 127, 91, 614, 65, 4001, 4, 1);



    }

    @Test
    void testCreateBulbizarreRocket() {
        Pokemon bulbizarre = rocketpokemonFactory.createPokemon(0, 613, 64, 4000, 4);
        Assertions.assertNotNull(bulbizarre);
        Assertions.assertEquals(expectedBulbizarre.getCp(), bulbizarre.getCp());
        Assertions.assertEquals(expectedBulbizarre.getHp(), bulbizarre.getHp());
        Assertions.assertEquals(expectedBulbizarre.getDust(), bulbizarre.getDust());
        Assertions.assertEquals(expectedBulbizarre.getCandy(), bulbizarre.getCandy());
        Assertions.assertEquals(expectedBulbizarre.getIv(), bulbizarre.getIv());
    }


    @Test
    void testCreateAqualiRocket() {
        Pokemon aquali = rocketpokemonFactory.createPokemon(133, 2729, 202, 5000, 5);
        Assertions.assertNotNull(aquali);
        Assertions.assertEquals(expectedAquali.getCp(), aquali.getCp());
        Assertions.assertEquals(expectedAquali.getHp(), aquali.getHp());
        Assertions.assertEquals(expectedAquali.getDust(), aquali.getDust());
        Assertions.assertEquals(expectedAquali.getCandy(), aquali.getCandy());
        Assertions.assertEquals(expectedAquali.getIv(), aquali.getIv());
    }
/*
    @Test
    void testInvalidAllParamsReturnNullRocket() {

        Pokemon pokemonInvalidAllParams = rocketpokemonFactory.createPokemon(-1000, -1000, -1000, -1000, -1000);

        Assertions.assertNull(pokemonInvalidAllParams);

        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getHp());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getDust());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonInvalidAllParams.getIv());

    }


    @Test
    void testInvalidNegativeIndxReturnNullRocket() {

        Pokemon pokemonNegativeIndex = rocketpokemonFactory.createPokemon(-1, 1632, 201, 4000, 5);

        Assertions.assertNull(pokemonNegativeIndex);

        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getIv());

    }

    @Test
    void testInvalidIndxSup150ReturnNullRocket() {

        Pokemon pokemonSupIndex = rocketpokemonFactory.createPokemon(200, 1632, 201, 4000, 5);

        Assertions.assertNull(pokemonSupIndex);


        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getHp());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getDust());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getCandy());
        assertThrows(NullPointerException.class, () -> pokemonSupIndex.getIv());

    }

*/

    @Test
    void verifierLaCoherenceDesMetadonneesPokemonRocket() {

        Pokemon testCo = rocketpokemonFactory.createPokemon(3, 614, 65, 4001, 4);

        Assertions.assertNotNull(testCo);

        Assertions.assertEquals(expectedCoherence.getCp(), testCo.getCp());
        Assertions.assertEquals(expectedCoherence.getHp(), testCo.getHp());
        Assertions.assertEquals(expectedCoherence.getDust(), testCo.getDust());
        Assertions.assertEquals(expectedCoherence.getCandy(), testCo.getCandy());
        Assertions.assertEquals(expectedCoherence.getIv(), testCo.getIv());
    }
}