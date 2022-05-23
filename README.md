# Kata tondeuse

## Description

La société MowItNow a décidé de développer une tondeuse à gazon automatique,
destinée aux surfaces rectangulaires.

La tondeuse peut être programmée pour parcourir l'intégralité de la surface. La
position de la tondeuse est représentée par une combinaison de coordonnées (x,y)
et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S).
La pelouse est divisée en grille pour simplifier la navigation.

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle
se situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres
possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à
droite ou à gauche respectivement, sans la déplacer. « A » signifie que l'on avance
la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier
son orientation.

Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge
pas, conserve son orientation et traite la commande suivante.

## Les scénarios couvert

- Mouvement autorisé de la tondeuse dans le jardin
- Aucun mouvement autorisé quand il n'y a pas de pelouse
- Exception levée quand la limite du jardin a été atteinte par la tondeuse
- Exception levée si la dimension du jardin est négative
- Parsing du fichier de configuration

## Structure du projet

Main : 
- com.mowitnow.mower.domain : package domaine métier
- com.mowitnow.mower.infrastructure : package dépendante de l'infrastructure pour toutes les autres couches

Test : contient tous les scénarios de tests 

## Lancer les tests

La commande à lancer pour les tests dans un terminal : 

    gradle test

## Tests unitaires sur les mowers

La classe qui permet de tester unitairement les movements d'une tondeuse :

    MowerMovementTest.java

