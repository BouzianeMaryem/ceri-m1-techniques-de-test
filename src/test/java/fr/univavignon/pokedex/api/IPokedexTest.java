package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import org.mockito.Mockito;
public class IPokedexTest {

    private IPokedex pokedex;
    private IPokemonMetadataProvider;
    private IPokemonFactory ;
    private List<Pokemon> pokemonList ;

    private Pokemon bulbizarre;
    private Pokemon herbizarre;
    @BeforeEach
    public void setUp() throws PokedexException {
        pokemonMetadataProvider = new PokemonMetadataProvider();
        pokemonFactory = new PokemonFactory();
        pokedex = new Pokedex(pokemonMetadataProvider, pokemonFactory);
        pokemonList = new ArrayList<>();
        bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
        herbizarre = new Pokemon(1, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);

        pokedex.addPokemon(bulbizarre);
        pokemonList.add(bulbizarre);

    }
// test size
    @Test
    public void testSize() {
        assertEquals(pokedex.size(), pokemonList.size());
    }
// test add pokemon
    @Test
    public void testAddPokemon() {

        // Test de l'ajout de Bulbizarre
        assertEquals(pokedex.addPokemon(bulbizarre), pokemonList.size());
        pokemonList.add(bulbizarre);

        // Test de l'ajout de Herbizarre
        assertEquals(pokedex.addPokemon(herbizarre), pokemonList.size());
        pokemonList.add(herbizarre);

    }
    //test on peut pas ajouter des pokemons si la liste est full
    // c à d : on doit pas dépasser 151 pokemons
    @Test
    public void testCannotAddPokemonWhenPokedexIsFull() {

    }


    // test getpokemon()
    @Test
    public void testGetPokemon() throws PokedexException {

    }
    //test getPokemon si index est invalid
    @Test
    public void testGetPokemonAvecIndexInvalid() throws PokedexException  {

    }

//test getpokemons
    @Test
    public void testGetPokemons() {
        assertEquals(pokedex.getPokemons(), pokemonList);
        assertEquals(pokedex.getPokemons().size(), pokemonList.size());
    }

    //test order
    //et aussi PokemonComparators

    //test par nom

    @Test
    public void testGetPokemonsOrderParNom() {
        pokemonList.add(aquali);
        pokedex.addPokemon(aquali);
        assertEquals(pokemonList, pokedex.getPokemons(PokemonComparators.INDEX));
        ArrayList<Pokemon> pokemonsOrderedByName = new ArrayList<>();
        pokemonsOrderedByName.add(aquali);
        pokemonsOrderedByName.add(bulbizarre);
        assertEquals(pokemonsOrderedByName, pokedex.getPokemons(PokemonComparators.NAME));
    }


// test par index

    @Test
    public void testGetPokemonsOrderParIndex() {

    }


    // test par CP

    @Test
    public void testGetPokemonsOrderParCP()  {

    }

}