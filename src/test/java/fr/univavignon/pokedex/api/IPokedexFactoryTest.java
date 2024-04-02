package fr.univavignon.pokedex.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;

@ExtendWith(MockitoExtension.class)
public class IPokedexFactoryTest {

    @Mock
    private IPokemonMetadataProvider metadataProvider;

    @Mock
    private IPokemonFactory pokemonFactory;

    @Mock
    private PokedexFactory pokedexFactory;

    private IPokedex pokedex;
    private Pokemon herbizarre;
    private Pokemon bulbasaur;

    @BeforeEach
    void setUp() {
        pokedexFactory = new PokedexFactory();

        pokedex = mock(IPokedex.class, withSettings().lenient());
        herbizarre = new Pokemon(0, "Herbizarre", 60, 62, 63, 80, 80, 1500, 20, 70);
        bulbasaur = new Pokemon(1, "Bulbasaur", 45, 49, 45, 230, 30, 500, 50, 0.5);

        try {
            when(pokedex.addPokemon(any(Pokemon.class))).thenAnswer(i -> {
                Pokemon p = i.getArgument(0);
                return p.getIndex();
            });
            when(pokedex.getPokemon(0)).thenReturn(herbizarre);
            when(pokedex.getPokemons()).thenReturn(Arrays.asList(herbizarre, bulbasaur));
            when(pokedex.getPokemons(any(Comparator.class))).thenReturn(Arrays.asList(bulbasaur, herbizarre));
            doThrow(new PokedexException("invalid id !!!")).when(pokedex).getPokemon(-1);
        } catch (PokedexException e) {
            e.printStackTrace();
        }

    }

//test creation podex
    @Test
    void testCreatePokedex() {
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
        assertNotNull(createdPokedex, "le pokedex ne doit pas etre null !!!");

    }


    //test size apres ajout
    @Test
    void testPokedexSizeApresAjoutPokemons() throws PokedexException {

        when(pokedex.addPokemon(any(Pokemon.class))).thenReturn(0).thenReturn(1);
        when(pokedex.size()).thenReturn(2);

        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        createdPokedex.addPokemon(herbizarre);
        createdPokedex.addPokemon(bulbasaur);

        assertEquals(2, createdPokedex.size(), "la taille du podex doit etre 2!!!");


    }

    //test invalid index
    @Test
    void testGetPokemonInvalidIndexNegatifException() throws PokedexException {

        doThrow(new PokedexException("invalid id !!!")).when(pokedex).getPokemon(-1);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        assertThrows(PokedexException.class, () -> createdPokedex.getPokemon(-1),
                "l'acces à un index invalid doit lancer une exception!!!");
    }

    @Test
    void testGetPokemonInvalidIndexSup150Exception() throws PokedexException {

        doThrow(new PokedexException("invalid id !!!")).when(pokedex).getPokemon(159);
        IPokedex createdPokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);

        assertThrows(PokedexException.class, () -> createdPokedex.getPokemon(159),
                "l'acces à un index invalid doit lancer une exception!!!");
    }






}