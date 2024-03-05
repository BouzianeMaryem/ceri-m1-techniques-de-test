package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class IPokedexTest {

    private IPokedex pokedex;
    private Pokemon bulbizarre;
    private Pokemon herbizarre;

    @BeforeEach
    public void setUp() throws PokedexException{
        //  mock IPokedex
        pokedex = mock(IPokedex.class);

        // creation de bulbizarre et herbizarre
        bulbizarre = new Pokemon(1, "Bulbizarre", 45, 49, 49, 65, 65, 1000, 10, 60);
        herbizarre = new Pokemon(2, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        when(pokedex.size()).thenReturn(2);

        // pokemon celon son index
        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedex.getPokemon(1)).thenReturn(herbizarre);

        //la liste avec tous le spokemons
        when(pokedex.getPokemons()).thenReturn(List.of(bulbizarre, herbizarre));

        // tri d'ordre pour les pokemons
        Comparator<Pokemon> comparator = Comparator.comparing(Pokemon::getName);
        when(pokedex.getPokemons(comparator)).thenReturn(List.of(bulbizarre, herbizarre));
    }
// test pour size
    @Test
    public void testSize() {
        assertEquals(2, pokedex.size());
    }
}