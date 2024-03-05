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
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

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
    //test pour add
    @Test
    public void testAddPokemon() {
        //ajout bulbizarre
        int idxBulbizarre = pokedex.addPokemon(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56));
        assertEquals(0, idxBulbizarre);
        // ajout herbizarre
        int idxHerbizarre = pokedex.addPokemon(new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70));
        assertEquals(1, idxAquali);
    }
    //test getpokemon
    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(bulbizarre, pokedex.getPokemon(0));
        assertEquals(herbizarre, pokedex.getPokemon(1));
    }

}