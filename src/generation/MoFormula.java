package generation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

public class MoFormula {

    private final int level;
    private HashMap<Integer, char[]> Alphabets = new HashMap<>();
    private char[] letters;
    private final char[] Operations = {'|','.'};
    private int[] vector;
    private ArrayList<String> blocs;
    private int remainingStars;

    /** f(x): [1, 10] --->  NxNxN
     *  n     --->  (NbrOfBlocs, NbrOfOperationsInOneBloc, NbrOfStars, deep)
     */

    public MoFormula(int level) throws Exception {
        if (level<1||level>10){
            throw new Exception("The level must be between 1 and 10");
        }
        else {
            Initialise();
            this.letters = Alphabets.get(getRandom(0,1));
            this.level = level;
            SetLevel();
            remainingStars = vector[2];
        }
    }

    public void SetLevel(){
        switch (level) {
            case 1:
                vector = new int[]{1, 0, 1, 0};
                break;
            case 2:
                vector = new int[]{1, 1, 1, 1};
                break;
            case 3:
                vector = new int[]{2, 1, 1, 1};
                break;
            case 4:
                vector = new int[]{2, 2, 1, 1 };
                break;
            case 5:
                vector = new int[]{3, 2, 1, 2 };
                break;
            case 6:
                vector = new int[]{3, 2, 2, 2 };
                break;
            case 7:
                vector = new int[]{3, 3, 2, 3};
                break;
            case 8:
                vector = new int[]{3, 3, 3, 3};
                break;
            case 9:
                vector = new int[]{4, 3, 3, 3};
                break;
            case 10:
                vector = new int[]{5, 4, 3, 4};
                break;
        }
    }
    public void Initialise(){
        Alphabets.put(0, new char[]{'a', 'b', 'c', 'd', 'e', 'ε'});
        Alphabets.put(1, new char[]{'0', '1', '2', '3', '4', 'ε'});
    }


    // Méthode générale pour obtenir des blocs
    public void GenerateDistinctBlocs() throws Exception {
        blocs = new ArrayList<>();
        for (int i = 0; i < vector[0]; i++) {
            String bloc = GenerateBloc(vector[3]);
            while (ContainsBloc(blocs,bloc)){
                bloc = GenerateBloc(vector[3]);
            }
            blocs.add(bloc);
        }
    }
    public String GenerateBloc(int deep) throws Exception {
        if (deep>letters.length-1){
            throw new Exception("letters index out of bound deep must be less or equal to :"+(letters.length-1));
        }
        else{
            StringBuilder stringBuilder = new StringBuilder();
            Character operator = letters[getRandom(0, deep)];
            stringBuilder.append(operator);
            if (level != 1){
                stringBuilder.append(Operations[(int)(Math.random()*Operations.length)]);
                stringBuilder.append(ChangeLetters(operator,deep));
            }
            else {
                stringBuilder.append(Operations[1]);
                stringBuilder.append(ChangeLetters(operator,deep));
            }

            return stringBuilder.toString();
        }
    }
    private Character ChangeLetters(Character operator1, int deep){
        for (int i = 0; i <= deep ; i++) {
            if (!operator1.equals(letters[i])){
                return letters[i];
            }
        }
        return operator1;
    }

    // Méthode pour obtenir des blocs mêlés de Sigma*
    public String GenerateBlocL(int deep, int level) throws Exception {
        if (deep>letters.length-1){
            throw new Exception("letters index out of bound deep must be less or equal to :"+(letters.length-1));
        }
        else {
            String string = "";
            String str = "";
            if (level == 3){
                int n = getRandom(0,1);
                if (n == 0){
                    str = boucle(2,3,str,deep);
                }
                else {
                    str = boucle(1,2,str,deep);
                    str = points(str, BetweenLanguage(str, deep), str);
                }
                string = BetweenLanguage(str, deep);

            }
            else if (level == 2){
                int n = getRandom(0,2);
                if (n == 0){
                    str = boucle(2,3,str,deep);
                    string = BetweenLanguage(str, deep);
                }
                else if (n==1){
                    str = boucle(1,3,str,deep);
                    int a = getRandom(0,1);
                    if (a==0) string = FinishWithLanguage(str, deep);
                    else string = BeginWithLanguage(str,deep);
                }
                else if (n==2){
                    str = boucle(1,1,str,deep);
                    str = points(str, getLanguage(deep), str);
                    string = BetweenLanguage(str, deep);
                }
                else string = getLanguage(vector[3]);
            }
            else if(level == 1) {
                int n = getRandom(0,1);
                if (n == 0){
                    str = boucle(1,2,str,deep);
                    string = BetweenLanguage(str, deep);
                }
                else string = getLanguage(vector[3]);
            }
            return string;
        }
    }

    private String points (String s, String st, String str){
        return s + "." + st + "." + str;
    }

    private String boucle (int min, int max, String str, int deep){
        int m = getRandom(min,max);
        for (int i = 0; i < m; i++) {
            str = str + letters[getRandom(0, deep)];
            if (i != m-1)
                str = str + ".";
        }
        return str;
    }

