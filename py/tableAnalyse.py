#!/usr/bin/env python
# coding: utf-8

import ReglesProductions_Premier_Suivant
from collections import defaultdict
from copy import deepcopy


def print_(tableAnalyse):
    # Fonction s'occupant de l'affichage du résultat

    ReglesProductions_Premier_Suivant.afficherRegles(regles)
    ReglesProductions_Premier_Suivant.afficherPremiers(premiers)
    ReglesProductions_Premier_Suivant.afficherSuivants(suivants)
    elementsTerminaux = set()

    for production in regles.keys():
        elementsTerminaux = elementsTerminaux.union(premiers[production])
        elementsTerminaux = elementsTerminaux.union(suivants[production])

    elementsTerminaux.discard('vide')
    print("\nÉléments non terminaux :\n", list(regles.keys()))
    print("\nÉléments terminaux :\n", list(elementsTerminaux))
    print("\nLa table d'analyse est :\n")

    for row, col in tableAnalyse.items():
        print(row, ':', col)

    return


def getTableAnalyse(premiers, suivants, regles):
    # Fonction récupérant la table d'analyse en données ainsi qu'en affichage

    tableAnalyse = defaultdict()
    tableAnalyseAffichee = defaultdict()  # Pour l'affichage
    for nonTerminal, regle in regles.items():
        for production in regle:
            symbole = production[0]
            if ReglesProductions_Premier_Suivant.estNonTerminal(symbole, regles):
                for ter in premiers[symbole] - {'vide'}:
                    tableAnalyse[nonTerminal, ter] = {nonTerminal: production}
                    tableAnalyseAffichee[nonTerminal, ter] = nonTerminal + '-> ' + ' '.join(i for i in production)

            elif symbole == "vide" or symbole in deepcopy(premiers[symbole]):
                for ter in suivants[nonTerminal]:
                    tableAnalyse[nonTerminal, ter] = {nonTerminal: ['vide']}
                    tableAnalyseAffichee[nonTerminal, ter] = nonTerminal + '-> ' + 'vide'
            else:
                tableAnalyse[nonTerminal, symbole] = {nonTerminal: production}
                tableAnalyseAffichee[nonTerminal, symbole] = nonTerminal + '-> ' + ' '.join(i for i in production)

    return tableAnalyse, tableAnalyseAffichee


def parser(p_table, etat_initial):
    acceptee = False
    expression = list(map(str, input("\nEntrez l'expression à analyser "
                                     "(Séparez chaque symbole par un espace et finissez par $)\n").split()))
    if expression[-1] != '$':
        print("\nVeuillez ajouter un '$' à la fin de votre expression.")
        return

    print("\nL'expression saisie est : ", expression)

    for i in range(len(expression)):
        if isinstance(expression[i], int):
            expression[i] = "int"
        if isinstance(expression[i], float):
            expression[i] = "float"

    # Initialisation de la pile
    pile = ['$', etat_initial]
    inp = 0
    while pile and expression[inp]:

        print(pile)
        if expression == ["main(){", "}", "$"]:
            acceptee = True
            break

        depiler = pile.pop()
        # On continue à dépiler tant que le sommet de la pile est vide
        while depiler == 'vide':
            depiler = pile.pop()

        # Le sommet de la pile est-il un élément terminal ?
        if depiler != expression[inp]:

            # L'entrée est-elle dans la table d'analyse ?
            if p_table.get((depiler, expression[inp])):

                # On émet en sortie la règle
                regle = p_table.get((depiler, expression[inp])).get(depiler)
                for x in range(len(regle)):
                    # On empile la règle à l'envers
                    pile.append(regle[-x - 1])
            else:
                print("\n Expression refusée, non générable par la grammaire")
                return
        else:
            # On passe au symbole suivant à analyser
            inp += 1
        if pile[0] == expression[inp]:
            acceptee = True
            break

    if acceptee:
        print("\nExpression acceptée, générable par la grammaire")
    else:
        print("\nExpression refusée, non générable par la grammaire")
    return


if __name__ == "__main__":
    regles, etat_initial = ReglesProductions_Premier_Suivant.getRegles()
    premiers = ReglesProductions_Premier_Suivant.getPremiers(regles)
    suivants = ReglesProductions_Premier_Suivant.getSuivants(regles, deepcopy(premiers), etat_initial)
    tableAnalyse, tableAnalyseAffichee = getTableAnalyse(deepcopy(premiers), deepcopy(suivants), regles)
    print_(tableAnalyseAffichee)
    parser(tableAnalyse, etat_initial)
