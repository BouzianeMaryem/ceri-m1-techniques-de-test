package fr.univavignon.pokedex.api;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
    void testInvalidParamsReturnNull() {
        Pokemon pokemonNegativeIndex = pokemonFactory.createPokemon(-1, 1632, 201, 4000, 5);
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getCp());
        assertThrows(NullPointerException.class, () -> pokemonNegativeIndex.getHp());

    }
}
