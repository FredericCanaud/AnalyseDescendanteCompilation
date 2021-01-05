import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ReglesDeProductions reglesDeProductions = new ReglesDeProductions();
        reglesDeProductions.printReglesDeProduction();

        TableDAnalyse tableDAnalyse = new TableDAnalyse();
        tableDAnalyse.getTableDeProduction();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrer votre expression a analyser : (Separer vos symboles par un espace et finissez par $)");

        String[] input;
        for(input = scanner.nextLine().split(" "); !input[input.length - 1].equals("$"); input = scanner.nextLine().split(" ")) {
            System.out.println("\n Ajouter un $ a la fin de votre expression. Essayez encore");
        }

        System.out.println("\n Votre chaîne est : " + Arrays.toString(input));
        ArrayList<String> stack = new ArrayList<>();
        stack.add("$");
        stack.add(reglesDeProductions.getRegleDeDepart());
        int inp = 0;
        boolean acceptee = false;

        while(stack.size() != 0 && input[inp] != null) {
            System.out.println(stack);

            String depiler = stack.remove(stack.size() - 1);
            while(depiler.equals("vide")){
                depiler = (String) stack.remove(0);
            }

            if (!depiler.equals(input[inp])) {
                if (!tableDAnalyse.getTableAnalyse().containsKey(depiler)) {
                    System.out.println("Expression rejetee, non generable par la grammaire.");
                    return;
                }

                System.out.println(tableDAnalyse.getTableAnalyse().get(depiler).containsKey(input[inp]));
                if (tableDAnalyse.getTableAnalyse().get(depiler).containsKey(input[inp])) {
                    String numeroRegle = tableDAnalyse.getTableAnalyse().get(depiler).get(input[inp]).toString();
                    stack.add(numeroRegle);
                }

            } else {
                ++inp;
            }

            if (stack.get(0).equals(input[inp])) {
                acceptee = true;
                break;
            }
        }

        if (acceptee) {
            System.out.println("Expression acceptee.");
        } else {
            System.out.println("Expression rejetee, non generable par la grammaire.");
        }

    }
}