    public String BetweenLanguage(String string, int deep){
        return points(getLanguage(deep),string,getLanguage(deep));

    }
    public String FinishWithLanguage(String string, int deep){
        return getLanguage(deep)+"."+string;
    }
    public String BeginWithLanguage(String string, int deep){
        return string+"."+getLanguage(deep);
    }
    private String getLanguage(int deep) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] oper = Arrays.copyOfRange(letters, 0, deep);
        for (int i = 0; i < oper.length+1; i++) {
            stringBuilder.append(letters[i]);
            if (i != oper.length){
                stringBuilder.append(Operations[0]);
            }
        }
        String string = kleen(stringBuilder.toString());
        return string;
    }

    private String kleen (String string){
        return PutParentheses(string)+"*";
    }

    public boolean SameUnion(String bloc1, String bloc2){
        if ((bloc1.charAt(0)==bloc2.charAt(0)&&(bloc1.charAt(2)==bloc2.charAt(2)))){
            return true;
        }
        else return bloc1.charAt(0) == bloc2.charAt(2) && (bloc1.charAt(2) == bloc2.charAt(0));
    }
    public boolean EqualBlocs(String bloc1, String bloc2){
        char operation1 = bloc1.charAt(1);
        char operation2 = bloc2.charAt(1);
        if (operation1 != operation2 ){
            return false;
        }
        else{
            if (operation1 == Operations[0]){
                return SameUnion(bloc1,bloc2);
            }
            else{
                return bloc1.charAt(0)==bloc2.charAt(0);
            }
        }
    }
    public boolean ContainsBloc(ArrayList<String> Blocs, String Bloc){
        for (String bloc: Blocs){
            if (EqualBlocs(Bloc,bloc)){
                return true;
            }
        }
        return false;
    }

    public int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt((max-min)+1)+min;
    }

    public void display(){
        System.out.println(blocs);
    }

    public String PutParentheses(String string){
        return "("+string+")";
    }

    public String KleeneStar(String string){
        if (remainingStars > 0 && (int)(Math.random())==0){
            remainingStars--;
            return PutParentheses(string)+'*';
        }
        return string;
    }

    public String EBloc(int deep){
        String string = "(" +
                letters[(int) (Math.random() * deep)] + "+ε)." +
                letters[(int) (Math.random() * deep)];
        return string;
    }

    public String EProblemGenerator(int deep){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(slot(FormulaGenerator(),EBloc(deep)));
        return stringBuilder.toString();
    }

    public String slot(String MainExpression, String expression){
        StringBuilder stringBuilder = new StringBuilder();
        int index = getRandom(MainExpression.length()/3,MainExpression.length()*2/3);
        stringBuilder.append(MainExpression.substring(0,index));
        stringBuilder.append(expression);
        stringBuilder.append(MainExpression.substring(index+1));
        return stringBuilder.toString();
    }

    public void BlocsFusion(){
        String bloc1 = blocs.get(getRandom(0,blocs.size()-1));
        blocs.remove(bloc1);
        String bloc2 = blocs.get(getRandom(0, blocs.size()-1));
        blocs.remove(bloc2);
        blocs.add(KleeneStar(combine(bloc1,bloc2)));
    }
    public String combine(String string1, String string2){
        char str1[] = string1.toCharArray();
        char str2[] = string2.toCharArray();
        int mil1 = (str1.length)/2;
        int mil2 = (str2.length)/2;
        char ope = Operations[(int) (Math.random()*Operations.length)];
        String combine = "";
        if (ope == str1[mil1]){
            if (ope == str2[mil2]){
                if (ope == Operations[0]){
                    String g1 = "";
                    String d1 = "";
                    String g2 = "";
                    String d2 = "";
                    for (int i = 0; i < mil1; i++) {
                        g1 = g1+str1[i];
                    }
                    for (int i = mil1+1; i < str1.length; i++) {
                        d1 = d1+str1[i];
                    }
                    for (int i = 0; i < mil2; i++) {
                        g2 = g2+str2[i];
                    }
                    for (int i = mil2+1 ; i < str2.length; i++) {
                        d2 = d2+str2[i];
                    }
                    if (g1.equals(g2) || g1.equals(d2)) combine = d1+ope+string2;
                    else if (d1.equals(g2) || d1.equals(d2)) combine = g1+ope+string2;
                    else combine = string1+ope+string2;
                }
                else combine = string1+ope+string2;
            }
            else {
                combine = string1+ope+PutParentheses(string2);
            }
        }
        else if (ope == str2[mil2]){
            combine = PutParentheses(string1)+ope+string2;
        }
        else combine = PutParentheses(string1)+ope+PutParentheses(string2);
        return combine;
    }

    public String WildGenerator(int ENbr,int deep){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < ENbr; i++) {
            stringBuilder.append(EProblemGenerator(deep)+Operations[1]);
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }
    public String FormulaGenerator() {
        while (blocs.size() > 1) {
            BlocsFusion();
        }
        return blocs.get(0);
    }


    public String methodeMain () throws Exception {
        String str = "";
        if (level == 1 || level == 2 || level == 3){
            int nb = getRandom(0,1);
            if (nb == 0){
                str = enleverPts(GenerateBlocL(vector[3], level));
                System.out.println(str);
                return str;
            }
            else {
                GenerateDistinctBlocs();
                str = enleverPts(FormulaGenerator());
                System.out.println(str);
                return str;

            }
        }
        else {
            GenerateDistinctBlocs();
            str = enleverPts(FormulaGenerator());
            System.out.println(str);
            return str;
        }
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

}
