package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BulbizarreTest {
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
    void ShouldReturnNull() {
        Assertions.assertNull(pokemonFactory.createPokemon(-1, 0, 0, 0, 0));
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
}
