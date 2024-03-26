package fr.univavignon.pokedex.api;

import java.util.ArrayList;
import java.util.List;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {

    private List<PokemonMetadata> metadataList;

    private static final int SIZE = 151;
    private static final int firstIndx = 0;
    private static final int lastIndx = 150;

    private void initMetadataList() {
        PokemonMetadata bulbizarre = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
        PokemonMetadata aquali = new PokemonMetadata(133, "Aquali", 186, 168, 260);

        for (int i = 0; i < SIZE; i++) {
            PokemonMetadata pokemonNull = new PokemonMetadata(i, "null", 0, 0, 0);
            metadataList.add(pokemonNull);
        }

        metadataList.set(0, bulbizarre);
        metadataList.set(133, aquali);
    }

    public PokemonMetadataProvider() {
        this.metadataList = new ArrayList<>(SIZE);
        initMetadataList();
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
