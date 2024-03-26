package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private List<PokemonMetadata> metadataList;

    private static final int firstIndx = 0;
    private static final int lastIndx = 150;

    public PokemonMetadataProvider() {
        this.metadataList = new ArrayList<>;
    }

    @Override
    public PokemonMetadata getPokemonMetadata(int indx) throws PokedexException {
        if (indx < firstIndx || indx > lastIndx) {
            throw new PokedexException("Attention: invalid index: " + indx + "!!!");
        }

        PokemonMetadata metadata = metadataList.get(indx);
        if (metadata == null) {
            throw new PokedexException("Attention: je ne trouve pas de metadonnees pour l'index " + indx + "!!!");
        }
        return metadata;
    }
}
