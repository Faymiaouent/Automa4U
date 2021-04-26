package generation;

import java.util.Random;

public class FRtoAUTO {

    private int level;
    private String a;

    public void setA(String a) {
        this.a = a;
    }

    public FRtoAUTO (int level) throws Exception {
        if (level<1||level>10){
            throw new Exception("The level must be between 1 and 10");
        }
        else {
            this.level = level;
        }
    }

    public int getRandom(int min, int max){
        Random random = new Random();
        return random.nextInt((max-min)+1)+min;
    }

    public String ANDCombine(String string1, String string2){
        return enleverPtFin(string1)+" et "+string2;
    }
    public String ORCombine(String string1, String string2){
        return enleverPtFin(string1)+" ou "+string2;
    }

    public String enleverPtFin (String str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
        }
        if(sb.length() > 0){
            if (sb.charAt(sb.length()-1) == '.'){
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return sb.toString();
    }

    public String[] SetLevel() throws Exception {
        String[] temp;
        String[] strs1;
        String[] strs2;
        String[] strings;
        int i;
        int j;
        switch (level){
            case 1:
                strings = init(1);
                break;
            case 2:
                i = getRandom(1,2);
                strings = init(i);
                break;
            case 3:
                i = getRandom(3,4);
                strings = init(i);
                break;
            case 4:
                i = getRandom(5,6);
                strings = init(i);
                break;
            case 5:
                j = getRandom(0,1);
                if (j == 0){
                    i = getRandom(7,8);
                    strings = init(i);
                }
                else {
                    temp = initCombine(1);
                    strs1 = new String[]{temp[0],temp[1]};
                    strs2 = new String[]{temp[2],temp[3]};
                    strings = new String[]{ORCombine(strs1[0],strs2[0]),strs1[1],strs2[1]};
                }
                break;
            case 6:
                j = getRandom(0,1);
                if (j == 0){
                    i = getRandom(9,10);
                    strings = init(i);
                }
                else {
                    temp = initCombine(2);
                    strs1 = new String[]{temp[0],temp[1]};
                    strs2 = new String[]{temp[2],temp[3]};
                    strings = new String[]{ORCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"O"};
                }
                break;
            case 7:
                temp = initCombine(3);
                strs1 = new String[]{temp[0],temp[1]};
                strs2 = new String[]{temp[2],temp[3]};
                j = getRandom(0,1);
                strings = new String[]{ORCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"O"};
                break;
            case 8:
                temp = initCombine(4);
                strs1 = new String[]{temp[0],temp[1]};
                strs2 = new String[]{temp[2],temp[3]};
                j = getRandom(0,1);
                if (j == 0) strings = new String[]{ORCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"O"};
                else strings = new String[]{ANDCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"A"};
                break;
            case 9:
                temp = initCombine(5);
                strs1 = new String[]{temp[0],temp[1]};
                strs2 = new String[]{temp[2],temp[3]};
                j = getRandom(0,1);
                if (j == 0) strings = new String[]{ORCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"O"};
                else strings = new String[]{ANDCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"A"};
                break;
            case 10:
                temp = initCombine(6);
                strs1 = new String[]{temp[0],temp[1]};
                strs2 = new String[]{temp[2],temp[3]};
                j = getRandom(0,1);
                if (j == 0) strings = new String[]{ORCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"O"};
                else strings = new String[]{ANDCombine(strs1[0],strs2[0]),strs1[1],strs2[1],"A"};
                break;
            default:
                strings = new String[]{"","","",""};
        }
        return strings;
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

    private String[] init (int niveau) throws Exception {
        FRtoRE fRtoRE = new FRtoRE(niveau);
        String[] resul1 = fRtoRE.goToRE();
        String[] resul = new String[]{"",""};
        for (int i = 0; i < resul1.length-1; i++) {
            resul[i] = resul1[i];
        }
        setA(resul1[resul1.length-1]);
        return resul;
    }
    private String[] initCombine (int niveau) throws Exception {
        FRtoRE fRtoRE = new FRtoRE(niveau);
        String[] resul1 = fRtoRE.goToCombine(niveau);
        String[] resul = new String[]{"","","",""};
        for (int i = 0; i < resul1.length-1; i++) {
            resul[i] = resul1[i];
        }
        setA(resul1[resul1.length-1]);
        return resul;
    }

    public String[] generate() throws Exception {
        String str = "Construire un automate qui reconnait ";
        String[] resul = SetLevel();
        resul[0] = str + resul[0] + " Sur l'alphabet "+a+".";
        return resul;
    }
    public void toString (String[] strings){
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
    public void toS (String[] strings){
        for (int i = 0; i < strings.length; i++) {
            if (! strings[i].equals("O")){
                if (! strings[i].equals("A")){
                    System.out.println(strings[i]);
                }
            }
        }
    }

    /*
    à appeler dans le main ou une autre classe pour savoir s'il s'agit d'une seule phrase
    ou d'une concaténation/union :

        FRtoAUTO fRtoAUTO = new FRtoAUTO(8);
        String[] monResultat = fRtoAUTO.generate();
        fRtoAUTO.toString(monResultat);
        if (monResultat.length == 4){
            String maReg1 = monResultat[1];
            String maReg2 = monResultat[2];
            if (monResultat[3].equals("O")){
                // faire union de maReg1 et maReg2
                System.out.println("je suis une union");
            }
            else if (monResultat[3].equals("A")){
                // faire concaténation de maReg1 et maReg2
                System.out.println("je suis une concaténation");
            }
        }
        else if (monResultat.length == 2){
            // faire l'automate normal
        }
     */


}
