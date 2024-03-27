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

    @Test
    public void testAddPokemonNull() {
        assertEquals(-1, pokedex.addPokemon(null));
    }

    // test getpokemon()
    @Test
    public void testGetPokemon() throws PokedexException {
        assertEquals(pokedex.getPokemon(0), pokemonList.get(0));
        assertEquals(pokedex.getPokemon(0).getIndex(), bulbizarre.getIndex());
        assertEquals(pokedex.getPokemon(0).getName(), bulbizarre.getName());
        assertEquals(pokedex.getPokemon(0).getAttack(), bulbizarre.getAttack());
        assertEquals(pokedex.getPokemon(0).getDefense(), bulbizarre.getDefense());
        assertEquals(pokedex.getPokemon(0).getCp(), bulbizarre.getCp());
        assertEquals(pokedex.getPokemon(0).getCandy(), bulbizarre.getCandy());
        assertEquals(pokedex.getPokemon(0).getDust(), bulbizarre.getDust());
        assertEquals(pokedex.getPokemon(0).getHp(), bulbizarre.getHp());


    }
    //test getPokemon si index est invalid
    @Test
    public void testGetPokemonAvecIndexInvalid() throws PokedexException  {
        Exception invalidIndx = assertThrows(PokedexException.class, () -> {
            pokedex.getPokemon(-6);
        });

        assertEquals(invalidIndx.getMessage(), "invalid id !!!");

    }

//test getpokemons
    @Test
    public void testGetPokemons() {
        assertEquals(pokedex.getPokemons(), pokemonList);
        assertEquals(pokedex.getPokemons().size(), pokemonList.size());
    }

    @Test
    public void testCreatePokemon() throws PokedexException {

        Pokemon aquali = pokedex.createPokemon(2729, 202, 5000, 4);

        int aqualiIndx = pokedex.addPokemon(pokemon);

        Pokemon  aqualiInPodex = pokedex.getPokemon(aqualiIndx);
        int aqualiCp = aquali.getCp();
        int aqualiHp = aquali.getHp();
        int aqualiDust = aquali.getDust();
        int aqualiCandy = aquali.getCandy();


        assertEquals(aqualiInPodex, aquali);
        assertEquals(aqualiCp, 2729);
        assertEquals(aqualiHp, 202);
        assertEquals(aqualiDust, 5000);
        assertEquals(aqualiCandy, 4);
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