package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Fournisseur de metadonnees Pokemon.
 *
 * Cette classe fournit les metadonnees des Pokemons
 * a partir de leur index.
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    /** Liste des metadonnees des Pokemons. */
    private List<PokemonMetadata> metadataList;

    /** Taille maximale de la liste des metadonnees. */
    private static final int SIZE = 151;

    /** Index du premier Pokemon dans la liste. */
    private static final int FIRST_INDX = 0;

    /** Index du dernier Pokemon dans la liste. */
    private static final int LAST_INDX = 150;

    /**
     * Initialise la liste des metadonnees des Pokemons.
     *
     * Les metadonnees de certains Pokemons specifiques.
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
     * Constructeur par defaut.
     *
     * Initialise le fournisseur de metadonnees des Pokemons.
     */
    public PokemonMetadataProvider() {
        initMetadataList();
    }

    /**
     * Obtient les metadonnees du Pokemon correspondant a l'index donne.
     *
     * @param indx L'index du Pokemon.
     * @return Les metadonnees du Pokemon.
     * @throws PokedexException Si l'index depasse les
     * limites de la liste ou si aucune metadonnee n'est trouvee.
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
