package generation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FRtoRE {
    int level;
    int tailleL;
    int length;

    private void setTailleL (int tailleL){
        this.tailleL = tailleL;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setL(int level) {
        this.level = level;
    }

    private char[] letters = new char[]{'a', 'b', 'c', 'd', 'e'};
    private char[] numbers = new char[]{'0', '1', '2', '3', '4'};
    private String[] num = new String[]{"un", "deux", "trois"};

    String[] phrases1 = {"tous les mots.",
            "les mots n'ayant que des _.",
            "les mots avec au moins _.",
            "les mots avec exactement _.",
            "les mots n'ayant aucun _."};
    String[] phrases2 = {
            "les mots finissant par _.",
            "les mots commençant par _.",
            "les mots qui contiennent le facteur _."};
    String[] phrases3 = {
            "les mots de longueur paire.",
            "les mots de longueur impaire.",
            "les mots avec un nombre pair de _.",
            "les mots avec un nombre impair de _."};
    String[] phrases4 = {"les mots dont la longueur est divisible par _.",
            "les mots dont toutes les lettres sont égales.",
            "les mots dont toutes les lettres sont différentes.",
            "les mots qui ne contiennent pas le facteur _."};
    String[] phrases5 = {"les mots tels que si le mot contient _ alors il doit y avoir, dans la suite du mot, _.",
            "les mots tels que les blocs de _ consécutifs sont tous de longueur multiple de _.",
            "les mots tels que les blocs de _ consécutifs sont tous de longueur paire.",
            "les mots tels que tous les _ (s'il y en a) sont avant tous les _."};


    public FRtoRE (int level) throws Exception {
        if (level<1||level>10){
            throw new Exception("The level must be between 1 and 10");
        }
        else {
            this.level = level;
        }
    }

    public String[] read (String phrase) throws Exception {
        String maPhrase = "";
        String[] construct;
        char cur_sym;
        char x[] = phrase.toCharArray();
        String reg = "";
        if (Arrays.toString(phrases5).contains(phrase)){
            int nb = 0;
            String pb1 = "";
            String pb2 = "";
            for (int i = 0; i < x.length; i++) {
                cur_sym = x[i];
                if (cur_sym == '_') {
                    nb++;
                    if (nb == 1) {
                        pb1 = genererAleatoire();
                        maPhrase += pb1;
                    }
                    else if (nb == 2){
                        if (phrases5[1] == phrase){
                            pb2 = num[getRandom(1,num.length-1)];
                            maPhrase += pb2;
                        }
                        else {
                            pb2 = genererAleatoire();
                            maPhrase += pb2;
                        }
                    }
                }
                else {
                    maPhrase = maPhrase + cur_sym;
                }
            }
            if (phrases5[0] == phrase){
                reg += getL(tailleL)+"("+pb1+getL(tailleL)+pb2+")*"+getL(tailleL);
            }
            else if (phrases5[1] == phrase){
                if (pb2 == "deux"){
                    reg += "("+getS(tailleL)+pb1+pb1+getS(tailleL)+")*";
                }
                else if (pb2 == "trois"){
                    reg += "("+getS(tailleL)+pb1+pb1+pb1+getS(tailleL)+")*";
                }
            }
            else if (phrases5[2] == phrase){
                reg += "("+getL(tailleL)+pb1+pb1+getL(tailleL)+")*";
            }
            else if (phrases5[3] == phrase){
                if (pb1.equals(pb2)){
                    while (pb1.equals(pb2)){
                        pb2 = genererAleatoire();
                    }
                }
                reg += getL(tailleL)+"|("+getL(tailleL)+pb1+getLwl(tailleL, pb2)+pb2+getLwl(tailleL,pb1)+")";
            }

        }

        else {
            for (int i = 0; i < x.length; i++) {
                cur_sym = x[i];
                if (cur_sym == '_'){
                    construct = construction(maPhrase, tailleL);
                    reg+=construct[1];
                    maPhrase = construct[0];
                }
                else {
                    maPhrase = maPhrase + cur_sym;
                    if (maPhrase.equals("tous les mots.")){
                        reg+=getL(tailleL);
                    }
                    else if (maPhrase.equals("les mots de longueur paire.")){
                        reg += "("+getS(tailleL)+getS(tailleL)+")*";
                    }
                    else if(maPhrase.equals("les mots de longueur impaire.")){
                        reg += getS(tailleL)+"("+getS(tailleL)+getS(tailleL)+")*";
                    }
                    else if(maPhrase.equals("les mots dont toutes les lettres sont égales.")){
                        for (int j = 0; j < tailleL; j++) {
                            reg += letters[j]+"*";
                            if (j != tailleL-1) {
                                reg += "|";
                            }
                        }
                    }
                    else if(maPhrase.equals("les mots dont toutes les lettres sont différentes.")){
                        ArrayList<String> traite = new ArrayList<>();
                        for (int j = 0; j < tailleL; j++) {
                            for (int k = 0; k < tailleL; k++) {
                                if (!traite.contains(String.valueOf(letters[j]))){
                                    reg += letters[j] + "|";
                                    traite.add(String.valueOf(letters[j]));
                                }
                                if (letters[j] != letters[k]){
                                    if (!traite.contains(letters[j]+""+letters[k])){
                                        reg += letters[j]+""+letters[k] + "|";
                                        traite.add(letters[j]+""+letters[k]);
                                    }
                                    if (tailleL >= 3){
                                        for (int l = 0; l < tailleL; l++) {
                                            if (letters[j] != letters[l] && letters[l] != letters[k]){
                                                if (!traite.contains(letters[j]+""+letters[k]+""+letters[l])){
                                                    reg += letters[j]+""+letters[k]+""+letters[l] + "|";
                                                    traite.add(letters[j]+""+letters[k]+""+letters[l]);
                                                }
                                                if(tailleL == 4){
                                                    for (int m = 0; m < tailleL; m++) {
                                                        if (letters[m] != letters[j] && letters[m] != letters[k] && letters[m] != letters[l]) {
                                                            if (!traite.contains(letters[j] + "" + letters[k] + "" + letters[l] + "" + letters[m])) {
                                                                reg += letters[j] + "" + letters[k] + "" + letters[l] + "" + letters[m] + "|";
                                                                traite.add(letters[j] + "" + letters[k] + "" + letters[l] + "" + letters[m]);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (reg.charAt(reg.length()-1) == '|'){
                            reg = reg.substring(0, reg.length()-1);
                        }
                    }
                }
            }
        }
        String[] probleme = new String[]{maPhrase,reg};
        return probleme;
    }

    private String getL(int level){
        return getS(level)+"*";
    }
    private String getS(int level){
        StringBuilder stringBuilder = new StringBuilder();
        char[] oper = Arrays.copyOfRange(letters, 0, level);
        for (int i = 0; i < oper.length; i++) {
            stringBuilder.append(letters[i]);
            if (i != oper.length-1){
                stringBuilder.append('|');
            }
        }
        String string = "("+stringBuilder.toString()+")";
        return string;
    }
    private String getAlphabet(int level){
        StringBuilder stringBuilder = new StringBuilder();
        char[] oper = Arrays.copyOfRange(letters, 0, level);
        for (int i = 0; i < oper.length; i++) {
            stringBuilder.append(letters[i]);
            if (i != oper.length-1){
                stringBuilder.append(',');
            }
        }
        String string = "{"+stringBuilder.toString()+"}";
        return string;
    }
    public String alphabet (){
        return getAlphabet(tailleL);
    }
    private String getLwl (int level, String s){
        StringBuilder stringBuilder = new StringBuilder();
        char[] oper = Arrays.copyOfRange(letters, 0, level);
        for (int i = 0; i < oper.length; i++) {
            if (!contenu(s, letters[i])){
                stringBuilder.append(letters[i]);
                stringBuilder.append('|');
            }
        }
        if(stringBuilder.length() > 0){
            if (stringBuilder.charAt(stringBuilder.length()-1) == '|'){
                stringBuilder.deleteCharAt(stringBuilder.length()-1);
            }
            return "("+stringBuilder.toString()+")*";
        }
        else return "";
    }
    private boolean contenu(String s, char c){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == c){
                return true;}
        }
        return false;
    }

    public String[] construction(String phrase, int l) throws Exception {
        String pb = "";
        String maPhrase = phrase;
        String reg = "";
        String nb="";
        switch (phrase){
            case "les mots commençant par ":
                pb = genererAleatoire();
                reg = pb+getL(l);
                break;
            case "les mots finissant par ":
                pb = genererAleatoire();
                reg = getL(l)+pb;
                break;
            case "les mots avec un nombre pair de ":
                pb = genererAleatoire();
                reg = "("+getLwl(l, pb)+pb+getLwl(l, pb)+pb+getLwl(l, pb)+")*";
                break;
            case "les mots avec un nombre impair de ":
                pb = genererAleatoire();
                reg = getLwl(l, pb)+pb+getLwl(l, pb)+"("+pb+getLwl(l, pb)+pb+getLwl(l, pb)+")*";
                break;
            case "les mots qui contiennent le facteur ":
                pb = genererAleatoire();
                reg = getL(l)+pb+getL(l);
                break;
            case "les mots qui ne contiennent pas le facteur ":
                if (level == 5 || level == 6) {
                    pb = genererAleatoire();
                    reg = getLwl(l, pb);
                }
                else if (level == 7){
                    pb = genererAleatoire();
                    String a = String.valueOf(pb.charAt(0));
                    String b ="";
                    if (pb.charAt(0) == pb.charAt(pb.length()-1)){
                        String[] temp = new String[]{"","","",""};
                        int j = 0;
                        for (int i = 0; i < tailleL; i++) {
                            if (! contenu(pb,letters[i])){
                                temp[j] = String.valueOf(letters[i]);
                                j++;
                            }
                        }
                        b = temp[0];
                        reg = "("+b+"|"+a+b+")*|("+b+"|"+a+b+")*"+a;
                    }
                    else {
                        b = String.valueOf(pb.charAt(1));
                        reg = b+"*"+a+"*";
                    }
                }
                else if (level == 8){
                    pb = genererAleatoire();
                    String[] temp = new String[]{"","","",""};;
                    int j = 0;
                    for (int i = 0; i < tailleL; i++) {
                        if (! contenu(pb,letters[i])){
                            temp[j] = String.valueOf(letters[i]);
                            j++;
                        }
                    }
                    if (pb.charAt(0) == pb.charAt(pb.length()-1)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = temp[0];
                        String c = temp[1];
                        reg = "("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*+("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"+a;
                    }
                    else {
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(1));
                        String c = temp[0];
                        reg = "("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+"*";
                    }
                }
                else if (level == 9){
                    pb = genererAleatoire();
                    if (pb.charAt(0) == pb.charAt(1) && pb.charAt(0) != pb.charAt(2)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(2));
                        reg = "("+b+"|"+a+b+")*"+a+"*";
                    }
                    else if (pb.charAt(0) == pb.charAt(2) && pb.charAt(0) != pb.charAt(1)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(1));
                        reg = b+"*"+a+"*"
                                +"|"+b+"*"+a+a+"*"+b
                                +"|("+b+"*"+a+a+"*"+b+b+b+"*)*";
                    }
                    else if (pb.charAt(1) == pb.charAt(2) && pb.charAt(0) != pb.charAt(1)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(1));
                        reg = b+"*"+a+"*"
                                +"|"+b+"*"+a+a+"*("+b+a+")*"
                                +"|"+b+"*"+a+a+"*("+b+a+")*"+b;
                    }
                    else if (pb.charAt(0) == pb.charAt(1) && pb.charAt(0) == pb.charAt(2)){
                        String[] temp = new String[]{"","","",""};
                        int j = 0;
                        for (int i = 0; i < tailleL; i++) {
                            if (! contenu(pb,letters[i])){
                                temp[j] = String.valueOf(letters[i]);
                                j++;
                            }
                        }
                        String a = String.valueOf(pb.charAt(0));
                        String b = temp[0];
                        reg = "("+b+"|"+a+b+")*"
                                +"|("+b+"|"+a+b+")*"+a
                                +"|("+b+"|"+a+b+")*"+a+a
                                +"|(("+b+"|"+a+b+")*"+a+a+b+"("+b+"|"+a+b+")*)*";
                    }
                }
                else if (level == 10){
                    pb = genererAleatoire();
                    String[] temp = new String[]{"","","",""};
                    int j = 0;
                    for (int i = 0; i < tailleL; i++) {
                        if (! contenu(pb,letters[i])){
                            temp[j] = String.valueOf(letters[i]);
                            j++;
                        }
                    }
                    if (pb.charAt(0) == pb.charAt(1) && pb.charAt(0) == pb.charAt(2)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = temp[0];
                        String c = temp[1];
                        reg = "("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"
                                +"|("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"+a
                                +"|("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"+a+a
                                +"|(("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"+a+a+"("+b+"|"+c+")("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*)*";
                    }
                    else if (pb.charAt(0) == pb.charAt(2) && pb.charAt(0) != pb.charAt(1)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(1));
                        String c = temp[0];
                        reg = "("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+"*"
                                +"|("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*"+b
                                +"|(("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*"+b+"("+b+"|"+c+")"+"("+b+"|"+c+"|"+a+a+"*"+c+")*)*";
                    }
                    else if (pb.charAt(1) == pb.charAt(2) && pb.charAt(0) != pb.charAt(1)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(1));
                        String c = temp[0];
                        reg = "("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+"*"
                                +"|("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*("+b+a+")*"
                                +"|("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*("+b+a+")*"+b
                                +"|(("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*("+b+a+")*"+b+c+"("+b+"|"+c+"|"+a+a+"*"+c+")*)*";
                    }
                    else if (pb.charAt(0) == pb.charAt(1) && pb.charAt(0) != pb.charAt(2)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(2));
                        String c = temp[0];
                        reg = "("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"+a+"*"
                                +"|(("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*"+a+a+"*"+c+"("+b+"|"+c+"|"+a+"("+b+"|"+c+"))*)*";
                    }
                    else if (pb.charAt(0) != pb.charAt(1) && pb.charAt(0) != pb.charAt(2) && pb.charAt(1) != pb.charAt(2)){
                        String a = String.valueOf(pb.charAt(0));
                        String b = String.valueOf(pb.charAt(1));
                        String c = String.valueOf(pb.charAt(2));
                        reg = "("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+"*"
                                +"|("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*("+b+a+")*"
                                +"|("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*("+b+a+")*"+b
                                +"|(("+b+"|"+c+"|"+a+a+"*"+c+")*"+a+a+"*("+b+a+")*"+b+b+"("+b+"|"+c+"|"+a+a+"*"+c+")*)*";
                    }
                }
                break;
            case "les mots avec au moins ":
                pb = genererAleatoire();
                if (level == 1) nb = num[0];
                else if (level == 2) nb = num[getRandom(0,1)];
                else nb = num[getRandom(1,num.length-1)];
                reg = ecrireReg(getL(l), nb, pb);
                maPhrase = maPhrase+nb+" ";
                break;
            case "les mots avec exactement ":
                pb = genererAleatoire();
                nb = num[getRandom(0,num.length-1)];
                reg = ecrireReg(getLwl(l, pb), nb, pb);
                maPhrase = maPhrase+nb+" ";
                break;
            case "les mots dont la longueur est divisible par ":
                nb = num[getRandom(1,num.length-1)];
                if (nb == "deux"){
                    reg = "("+getS(l)+getS(l)+")*";
                }
                else if (nb == "trois"){
                    reg = "("+getS(l)+getS(l)+getS(l)+")*";
                }
                maPhrase = maPhrase+nb;
                break;
            case "les mots n'ayant aucun ":
                pb = genererAleatoire();
                reg = getLwl(l, pb);
                break;
            case "les mots n'ayant que des ":
                pb = genererAleatoire();
                reg = "("+pb+")*";
                break;

        }
        maPhrase+= pb;
        String[] phETreg = new String[]{maPhrase,reg};
        return phETreg;
    }

    private String ecrireReg(String getL, String nb, String pb){
        String reg = "";
        if (nb == "un"){
            reg = getL+pb+getL;
        }
        else if (nb == "deux"){
            reg = getL+pb+getL+pb+getL;
        }
        else if (nb == "trois"){
            reg = getL+pb+getL+pb+getL+pb+getL;
        }
        return reg;
    }

    public int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt((max-min)+1)+min;
    }

    public String genererAleatoire (){
        String str = "";
        for (int i = 0; i < length ; i++) {
            str = str + letters[getRandom(0,tailleL-1)];
        }
        return str;
    }
    public String[] combineLevel (int i){
        String str1="";
        String str2="";
        int j;
        if (i == 1) {
            setTailleL(2);
            setLength(1);
            j = getRandom(0, 1);
            if (j == 0) {
                str1 = phrases2[getRandom(0, phrases2.length - 1)];
                str2 = phrases2[getRandom(0, phrases2.length - 1)];
                if (str1.equals(str2)) {
                    while (str1.equals(str2)) {
                        str2 = phrases2[getRandom(0, phrases2.length - 1)];
                    }
                }
            }
            else if (j == 1) {
                str1 = phrases1[getRandom(2, phrases1.length - 2)];
                str2 = phrases2[getRandom(0, phrases2.length - 1)];
            }
        }
        else if (i == 2){
            setTailleL(2);
            setLength(2);
            j = getRandom(0,1);
            if (j == 0){
                str1 = phrases2[getRandom(0,phrases2.length-1)];
                str2 = phrases2[getRandom(0,phrases2.length-1)];
                if (str1.equals(str2)){
                    while (str1.equals(str2)){
                        str2 = phrases2[getRandom(0,phrases2.length-1)];
                    }
                }
            }
            else if (j == 1){
                str1 = phrases2[getRandom(0,phrases2.length-1)];
                str2 = phrases3[getRandom(0,phrases3.length-1)];
            }
        }
        else if (i == 3){
            j = getRandom(0,3);
            if (j == 0){
                setTailleL(2);
                setLength(2);
                str1 = phrases3[getRandom(0,1)];
                str2 = phrases4[getRandom(1,phrases4.length-1)];
                if (str2.equals(phrases4[3])){
                    setL(7);
                }
            }
            else if (j == 1){
                setTailleL(3);
                setLength(3);
                str1 = phrases2[getRandom(0,phrases2.length-1)];
                str2 = phrases3[getRandom(0,phrases3.length-1)];
            }
            else if (j == 2){
                setTailleL(4);
                setLength(3);
                str1 = phrases2[getRandom(0,phrases2.length-1)];
                str2 = phrases2[getRandom(0,phrases2.length-1)];
                if (str1.equals(str2)){
                    while (str1.equals(str2)){
                        str2 = phrases2[getRandom(0,phrases2.length-1)];
                    }
                }
            }
            else if (j == 3){
                setTailleL(2);
                setLength(1);
                str1 = phrases1[getRandom(2, phrases1.length - 2)];
                str2 = phrases3[getRandom(0,1)];
            }
        }
        else if (i == 4){
            j = getRandom(0,3);
            if (j == 0){
                setTailleL(3);
                setLength(1);
                str1 = phrases3[getRandom(2,3)];
                if (getRandom(0,1) == 0) str2 = phrases4[0];
                else {
                    str2 = phrases4[3];
                    setL(6);
                }
            }
            else if (j == 1){
                setTailleL(5);
                setLength(4);
                str1 = phrases2[getRandom(0,phrases2.length-1)];
                str2 = phrases3[getRandom(0,phrases3.length-1)];
            }
            else if (j == 2){
                setTailleL(5);
                setLength(4);
                str1 = phrases2[getRandom(0,phrases2.length-1)];
                str2 = phrases2[getRandom(0,phrases2.length-1)];
                if (str1.equals(str2)){
                    while (str1.equals(str2)){
                        str2 = phrases2[getRandom(0,phrases2.length-1)];
                    }
                }
            }
            else if (j == 3){
                setTailleL(4);
                setLength(1);
                str1 = phrases1[getRandom(2, phrases1.length - 2)];
                str2 = phrases3[getRandom(0,1)];
            }
        }
        else if (i == 5){
            j = getRandom(0,2);
            if (j == 0){
                setTailleL(5);
                setLength(4);
                str1 = phrases2[getRandom(0,phrases2.length-2)];
                str2 = phrases4[0];
            }
            else if (j == 1){
                setTailleL(3);
                setLength(2);
                str1 = phrases2[2];
                str2 = phrases5[getRandom(0,phrases5.length-1)];
            }
            else if (j == 2){
                setTailleL(3);
                setLength(2);
                str1 = phrases3[getRandom(0,phrases3.length-1)];
                str2 = phrases5[getRandom(0,phrases5.length-1)];
            }
        }
        else if (i == 6){
            j = getRandom(0,1);
            if (j == 0){
                setTailleL(5);
                setLength(3);
                str1 = phrases2[2];
                str2 = phrases5[getRandom(0,phrases5.length-1)];
            }
            else if (j == 1){
                setTailleL(5);
                setLength(3);
                str1 = phrases3[getRandom(0,phrases3.length-1)];
                str2 = phrases5[getRandom(0,phrases5.length-1)];
            }
        }
        String[] mesPb = new String[]{str1,str2};
        return mesPb;
    }
    private String setLevel(){
        String str = "";
        int i;
        switch (level){
            case 1:
                i = getRandom(1,2);
                setLength(1);
                setTailleL(1);
                if (i == 1){
                    str = phrases1[getRandom(0,phrases1.length-2)];
                }
                else if (i == 2){
                    str = phrases2[getRandom(0,phrases2.length-1)];
                }
                break;
            case 2:
                i = getRandom(1,2);
                setLength(1);
                str = initialise(i,2,2,0,0,0);
                break;
            case 3:
                i = getRandom(1,3);
                if (i == 1 || i == 3) setLength(1);
                else setLength(2);
                str = initialise(i,3,3,2,0,0);
                break;
            case 4:
                i = getRandom(1,3);
                if (i == 1) setLength(1);
                else setLength(2);
                str = initialise(i,4,3,3,0,0);
                break;
            case 5:
                i = getRandom(1,4);
                if (i == 1) setLength(1);
                else if (i == 3 || i == 4) setLength(2);
                else setLength(3);
                str = initialise(i,5,3,3,2,0);
                break;
            case 6:
                i = getRandom(2,4);
                if (i == 4) setLength(2);
                else setLength(3);
                str = initialise(i,0,4,4,3,0);
                break;
            case 7:
                i = getRandom(2,5);
                if (i == 2) setLength(4);
                else if (i == 3 || i == 4) setLength(3);
                else setLength(1);
                str = initialise(i,0,5,5,3,2);
                break;
            case 8:
                i = getRandom(4,5);
                if (i == 4) setLength(2);
                else setLength(1);
                str = initialise(i,0,0,0,4,3);
                break;
            case 9:
                i = getRandom(4,5);
                if (i == 4 ) setLength(3);
                else setLength(2);
                str = initialise(i,0,0,0,4,4);
                break;
            case 10:
                i = getRandom(4,5);
                setLength(3);
                str = initialise(i,0,0,0,5,4);
                break;
        }
        return str;
    }

    private String initialise(int i, int L1, int L2, int L3, int L4, int L5) {
        String str;
        if (i == 1){
            setTailleL(L1);
            str = phrases1[getRandom(0,phrases1.length-1)];
        }
        else if (i == 2){
            setTailleL(L2);
            str = phrases2[getRandom(0,phrases2.length-1)];
        }
        else if (i == 3){
            setTailleL(L3);
            str = phrases3[getRandom(0,phrases3.length-1)];
        }
        else if (i == 4){
            setTailleL(L4);
            str = phrases4[getRandom(0,phrases4.length-1)];
            if(str.equals(phrases4[2])){
                if (level == 5 || level == 6) setTailleL(2);
                else if (level == 7 || level == 8 || level == 9) setTailleL(3);
                else if (level == 10) setTailleL(4);
            }
            if(str.equals(phrases4[3])){
                if (level == 5) {
                    setLength(1);
                    setTailleL(2);
                }
                else if (level == 6){
                    setLength(1);
                    setTailleL(3);
                }
                else if (level == 7){
                    setLength(2);
                    setTailleL(2);
                }
                else if (level == 8){
                    setLength(2);
                    setTailleL(3);
                }
                else if (level == 9){
                    setLength(3);
                    setTailleL(2);
                }
                else if (level == 10){
                    setLength(3);
                    setTailleL(3);
                }
            }
        }
        else {
            setTailleL(L5);
            str = phrases5[getRandom(0,phrases5.length-1)];
        }
        return str;
    }

    public String[] goToCombine (int i) throws Exception {
        String[] mesPb = combineLevel(i);
        String[] resul1 = read(mesPb[0]);
        String[] resul2 = read(mesPb[1]);
        String[] resul = new String[]{resul1[0],resul1[1],resul2[0],resul2[1],alphabet()};
        return resul;
    }
    public String[] goToRE () throws Exception {
        String sl = setLevel();
        String[] resulRead = read(sl);
        String[] resul = new String[]{resulRead[0],resulRead[1],alphabet()};
        return resul;
    }

    public String[] generate() throws Exception {
        String[] genererResul;
        String str = "Donner une expression régulière qui reconnait ";
        String[] resul = goToRE();
        String s = resul[0];
        String maReg = resul[1];
        str = str + s + " Sur l'alphabet "+getAlphabet(tailleL)+".";
        genererResul = new String[]{str, enleverPts(maReg)};
        toString(genererResul);
        return genererResul;
    }

    private String enleverPts (String str){
        String maPhrase = "";
        char cur_sym;
        char x[] = str.toCharArray();
        for (int i = 0; i < x.length; i++) {
            cur_sym = x[i];
            if (cur_sym != '.') {
                maPhrase = maPhrase + cur_sym;
            }
        }
        return maPhrase;
    }
    public void toString (String[] strings){
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }

}
