import java.util.*;

public class ReglesDeProductions {

    static LinkedHashMap<String, ArrayList<ArrayList<String>>> regles = new LinkedHashMap<>();
    static{

        regles.put("<Programme>",new ArrayList<>());
        regles.get("<Programme>").add(new ArrayList<>());
        regles.get("<Programme>").get(0).add(("main(){"));
        regles.get("<Programme>").get(0).add(("<liste_declarations>"));
        regles.get("<Programme>").get(0).add(("<liste_instructions>"));
        regles.get("<Programme>").get(0).add(("}"));

        regles.put("<liste_declarations>",new ArrayList<>());
        regles.get("<liste_declarations>").add(new ArrayList<>());
        regles.get("<liste_declarations>").get(0).add(("<une_declaration>"));
        regles.get("<liste_declarations>").get(0).add(("<liste_declarations>"));
        regles.get("<liste_declarations>").add(new ArrayList<>());
        regles.get("<liste_declarations>").get(1).add(("vide"));

        regles.put("<une_declaration>",new ArrayList<>());
        regles.get("<une_declaration>").add(new ArrayList<>());
        regles.get("<une_declaration>").get(0).add(("type>"));
        regles.get("<une_declaration>").get(0).add(("id"));

        regles.put("<liste_instructions>",new ArrayList<>());
        regles.get("<liste_instructions>").add(new ArrayList<>());
        regles.get("<liste_instructions>").get(0).add(("<une_instruction>"));
        regles.get("<liste_instructions>").get(0).add(("<liste_instructions>"));
        regles.get("<liste_instructions>").add(new ArrayList<>());
        regles.get("<liste_instructions>").get(1).add(("vide"));

        regles.put("<une_instruction>",new ArrayList<>());
        regles.get("<une_instruction>").add(new ArrayList<>());
        regles.get("<une_instruction>").get(0).add(("<affectation>"));
        regles.get("<une_instruction>").add(new ArrayList<>());
        regles.get("<une_instruction>").get(1).add(("<test>"));

        regles.put("<type>",new ArrayList<>());
        regles.get("<type>").add(new ArrayList<>());
        regles.get("<type>").get(0).add(("int"));
        regles.get("<type>").add(new ArrayList<>());
        regles.get("<type>").get(1).add(("float"));

        regles.put("<affectation>",new ArrayList<>());
        regles.get("<affectation>").add(new ArrayList<>());
        regles.get("<affectation>").get(0).add(("id"));
        regles.get("<affectation>").get(0).add(("="));
        regles.get("<affectation>").get(0).add(("nombre"));
        regles.get("<affectation>").get(0).add((";"));

        regles.put("<test>",new ArrayList<>());
        regles.get("<test>").add(new ArrayList<>());
        regles.get("<test>").get(0).add(("if"));
        regles.get("<test>").get(0).add(("<condition>"));
        regles.get("<test>").get(0).add(("<instruction>"));
        regles.get("<test>").get(0).add(("else"));
        regles.get("<test>").get(0).add(("<instruction>"));
        regles.get("<test>").get(0).add(("vide"));

        regles.put("<condition>",new ArrayList<>());
        regles.get("<condition>").add(new ArrayList<>());
        regles.get("<condition>").get(0).add(("id"));
        regles.get("<condition>").get(0).add(("<operateur>"));
        regles.get("<condition>").get(0).add(("nombre"));

        regles.put("<operateur>",new ArrayList<>());
        regles.get("<operateur>").add(new ArrayList<>());
        regles.get("<operateur>").get(0).add(("<"));
        regles.get("<operateur>").add(new ArrayList<>());
        regles.get("<operateur>").get(1).add((">"));
        regles.get("<operateur>").add(new ArrayList<>());
        regles.get("<operateur>").get(2).add(("="));
    }

    public void printReglesDeProduction(){

        System.out.println("Les règles de production sont :");
        for (Map.Entry<String, ArrayList<ArrayList<String>>> entry : regles.entrySet()) {
            String key = entry.getKey();
            ArrayList<ArrayList<String>> value = entry.getValue();
            System.out.print("    " + key + " ::=  ");
            for (ArrayList<String> strings : value) {
                for(String string : strings){
                    System.out.print(string + " ");
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    public boolean estTerminal(String symbole) {
        return !regles.containsKey(symbole);
    }

    public String getRegleDeDepart() {
        return "<Programme>";
    }

    public LinkedHashMap<String, ArrayList<ArrayList<String>>> getRegles() {
        return regles;
    }
}
