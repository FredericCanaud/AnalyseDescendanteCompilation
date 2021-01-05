<h1 align="center"> Analyseur syntaxique descendant de grammaire LL(1) </h1> 

<p align="center"><strong> Réalisation d'un analyseur syntaxique pour une grammaire donnée</strong></p>

>## Technologies utilisées
- Java (WIP)
- Python (Working :D)

>## Fonctionnalités

- Calcul des ensembles premiers des éléments non terminaux
- Calcul des ensembles suivants des éléments non terminaux
- Calcul de la table d'analyse de la grammaire fournie
- Déterminer si la grammaire est LL(1) ou non
- Déterminer si une chaîne est acceptée par la grammaire

>## Sujet
<p align="center">Le but du projet était de réaliser un analyseur syntaxique pour une grammaire d'un mini
du langage C. <br>
L'analyseur prend en entrée une chaine (programme) et devra retourner l'arbre
syntaxique (liste des règles de grammaires) correspondant à ce programme. <br>
Nous devions vérifier (manuellement) que la grammaire ci dessous, est bien LL(1),
afin de nous assurer du caractère déterministe de l'analyseur à construire.

>## Grammaire du Langage

<p align="center"><strong> Programme </strong> ::=main() {liste_declarations liste_instructions} <br>
<strong> liste_declarations </strong> ::= une_declaration liste_declarations | vide <br>
<strong> une_declaration </strong> ::= type id <br>
<strong> liste_instructions </strong> ::= une_instruction liste_instructions | vide <br>
<strong> une_instruction </strong> ::= affectation | test <br>
<strong> type </strong> ::= int | float <br>
<strong> affectation </strong> ::= id=nombre; <br>
<strong> test </strong> ::= if condition instruction else instruction> <br>
<strong> condition </strong> ::= id operateur nombre <br>
<strong> operateur </strong> ::= < | > | = <br><br> 

<i> Remarque : id et nombre sont des terminaux. </i><br></p>

>## Liens utiles

>#### Rapport de notre projet > [ProjetCompilation.pdf](./ProjetCompilation.pdf) <br>
>#### Sujet du projet > [SujetProjetCompilation.pdf](./SujetProjetCompilation.pdf)