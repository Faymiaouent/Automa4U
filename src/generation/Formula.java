package generation;

public class Formula {
    private int NbrOfLetters;
    private int NbrOperator;
    private int NbrCompartments;
    private static Character[] alphabet = {'a','b','c','d'};
    private static String[] Operators = {"","|"};
    private int level;


    public Formula(int level) {
        this.level = level;
        this.NbrOfLetters = (int)((Math.random()*level)+1);
        this.NbrOperator = (int)((Math.random()*level)%3+1);
        this.NbrCompartments = (int)((Math.random()*level)+1);
    }

    public char[] RandomAlphabets(int nbrOfLetters){
        char[] Alphabet = new char[nbrOfLetters];
        for (int i = 0; i < nbrOfLetters; i++) {
            Alphabet[i] = alphabet[(int)(Math.random()*alphabet.length)];
        }
        return Alphabet;
    }

    public String GenerateCompartment(char[] alphabet){
        int Operator = (int) (NbrOperator*Math.random())+1;
        String str = "";
        while(Operator>0){
            str = str+alphabet[(int) (Math.random()*(alphabet.length-1))];
            str = str+Operators[(int)(Math.random()*2)];
            str = str+alphabet[(int) (Math.random()*alphabet.length)];
            Operator--;
        }
        if(str.isEmpty()) {
            ParentheseVide(alphabet);
        }
        return str;
    }

    private void ParentheseVide(char[] alphabet) {
        int Operator = (int) (NbrOperator*Math.random())+1;
        String str = "";
        while(Operator>0){
            str = str+alphabet[(int) (Math.random()*alphabet.length)];
            str = str+Operators[(int)(Math.random()*2)];
            str = str+alphabet[(int) (Math.random()*alphabet.length)];
            Operator--;
        }
        if(str.isEmpty()) {
            ParentheseVide(alphabet);
        }
    }


    private void DoubleOccurrence(char[] alphabet){

    }

    public String Generate(){
        char[] alphabet = RandomAlphabets(NbrOfLetters);
        String formula = "";
        for (int i = 0; i < NbrCompartments; i++) {
            int random = (int)(Math.random());
            String str = "(";
            str = str+GenerateCompartment(alphabet);
            str = str+")";
            if (random==0){
                str=str+"*";
            }
            formula = formula+str+Operators[(int) (Math.random()*2)];
        }
        return formula.substring(0,formula.length()-1);
    }

    public String FinalGenerate(){
        if (level<5){
            return Generate();
        }
        else {
            String str = "";
            for (int i = 0; i < level/3; i++) {
                int random = (int)(Math.random());
                String string = "("+Generate()+")";
                if (random==1){
                    str = str+string+"*";
                }
                else {
                    str = str+string;
                }
            }
            return str;
        }
    }
}


