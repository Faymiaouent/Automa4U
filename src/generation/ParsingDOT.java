package generation;

import java.io.File;
import java.io.IOException;

public class ParsingDOT {

    /**
     * (*!!* IMPORTANT *!!*)
     * Modifier le chemin si votre Graphviz n'est pas à l'endroit inscrit ci-dessous pour votre OS.
     * OS -> "Windows" ou "Linux"
     */
    public void Parsing(String OS) throws IOException {
        File dossier = new File("Result");
        if(!dossier.exists()) dossier.mkdir();
        Runtime rt = Runtime.getRuntime();
        switch(OS){
            case("Windows"):
                Process pr = rt.exec("c:\\Graphviz\\bin\\dot.exe -Tpng NFA.dot -o Result\\NFA.png");
                Process pr2 = rt.exec("c:\\Graphviz\\bin\\gxl2gv.exe -g -o Result\\NFA.gxl NFA.dot");
                Process pr3 = rt.exec("c:\\Graphviz\\bin\\dot.exe -Tpng DFA.dot -o Result\\DFA.png");
                Process pr4 = rt.exec("c:\\Graphviz\\bin\\gxl2gv.exe -g -o Result\\DFA.gxl DFA.dot");
                break;
            case("Linux"):
                Process pr5 = rt.exec("\\usr\\bin\\dot -Tpng NFA.dot -o Result\\NFA.png");
                Process pr6 = rt.exec("\\usr\\bin\\gxl2gv -g -o Result\\NFA.gxl NFA.dot");
                Process pr7 = rt.exec("\\usr\\bin\\dot -Tpng DFA.dot -o Result\\DFA.png");
                Process pr8 = rt.exec("\\usr\\bin\\gxl2gv -g -o Result\\DFA.gxl DFA.dot");
                break;
        }
    }

    public void ParsingDFA(String file) throws IOException {
        File dossier = new File("dotfiles");
        if(!dossier.exists()) dossier.mkdir();
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("c:\\Graphviz\\bin\\dot.exe -Tpng dotfiles\\DFA"+file+".dot -o src\\sample\\ressources\\dfa"+file+".png");
        Process pr4 = rt.exec("c:\\Graphviz\\bin\\gxl2gv.exe -g -o Result\\DFA"+file+".gxl dotfiles\\DFA"+file+".dot");
    }

    public void ParsingNFA(String file) throws IOException {
        File dossier = new File("dotfiles");
        if(!dossier.exists()) dossier.mkdir();
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("c:\\Graphviz\\bin\\dot.exe -Tpng dotfiles\\NFA"+file+".dot -o src\\sample\\ressources\\nfa"+file+".png");
        Process pr4 = rt.exec("c:\\Graphviz\\bin\\gxl2gv.exe -g -o Result\\DFA"+file+".gxl dotfiles\\DFA"+file+".dot");
    }

    public void ParsingFrDFA(String file) throws IOException{
        File dossier = new File("dotfiles");
        if(!dossier.exists()) dossier.mkdir();
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("c:\\Graphviz\\bin\\dot.exe -Tpng dotfiles\\frdfa"+file+".dot -o src\\sample\\ressourcefrdfa\\frdfa"+file+".png");
        Process pr4 = rt.exec("c:\\Graphviz\\bin\\gxl2gv.exe -g -o Result\\frdfa"+file+".gxl dotfiles\\frdfa"+file+".dot");
    }

    public void ParsingFrNFA(String file) throws IOException{
        File dossier = new File("dotfiles");
        if(!dossier.exists()) dossier.mkdir();
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("c:\\Graphviz\\bin\\dot.exe -Tpng dotfiles\\frnfa"+file+".dot -o src\\sample\\ressourcefrnfa\\frnfa"+file+".png");
        Process pr4 = rt.exec("c:\\Graphviz\\bin\\gxl2gv.exe -g -o Result\\frnfa"+file+".gxl dotfiles\\frnfa"+file+".dot");
    }
}
