package generation;

import automaton.Automaton;
import automaton.BasicOperations;
import automaton.RegExp;

import java.io.*;


public class Main {

    public static void main(String[] args) throws Exception {

        FRtoAUTO fRtoAUTO = new FRtoAUTO(8);
        String[] resultat = fRtoAUTO.generate();
        //fRtoAUTO.toString(resultat);
        PrintStream stdout = System.out;
        PrintStream fr = new PrintStream(new FileOutputStream("src/sample/ressourcefrnfa/enoncefrnfa.txt",false));
        PrintStream dfa = new PrintStream(new FileOutputStream("dotfiles\\frnfa.dot",false));
        System.setOut(fr);
        System.out.println(resultat[0]);
        if (resultat.length == 4){
            String exp1 = resultat[1];
            String exp2 = resultat[2];
            RegExp regexp1 = new RegExp(exp1);
            RegExp regexp2 = new RegExp(exp2);
            if (resultat[3].equals("O")){
                Automaton a = regexp1.toAutomaton(true);
                Automaton b = regexp1.toAutomaton(true);
                System.setOut(dfa);
                System.out.println(BasicOperations.concatenate(a,b).toDot());
                System.setOut(stdout);
            }
            else if (resultat[3].equals("A")){
                Automaton a = regexp1.toAutomaton(true);
                Automaton b = regexp2.toAutomaton(true);
                System.setOut(dfa);
                System.out.println(BasicOperations.concatenate(a,b).toDot());
                System.setOut(stdout);
            }
        }
        else if (resultat.length == 2){
            String exp = resultat[1];
            RegExp regexp = new RegExp(exp);
            Automaton a = regexp.toAutomaton(true);
            System.setOut(dfa);
            System.out.println(a.toDot());
            //System.setOut(stdout);
        }





        /*PrintStream out = new PrintStream(new FileOutputStream("NFA.dot", false));
        PrintStream stdout = System.out;
        PrintStream dfa = new PrintStream(new FileOutputStream("DFA.dot", false));
        REtoNFA retonfa = new REtoNFA();
        int compteur;
        MoFormula moFormula = new MoFormula(10);
        //moFormula.GenerateDistinctBlocs();
        RegExp regExp = new RegExp(moFormula.PutParentheses(moFormula.methodeMain()));
        //RegExp regExp = new RegExp("a(a|b)*");
        //String formule = moFormula.PutParentheses(moFormula.methodeMain());


        System.setOut(out);

         */


        /*svp.REtoNFA.NFA required_nfa;
        required_nfa = retonfa.re_to_nfa(formule);
        required_nfa.display();

        Automaton a = regExp.toAutomaton(false);
        System.out.println(a.toDot());

        System.setOut(dfa);
        a = regExp.toAutomaton(true);
        System.out.println(a.toDot());
        //svp.REtoNFA.DFA required_dfa = retonfa.nfa_to_dfa(required_nfa);
        //required_dfa.display();

        System.setOut(stdout);
        //required_nfa.display();
        //required_dfa.display();
        //System.out.println(formule);

        ParsingDOT parsingDOT = new ParsingDOT();
        parsingDOT.Parsing("Windows");
        //parsingDOT.Parsing("Linux");

         */





        /**
         * Création de l'automate(NFA,DFA) avec la librairie provenant de la classe svp.REtoNFA
         */
        //retonfa.run(moFormula.PutParentheses(moFormula.methodeMain()));


        /**
         * Création de 10 formules
         */
        //for (int i = 0; i < 10; i++) {
          //  generation.MoFormula moFormula2 = new generation.MoFormula(3);
            //moFormula2.display();
           // moFormula2.methodeMain();

        }

         //*/



        /**
         * Pour utiliser l'autre librairie (celle du sous-dossier automaton)
         */
        //RegExp regexp = new RegExp(svp.MoFormula.PutParentheses(moFormula.methodeMain()));
        /*RegExp regexp = new RegExp("(b|a)(a)((a|b)*)");
        System.out.println(regexp);
        Automaton a = regexp.toAutomaton();
        System.out.println("NFA : \n");
        System.out.println(a.toDot());

        a.determinize();
        System.out.println("DFA : \n");
        System.out.println(a.toDot());

         */






        /*try (BufferedReader br = new BufferedReader(new FileReader("salut.txt"))) {
            System.setOut(in);
            boolean line;
            int nbrligne = 0;
            while (line = br.readLine() != null) {
                nbrligne++;
            }
            br.close();
            System.out.println("Nombre de ligne : " + nbrligne);
        }

         */














        //moFormula.display();
        //System.out.println(moFormula.FormulaGenerator());
        //moFormula.GenerateDistinctBlocs();
        //moFormula.display();
        //System.out.println(moFormula.FormulaGenerator());
        //System.out.println(moFormula.SigmaLanguage(4));
        //System.out.println(moFormula.EBloc(3));
        //System.out.println(moFormula.EProblemGenerator(3));
        // System.out.println(moFormula.WildGenerator(2, 3));

        // System.out.println(formula.RandomAlphabets(3));





    }

