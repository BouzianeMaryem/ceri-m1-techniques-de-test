# Techniques de Test - M1 IA Alt
## Informations :

- **Nom** : Bouziane
- **Prénom** : Maryem
- **Groupe** : M1 IA Alt


## Badges :
- **CircleCI** : 
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/BouzianeMaryem/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/BouzianeMaryem/ceri-m1-techniques-de-test/tree/master)

- **Codecov** : 
[![codecov](https://codecov.io/gh/BouzianeMaryem/ceri-m1-techniques-de-test/graph/badge.svg?token=6WQJWMQFOP)](https://codecov.io/gh/BouzianeMaryem/ceri-m1-techniques-de-test)

- **checkstyle** : 
![Checkstyle result](badges/checkstyle-result.svg)
- **JavaDoc** :
[![JavaDoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://bouzianemaryem.github.io/ceri-m1-techniques-de-test/)

## technologies :
- **Maven** : Maven pour la gestion des dépendances .
- **CircleCI** :  pour la validation des tests.
- **JUnit/Mockito** : pour les tests unitaires.
- **JaCoCo** : pour les rapports de couverture de tests et de code, codecove.

## Implementations :

### Pokedex :
- **Classe Pokedex** :
  <br>
Implémente l'interface IPokedex, servant de répertoire pour stocker et interagir avec les informations des Pokémon. Elle utilise des listes pour gérer les données et les métadonnées des Pokémon.
  
### PokedexFactory :
- **Classe PokedexFactory** :
  <br>
Implémente IPokedexFactory, fournissant une méthode de fabrique pour créer des instances de Pokedex. Cette approche modulaire facilite l'extension et la maintenance du système.

### FactoryPokemon :
- **Classe FactoryPokemon** :
  <br>
Implémente IPokemonFactory, chargée de la création des objets Pokemon avec leurs caractéristiques de base et des attributs calculés comme les points de combat (CP) et les points de vie (HP).

### PokemonMetadataProvider :
- **ClassePokemonMetadataProvider** :
  <br>
Implémente IPokemonMetadataProvider, s'occupant de la fourniture des métadonnées pour les Pokémon. Les métadonnées incluent des informations essentielles telles que le nom, l'attaque, la défense et l'endurance des Pokémon.

### PokemonTrainerFactory :
- **ClassePokemonTrainerFactory** :
  <br>
Implémente IPokemonTrainerFactory, permettant de créer des instances de PokemonTrainer. Cette classe utilise les fabricants IPokedexFactory, IPokemonMetadataProvider et IPokemonFactory pour assembler un PokemonTrainer complet avec son propre Pokedex.

