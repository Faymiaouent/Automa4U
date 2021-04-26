package generation;

import java.util.Random;

public class Sentence {
    private int level;
    Character[] alphabet = {'a','b','c','d','e'};
    String[] sentences1 = {"tous les mots","les finissant par '_'","les mots commençant par '_'"};
    String[] sentences2 = {"les mots avec un nombre pair de '_' ","les mots avec un nombre impair de '_' " ,"les mots qui contient le facteur _","les mots avec au moins","les mots ayant que des _","les mots dont toutes les lettres sont égales","les mots qui contient toutes les lettres sont différentes"};
    String[] sentences3 = {"les mots dont la longueur est divisible par _","les blocs de '_' consécutifs sont tous de longeur multiple de _"};
    String[] sentences4 = {"les mots tels que si le mot contient un '_' alors il doit y avoir, dans la suite du mot, un '_'","tous les '_' (s'il y en a) sont avant tous les '_'"};


    public Sentence(int level) {
        this.level = level;
    }

    public String ANDCombine(String string1, String string2){
        return string1+" et "+string2;
    }

    public String ORCombine(String string1, String string2){
        return string1+" ou "+string2;
    }

    public String generate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Construire un automate déterministe qui reconnait  ");
        stringBuilder.append(read(SetLevel()));
        return stringBuilder.toString();
    }

    public int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt((max-min)+1)+min;
    }

    public String ChosenProblem(int SentencesNbr){
        if (getRandom(0,1)==0){
            if (SentencesNbr == 1){
                return ANDCombine(sentences1[getRandom(0, sentences1.length-1)],sentences2[getRandom(0, sentences2.length-1)]);
            }
            else if (SentencesNbr == 2){
                return ANDCombine(sentences2[getRandom(0, sentences2.length-1)],sentences3[getRandom(0, sentences3.length-1)] );
            }
            else{
                return ANDCombine(sentences3[getRandom(0, sentences3.length-1)],sentences4[getRandom(0, sentences4.length-1)] );
            }
        }
        else {
            if (SentencesNbr == 1){
                return ORCombine(sentences1[getRandom(0, sentences1.length-1)],sentences2[getRandom(0, sentences2.length-1)]);
            }
            else if (SentencesNbr == 2){
                return ORCombine(sentences2[getRandom(0, sentences2.length-1)],sentences3[getRandom(0, sentences3.length-1)] );
            }
            else {
                return ORCombine(sentences3[getRandom(0, sentences3.length-1)],sentences4[getRandom(0, sentences4.length-1)] );
            }
        }
    }


    public String SetLevel(){
        switch (level){
            case 1:
                return sentences1[getRandom(0,sentences1.length-1)];
            case 2:
                return sentences2[getRandom(0,sentences2.length-1)];
            case 3:
                return sentences3[getRandom(0,sentences3.length-1)];
            case 4:
                return sentences4[getRandom(0, sentences4.length)-1];
            case 5:
                return ORCombine(sentences1[getRandom(0,sentences1.length-1)],sentences2[getRandom(0, sentences2.length-1)]);
            case 6:
                return ORCombine(sentences1[getRandom(0,sentences2.length-1)],sentences3[getRandom(0, sentences3.length-1)] );
            case 7:
                return ChosenProblem(1);
            case 8:
                return ChosenProblem(2);
            case 9:
                return ChosenProblem(3);
            case 10:
                if (getRandom(0,1)==1){
                    return ANDCombine(ChosenProblem(3),sentences1[getRandom(0, sentences1.length-1)]);
                }
                else{
                    return ORCombine(ORCombine(sentences1[getRandom(0, sentences1.length-1)],sentences2[getRandom(0, sentences2.length-1)]),sentences3[getRandom(0, sentences3.length-1)]);
                }
            default:
                return null;
        }
    }

    public String GenerateWord(int length, Character[] letters){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <length ; i++) {
            stringBuilder.append(letters[getRandom(0,letters.length-1)]);
        }
        return stringBuilder.toString();
    }



    public String read(String string){
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i)=='\''){

                string = string.substring(0,i)+GenerateWord(getRandom(1,2),alphabet)+string.substring(i+3);
            }
            if (string.charAt(i)== '_'){
                string = string.substring(0,i)+getRandom(0,5)+string.substring(i+1);
            }
        }
        return string;
    }

    public static void main(String[] args) {
        Sentence sentence = new Sentence(9);
        System.out.println(sentence.generate());
    }
}

