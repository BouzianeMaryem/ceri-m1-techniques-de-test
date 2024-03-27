# Techniques de Test - M1 IA Alt
## Informations :

- **Nom** : Bouziane
- **Prénom** : Maryem
- **Groupe** : M1 IA Alt

## Badge codecove :
[![codecov](https://codecov.io/gh/BouzianeMaryem/ceri-m1-techniques-de-test/graph/badge.svg?token=6WQJWMQFOP)](https://codecov.io/gh/BouzianeMaryem/ceri-m1-techniques-de-test)
## Badge CircleCI :

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/BouzianeMaryem/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/BouzianeMaryem/ceri-m1-techniques-de-test/tree/master)
## Implementations Doc :

### `Pokedex`
- **Classe `Pokedex`** : Implémente l'interface `IPokedex`, servant de répertoire pour stocker et interagir avec les informations des Pokémon. Elle utilise des listes pour gérer les données et les métadonnées des Pokémon.
- **Dépendances** :
    - `IPokemonMetadataProvider` : Offre l'accès aux métadonnées des Pokémon.
    - `IPokemonFactory` : Utilisée pour créer de nouvelles instances de Pokémon.