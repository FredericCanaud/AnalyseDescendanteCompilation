import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class TableDAnalyse {

    public TableDAnalyse() {
    }
    static LinkedHashMap<String, LinkedHashMap<String, Integer>> tableAnalyse = new LinkedHashMap<>();
    public LinkedHashMap<String, LinkedHashMap<String, Integer>> getTableAnalyse() {
        return tableAnalyse;
    }

    static {
        tableAnalyse.put("<Programme>", new LinkedHashMap<>());
        tableAnalyse.get("<Programme>").put("main(){", 0);
        tableAnalyse.put("<liste_declarations>", new LinkedHashMap<>());
        tableAnalyse.get("<liste_declarations>").put("int", 1);
        tableAnalyse.get("<liste_declarations>").put("float", 1);
        tableAnalyse.get("<liste_declarations>").put("$", 1);
        tableAnalyse.put("<une_declaration>", new LinkedHashMap<>());
        tableAnalyse.get("<une_declaration>").put("int", 0);
        tableAnalyse.get("<une_declaration>").put("float", 0);
        tableAnalyse.put("<liste_instructions>", new LinkedHashMap<>());
        tableAnalyse.get("<liste_instructions>").put("int", 0);
        tableAnalyse.get("<liste_instructions>").put("float", 1);
        tableAnalyse.get("<liste_instructions>").put("}", 1);
        tableAnalyse.put("<une_instruction>", new LinkedHashMap<>());
        tableAnalyse.get("<une_instruction>").put("id", 0);
        tableAnalyse.get("<une_instruction>").put("if", 1);
        tableAnalyse.put("<type>", new LinkedHashMap<>());
        tableAnalyse.get("<type>").put("int", 0);
        tableAnalyse.get("<type>").put("float", 1);
        tableAnalyse.put("<affectation>", new LinkedHashMap<>());
        tableAnalyse.get("<affectation>").put("id", 0);
        tableAnalyse.put("<test>", new LinkedHashMap<>());
        tableAnalyse.get("<test>").put("if", 0);
        tableAnalyse.put("<condition>", new LinkedHashMap<>());
        tableAnalyse.get("<condition>").put("id", 0);
        tableAnalyse.put("<operateur>", new LinkedHashMap<>());
        tableAnalyse.get("<operateur>").put("<", 0);
        tableAnalyse.get("<operateur>").put(">", 1);
        tableAnalyse.get("<operateur>").put("=", 2);
    }

    public void getTableDeProduction() {
        ReglesDeProductions regles = new ReglesDeProductions();
        System.out.println("La table d'analyse de la grammaire est : \n");

        for (Entry<String, LinkedHashMap<String, Integer>> entry : tableAnalyse.entrySet()) {
            String regle = entry.getKey();
            LinkedHashMap<String, Integer> premiers = entry.getValue();

            for (Entry<String, Integer> production : premiers.entrySet()) {
                String terminal = production.getKey();
                int regleAssociee = production.getValue();
                System.out.println("('" + regle + "', '" + terminal + "') : " + regle + "::= " + (regles.getRegles().get(regle)).get(regleAssociee));
            }
        }

    }
}