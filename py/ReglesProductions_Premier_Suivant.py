#!/usr/bin/env python
# coding: utf-8

def getRegles():
    dic = {
        "Programme": [["main(){", "liste_declarations", "liste_instructions", "}"]],
        "liste_declarations": [["une_declaration", "liste_declarations"], ["vide"]],
        "une_declaration": [["type", "id"]],
        "liste_instructions": [["une_declaration", "liste_instructions"], ["vide"]],
        "une_instruction": [["affectation"], ["test"]],
        "type": [["int"], ["float"]],
        "affectation": [["id", "=", "nombre", ";"]],
        "test": [["if", "condition", "une_instruction", "else", "une_instruction", ";"]],
        "condition": [["id", "operateur", "nombre"]],
        "operateur": [["<"], [">"], ["="]]
    }
    return dic, "Programme"


def afficherRegles(regles):
    print("\nLes règles de production sont :\n")
    for key,rule in regles.items():
        print(key, end=" ::= ")
        for sub_rule in rule:
            for symbole in sub_rule:
                print(symbole, end=" ")
            print(' | ', end='')
        print()
    return


def estNonTerminal(symbole, regles):
    if symbole in regles.keys():
        return True
    return False


def premier(regles, key, premiers):
    for regle in regles[key]:
        symbole = regle[0]
        # L'élément est-il terminal ?
        if not estNonTerminal(symbole, regles):
            # Oui -> On l'ajoute à la liste des premiers
            premiers[key].add(symbole)
        else:
            # Non -> On ajoute les premiers du symbole non terminal
            premiers[key] = premiers[key].union(premier(regles, symbole, premiers))
    return premiers[key]


def getPremiers(regles):
    premiers = defaultdict(set)
    for key,rule in regles.items():
        premier(regles, key, premiers)
    return premiers


def afficherPremiers(premiers):
    print("\nLes ensembles premiers sont :\n")
    for element,listePremiers in premiers.items():
        print("Premier(", element, ") =", listePremiers)
    return


def suivant(regles, premiers, suivants, elementNonTerminal):
    # Pour chaque règle de production
    for symboleRegle,regle in regles.items():
        # Pour chaque production d'une règle
        for production in regle:
            # Pour chaque symbole de la production
            for i in range(len(production)):
                # Symbole non terminal ?
                if production[i] == elementNonTerminal:
                    # Sommes-nous au dernier symbole de la règle de production ?
                    if i+1 < len(production):
                        # On prend beta à partir du symbole
                        beta = production[i+1]
                        # Le symbole beta est-il non terminal ?
                        if estNonTerminal(beta, regles):
                            # Oui -> On ajoute Premier(beta) dans l'ensemble Suivant
                            suivants[elementNonTerminal] = suivants[elementNonTerminal].union(premiers[beta])
                            # On retire le symbole vide au cas où
                            suivants[elementNonTerminal].discard('vide')
                        else:
                            # Non -> On ajoute le terminal beta dans l'ensemble Suivant
                            suivants[elementNonTerminal].add(beta)
                        # Cas où beta n'est composé que du vide
                        if 'vide' in premiers[beta] and symboleRegle != beta:
                              suivants[elementNonTerminal] = suivants[elementNonTerminal].union(suivant(regles, premiers, suivants, symboleRegle))
                    # Cas où on est au dernier symbole de la règle de production
                    # On ajoute le suivant de ce symbole s'il n'est pas terminal
                    elif i+1 == len(production) and symboleRegle != elementNonTerminal:
                        if estNonTerminal(production[i], regles):
                            suivants[elementNonTerminal] = suivants[elementNonTerminal].union(suivant(regles, premiers, suivants, symboleRegle))
                            
    return suivants[elementNonTerminal]


def getSuivants(regles, premiers, etat_initial):
    suivants = defaultdict(set)
    for non_terminal in regles.keys():
        if non_terminal == etat_initial:
            suivants[non_terminal].add('$')
        else:
            suivant(regles, premiers, suivants, non_terminal)
    return suivants


def afficherSuivants(suivants):
    print("\nLes ensembles suivants sont :\n")
    for element,listeSuivants in suivants.items():
        print("Suivant(", element, ") =", listeSuivants)
    return


from collections import defaultdict
from copy import deepcopy

if __name__ == "__main__":
    regles, etat_initial = getRegles()
    premiers = getPremiers(regles)
    suivants = getSuivants(regles, deepcopy(premiers), etat_initial)
    
    afficherRegles(regles)
    afficherPremiers(premiers)
    afficherSuivants(suivants)

    

