# Techniques de Test - M1 IA Alt
___
## Informations :

- **Nom** : Bouziane
- **Prénom** : Maryem
- **Groupe** : M1 IA Alt

___
## Badges :
- **CircleCI** : 
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/BouzianeMaryem/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/BouzianeMaryem/ceri-m1-techniques-de-test/tree/master)

- **Codecov** : 
[![codecov](https://codecov.io/gh/BouzianeMaryem/ceri-m1-techniques-de-test/graph/badge.svg?token=6WQJWMQFOP)](https://codecov.io/gh/BouzianeMaryem/ceri-m1-techniques-de-test)

- **checkstyle** : 
![Checkstyle result](badges/checkstyle-result.svg)
- **JavaDoc** :
[![JavaDoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://bouzianemaryem.github.io/ceri-m1-techniques-de-test/)
___
___
## technologies :
- **Maven** :
  <br>
  Maven pour la gestion des dépendances. On utilise **pom.xml** pour cela.
***
- **CircleCI** :
  <br>
  On utilise CircleCI pour vérifier que les tests marchent bien avec le reste du code. Il regarde si les tests passent et les valident, et dans notre cas, ils passent tous, donc on a le **badge** "**Passed**".
***
- **JUnit/Mockito** :
  <br>
  Pour les tests unitaires, j'utilise JUnit et Mockito. JUnit sert à exécuter les tests, tandis que Mockito permet de simuler des parties du code pour faciliter les tests. Cela m'a aidé à **tester** des interfaces ou des classes précises avant les implementer.
***
- **Codecov** :
  <br>
  Codecov est un outil qui mesure quelle partie de notre code est **testée** par nos tests automatiques, montrant un pourcentage de couverture. Notre implémentation a un **badge Codecov** à **100%**, indiquant que tout notre code et toutes les parties de l'implémentation sont **testées**. Pour Java, nous utilisons JaCoCo pour cette mesure.
***
- **Checkstyle** :
  <br>
  Checkstyle vérifie notre code pour s'assurer qu'il respecte de bonnes pratiques de codage. Nous avons choisi **les règles de Google** pour vérifier la qualité de notre code. Après avoir corrigé les problèmes identifiés par Checkstyle, nous avons réussi à obtenir un **badge** montrant **0 erreur** Checkstyle.
***
- **JavaDoc** :
  <br>
  JavaDoc génère automatiquement de la documentation pour notre code. En cliquant sur le **badge JavaDoc**, vous serez **redirigé vers le lien en ligne** de la documentation générée automatiquement pour notre implémentation.




___
___
## Implementations :

- **Classe Pokedex** :
  <br>
Implémente l'interface IPokedex, servant de répertoire pour stocker et interagir avec les informations des Pokémon. Elle utilise des listes pour gérer les données et les métadonnées des Pokémon.

- **Classe PokedexFactory** :
  <br>
Implémente IPokedexFactory, fournissant une méthode de fabrique pour créer des instances de Pokedex. Cette approche modulaire facilite l'extension et la maintenance du système.

- **Classe FactoryPokemon** :
  <br>
Implémente IPokemonFactory, chargée de la création des objets Pokemon avec leurs caractéristiques de base et des attributs calculés comme les points de combat (CP) et les points de vie (HP).

- **ClassePokemonMetadataProvider** :
  <br>
Implémente IPokemonMetadataProvider, s'occupant de la fourniture des métadonnées pour les Pokémon. Les métadonnées incluent des informations essentielles telles que le nom, l'attaque, la défense et l'endurance des Pokémon.

- **ClassePokemonTrainerFactory** :
  <br>
Implémente IPokemonTrainerFactory, permettant de créer des instances de PokemonTrainer. Cette classe utilise les fabricants IPokedexFactory, IPokemonMetadataProvider et IPokemonFactory pour assembler un PokemonTrainer complet avec son propre Pokedex.

