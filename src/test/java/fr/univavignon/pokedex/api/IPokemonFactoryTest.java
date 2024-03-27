package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IPokemonFactoryTest {
    IPokemonFactory pokemonFactory = new PokemonFactory();
    Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

    Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 5, 1000);


    @Test
    void ShouldReturnNull(){
        assertNull(pokemonFactory.createPokemon(-1, 0, 0, 0, 0));
    }
    @Test
    void ShouldRetunRightCp() {
        assertEquals(bulbizarre.getCp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCp());
        assertEquals(aquali.getCp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getCp());
        assertNotEquals(aquali.getCp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCp());
    }

    @Test
    void shouldReturnTheRightHp() {
        assertEquals(bulbizarre.getHp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getHp());
        assertEquals(aquali.getHp(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getHp());
        assertNotEquals(aquali.getHp(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getHp());
    }

    @Test
    void shouldReturnRightDust() {
        assertEquals(bulbizarre.getDust(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getDust());
        assertEquals(aquali.getDust(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getDust());
        assertNotEquals(aquali.getDust(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getDust());
    }

    @Test
    void shouldRturnRightCandy() {
        assertEquals(bulbizarre.getCandy(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCandy());
        assertEquals(aquali.getCandy(), pokemonFactory.createPokemon(133, 2729, 202, 5000, 5).getCandy());
        assertNotEquals(aquali.getCandy(), pokemonFactory.createPokemon(0, 613, 64, 4000, 4).getCandy());
    }


}