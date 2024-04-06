package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Pokemon metadata provider.
 *
 * This class provides metadata for Pokemons
 * based on their index.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    /** List of Pokemon metadata. */
    private List<PokemonMetadata> metadataList;

    /** Maximum size of the metadata list. */
    private static final int SIZE = 151;

    /** Index of the first Pokemon in the list. */
    private static final int FIRST_INDX = 0;

    /** Index of the last Pokemon in the list. */
    private static final int LAST_INDX = 150;

    /**
     * Initializes the list of Pokemon metadata.
     *
     * The metadata for some specific Pokemons.
     */
    private void initMetadataList() {
        PokemonMetadata bulbizarre = new
                PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata coherence = new
                PokemonMetadata(3, "Coherence", 127, 127, 91);
        PokemonMetadata aquali = new
                PokemonMetadata(133, "Aquali", 186, 168, 260);
        metadataList = new ArrayList<>(SIZE);

        for (int i = 0; i < SIZE; i++) {
            metadataList.add(null);
        }

        metadataList.set(0, bulbizarre);
        metadataList.set(3, coherence);
        metadataList.set(133, aquali);
    }

    /**
     * Default constructor.
     *
     * Initializes the Pokemon metadata provider.
     */
    public PokemonMetadataProvider() {
        initMetadataList();
    }

    /**
     * Fetches Pokemon metadata by index.
     *
     * @param indx The index of the Pokemon.
     * @return The metadata of the Pokemon.
     * @throws PokedexException If the index is out of the list's
     * bounds or if no metadata is found.
     */
    @Override
    public PokemonMetadata getPokemonMetadata(final int indx)
            throws PokedexException {
        if (indx < FIRST_INDX || indx > LAST_INDX) {
            throw new PokedexException(
                    "Attention: invalid index: " + indx + "!!!");
        }

        PokemonMetadata metadata = metadataList.get(indx);
        if (metadata == null) {
            throw new PokedexException("Attention: je ne trouve pas "
                    + "de metadonnees pour l'index " + indx + "!!!");
        }
        return metadata;
    }

}
