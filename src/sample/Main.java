package sample;

import automaton.Automaton;
import automaton.BasicOperations;
import automaton.RegExp;
import generation.FRtoAUTO;
import generation.FRtoRE;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import generation.MoFormula;
import generation.ParsingDOT;

import java.io.*;
import java.util.Random;
import java.util.Scanner;


public class Main extends Application implements EventHandler<ActionEvent> {
    Background background = new Background(new BackgroundImage(new Image(getClass().getResource("/sample/fond.png").toString()),null,null,null,null));
    Stage window;
    /**
     * Premiere scène (accueil)
     */
    int numprobleme;
    int nbProbleme = 30;
    int variable;
    int variablenfa;
    int variablergxdfa;
    int variablergxnfa;
    int variablefr2regex;
    int variablefr2dfa;
    int variablefr2nfa;
    Random rn = new Random();
    int borne=0;
    int level;
    Button oui = new Button("+");
    Button non = new Button("-");
    int compteur = 1;
    HBox hbox = new HBox(20);
    HBox hbox1 = new HBox(20);
    Button choisir = new Button("choix");
    int algo;
    Label valeur = new Label();
    Label lselection = new Label();
    ComboBox<Integer> choix = new ComboBox<>();
    ImageView image = new ImageView(new Image(getClass().getResource("/sample/Accueil2.png").toString()));
    ToolBar toolBar = new ToolBar();
    BorderPane layout = new BorderPane();
    Scene accueil = new Scene(layout,1000,600);


    /**
     * Deuxième scène (Choix entre déterministe et non déterministe)
     */
    HBox hbox3 = new HBox(20);
    ToolBar tooltitre = new ToolBar();
    Button todfa = new Button("DFA");
    Button tonfa = new Button("NFA");
    Label question = new Label();
    VBox vboxvide = new VBox(20);
    BorderPane layout1 = new BorderPane();
    GridPane grid = new GridPane();
    Scene sceneSelection = new Scene(layout1,1000,600);

    /**
     * Troisième scène (DFA -> regex)
     */
    TextField textedfa = new TextField();
    ImageView dfa = new ImageView(new Image("sample/logo.png"));
    Button toexitdfa = new Button("Retour");
    Button toAfficherregex = new Button("Réponse");
    Button submitregex = new Button("Soumettre");
    Button newDfa = new Button("Recommencer");
    HBox hboximage = new HBox(20);
    HBox hboxdfa2regex = new HBox(20);
    Label consigne = new Label();
    Label reponseregexdfa = new Label();
    ToolBar tooltitre1 = new ToolBar();
    GridPane gridImage = new GridPane();
    BorderPane layoutdfa2regex = new BorderPane();
    Scene dfa2regex = new Scene(layoutdfa2regex,1200,800);

    /**
     * Quatrième scène (NFA->regex)
     */
    TextField textenfa = new TextField();
    ImageView nfa = new ImageView(new Image("sample/logo.png"));
    Button toexitNfa = new Button("Retour");
    Button toanswerRgxnfa = new Button("Réponse");
    Button tosubmitRgxnfa = new Button("Soumettre");
    Button newNfa = new Button("Recommencer");
    Label reponseregexnfa = new Label();
    HBox hboxnfa2regex = new HBox(20);
    Label consignenfa = new Label();
    ToolBar tooltitre2 = new ToolBar();
    GridPane gridImageNFA = new GridPane();
    BorderPane layoutnfa2regex = new BorderPane();
    Scene nfa2regex = new Scene(layoutnfa2regex, 1200,800);



    /**
     * Cinquième scène (Choix du type de solution pour Regex -> DFA)
     */
    HBox hboxchoix = new HBox(20);
    ToolBar toolTitreChoix = new ToolBar();
    Button toDFAfrgx = new Button("DFA");
    Button toNFArgx = new Button("NFA");
    Label qst = new Label();
    BorderPane layoutchoix = new BorderPane();
    GridPane gridChoix = new GridPane();
    Scene sceneChoix = new Scene(layoutchoix,1000,600);

    TextField texteRegex = new TextField();
    Button toExitRgx = new Button("Retour");
    Button toAfficherDFA = new Button("Réponse");

    /**
     * Sixième scène(Regex->DFA)
     */
    Button toexitrgxdfa = new Button("Retour");
    ImageView dfasolution = new ImageView(new Image("sample/logo.png"));
    Label regexdfa = new Label();
    Button toanswerdfa = new Button("Voir réponse");
    Button newrgxdfa = new Button("Recommencer");
    HBox hBoxrgx2dfa = new HBox(20);
    Label consignergxdfa = new Label();
    ToolBar tooltitrergxdfa = new ToolBar();
    GridPane gridImagergxdfa = new GridPane();
    BorderPane layoutrgx2dfa = new BorderPane();
    Scene regex2dfa = new Scene(layoutrgx2dfa,1200,800);

    /**
     * Septième scène(Regex->NFA)
     */
    Button toexitrgxnfa = new Button("Retour");
    ImageView nfasolution = new ImageView(new Image("sample/logo.png"));
    Label regexnfa = new Label();
    Button toanswernfa = new Button("Voir réponse");
    Button newrgxnfa = new Button("Recommencer");
    HBox hBoxrgx2nfa = new HBox(20);
    Label consignergxnfa = new Label();
    ToolBar tooltitreRGXnfa = new ToolBar();
    GridPane gridImageRGXnfa = new GridPane();
    BorderPane layoutrgx2nfa = new BorderPane();
    Scene regex2nfa = new Scene(layoutrgx2nfa,1200,800);

    /**
     * Huitième scène(FR->REGEX)
     */
    Button toexitfrregex = new Button("Retour");
    String attentefr2regex = "Cliquez sur \"Recommencer\"";
    Label debutfr2regex = new Label();
    Label regexsolution = new Label();
    Label enonceFrancais = new Label();
    Button reponsefr2regex = new Button("Voir réponse");
    Button newEnonce = new Button("Recommencer");
    HBox hboxfr2regex = new HBox();
    Label consignefr2regex = new Label();
    ToolBar tooltitrefr2regex = new ToolBar();
    GridPane gridcentrefr2regex = new GridPane();
    BorderPane layoutfr2regex = new BorderPane();
    Scene fr2regex = new Scene(layoutfr2regex,1200,800);


    /**
     * Neuvième scène (CHOIX FR->DFA OU FR->NFA)
     */
    HBox hbox4 = new HBox(20);
    ToolBar tooltitrechoixfr = new ToolBar();
    Button tofrdfa = new Button("DFA");
    Button tofrnfa = new Button("NFA");
    Label questionfr = new Label();
    BorderPane layoutfrdfa = new BorderPane();
    GridPane gridfrdfanfa = new GridPane();
    Scene sceneselectionfrdfa = new Scene(layoutfrdfa,1000,600);

    /**
     *
     * Dixième scène (FR -> DFA)
     */
    Button toexitfrdfa = new Button("Retour");
    String attentefr2dfa = "Cliquez sur \"Recommencer\"";
    Label debutfr2dfa = new Label();
    ImageView frdfasolution = new ImageView(new Image("sample/logo.png"));
    Label enoncefrancaisdfa = new Label();
    Button reponsefr2dfa = new Button("Voir réponse");
    Button newenoncefr2dfa = new Button("Recommencer");
    //HBox hboxfr2dfa = new HBox();
    Label consignefr2dfa = new Label();
    ToolBar tooltitrefr2dfa = new ToolBar();
    GridPane gridcentrefr2dfa = new GridPane();
    BorderPane layoutfr2dfa = new BorderPane();
    Scene fr2dfa = new Scene(layoutfr2dfa,1200 ,800);

    /**
     * Onzième scène (FR -> NFA)
     */
    Button toexitfrnfa = new Button("Retour");
    ImageView frnfasolution = new ImageView(new Image("sample/logo.png"));
    Label enoncefrancaisnfa = new Label();
    Button reponsefr2nfa = new Button("Voir réponse");
    Button newenoncefr2nfa = new Button("Recommencer");
    Label consignefr2nfa = new Label();
    ToolBar tooltitrefr2nfa = new ToolBar();
    GridPane gridcentrefr2nfa = new GridPane();
    BorderPane layoutfr2nfa = new BorderPane();
    Scene fr2nfa = new Scene(layoutfr2nfa,1200,800);

    /**
     * Douzième scène (Résultat de l'étudiant)
     */
    ToolBar toolresultat = new ToolBar();
    Button accepter = new Button("Continuer");
    TextField resultat = new TextField();
    HBox hboxresultat = new HBox();
    Label texteresultat = new Label();
    GridPane gridresultat = new GridPane();
    BorderPane layoutresultat = new BorderPane();
    Scene sceneresultat = new Scene(layoutresultat,1000,600);




    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Automa4U");
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        /**
         * Première scène (accueil)
         */
        valeur.setText(Integer.toString(compteur));
        lselection.setText(String.valueOf(algo));

        choix.getItems().add(1);
        choix.getItems().add(2);
        choix.getItems().add(3);
        choix.getItems().add(4);
        choix.setValue(1);

        oui.setOnAction(this);
        non.setOnAction(this);
        choisir.setOnAction(this);

        hbox1.getChildren().addAll(image);
        hbox.getChildren().addAll(oui, non, valeur, choix, choisir, lselection);

        toolBar.getItems().addAll(hbox,hbox1);


        layout.setTop(toolBar);
        layout.setCenter(image);


        /**
         * Deuxième scène (choix déterministe et non déterministe)
         */
        todfa.setPrefSize(200,100);
        tonfa.setPrefSize(200,100);
        question.setText("==> Choisissez le type d'automate :");
        question.setFont(new Font(50));
        tooltitre.getItems().addAll(question);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.add(todfa,0,0);
        grid.add(tonfa,1,0);
        grid.setBackground(background);
        layout1.setTop(tooltitre);
        layout1.setCenter(grid);
        todfa.setOnAction(this);
        tonfa.setOnAction(this);


        /**
         * Troisième scène (DFA -> regex)
         */
        consigne.setText("Ecrire l'expression régulière correspondante à l'automate :");
        consigne.setFont(new Font(35));
        //question.setAlignment(Pos.TOP_CENTER);
        tooltitre1.getItems().addAll(consigne);
        layoutdfa2regex.setTop(tooltitre1);
        gridImage.setAlignment(Pos.CENTER);
        gridImage.add(dfa,0,0);
        gridImage.setBackground(background);
        layoutdfa2regex.setCenter(gridImage);
        toexitdfa.setPrefSize(200,100);
        submitregex.setPrefSize(200,100);
        toAfficherregex.setPrefSize(200,100);
        newDfa.setPrefSize(200,100);
        textedfa.setMaxSize(300,30);
        hboxdfa2regex.getChildren().addAll(toexitdfa, submitregex, toAfficherregex, newDfa);
        hboxdfa2regex.setBackground(background);
        layoutdfa2regex.setBottom(hboxdfa2regex);
        newDfa.setOnAction(this);
        toexitdfa.setOnAction(this);
        toAfficherregex.setOnAction(this);



        /**
         * Quatrième scène (NFA -> regex)
         */
        consignenfa.setText("Ecrire l'expression régulière correspondante à l'automate :");
        consignenfa.setFont(new Font(35));
        newNfa.setPrefSize(100,100);
        toexitNfa.setPrefSize(100,100);
        toanswerRgxnfa.setPrefSize(100,100);
        tooltitre2.getItems().addAll(consignenfa, newNfa, toanswerRgxnfa, toexitNfa);
        layoutnfa2regex.setTop(tooltitre2);
        gridImageNFA.setAlignment(Pos.CENTER);
        gridImageNFA.add(nfa,0,0);
        gridImageNFA.setBackground(background);
        layoutnfa2regex.setCenter(gridImageNFA);
        tosubmitRgxnfa.setPrefSize(200,100);
        textenfa.setPrefSize(200,30);
        hboxnfa2regex.getChildren().addAll(textenfa, tosubmitRgxnfa);
        hboxnfa2regex.setBackground(background);
        layoutnfa2regex.setBottom(hboxnfa2regex);
        newNfa.setOnAction(this);
        toexitNfa.setOnAction(this);
        toanswerRgxnfa.setOnAction(this);
        tosubmitRgxnfa.setOnAction(this);


        /**
         * Cinquième scène (Choix du type de solution pour Regex -> Automate)
         */
        toDFAfrgx.setPrefSize(200,100);
        toNFArgx.setPrefSize(200,100);
        hboxchoix.getChildren().addAll(toDFAfrgx,toNFArgx);
        qst.setText("==> Choisissez le type d'automate :");
        qst.setFont(new Font(50));
        toolTitreChoix.getItems().addAll(question);
        gridChoix.setAlignment(Pos.CENTER);
        gridChoix.setHgap(20);
        gridChoix.add(toDFAfrgx,0,0);
        gridChoix.add(toNFArgx,1,0);
        gridChoix.setBackground(background);
        layoutchoix.setTop(toolTitreChoix);
        layoutchoix.setCenter(gridChoix);
        toDFAfrgx.setOnAction(this);
        toNFArgx.setOnAction(this);
        //layout1.getChildren().add(hbox3);
        //layout1.setAlignment(hbox3, Pos.BOTTOM_CENTER);

        /**
         * Sixième scène (Regex -> DFA)
         */
        consignergxdfa.setText("Ecrire sur votre feuille l'automate déterministe correspondant à l'expression :");
        consignergxdfa.setFont(new Font(35));
        tooltitrergxdfa.getItems().addAll(consignergxdfa);
        layoutrgx2dfa.setTop(tooltitrergxdfa);
        gridImagergxdfa.setAlignment(Pos.CENTER);
        gridImagergxdfa.add(dfasolution,0,0);
        gridImagergxdfa.setBackground(background);
        layoutrgx2dfa.setCenter(gridImagergxdfa);
        toexitrgxdfa.setPrefSize(300,100);
        toanswerdfa.setPrefSize(300,100);
        newrgxdfa.setPrefSize(300,100);
        hBoxrgx2dfa.getChildren().addAll(toexitrgxdfa, toanswerdfa, newrgxdfa);
        hBoxrgx2dfa.setBackground(background);
        layoutrgx2dfa.setBottom(hBoxrgx2dfa);
        toexitrgxdfa.setOnAction(this);
        toanswerdfa.setOnAction(this);
        newrgxdfa.setOnAction(this);

        /**
         * Septième scène (Regex -> NFA)
         */
        consignergxnfa.setText("Ecrire sur votre feuille l'automate non déterministe correspondant à l'expression :");
        consignergxnfa.setFont(new Font(30));
        tooltitreRGXnfa.getItems().addAll(consignergxnfa, toexitrgxnfa);
        layoutrgx2nfa.setTop(tooltitreRGXnfa);

        gridImageRGXnfa.setAlignment(Pos.CENTER);
        gridImageRGXnfa.add(nfasolution,0,0);
        gridImageRGXnfa.setBackground(background);
        layoutrgx2nfa.setCenter(gridImageRGXnfa);

        toexitrgxnfa.setPrefSize(100,100);
        toanswernfa.setPrefSize(300,100);
        newrgxnfa.setPrefSize(300,100);
        hBoxrgx2nfa.getChildren().addAll(toanswernfa,newrgxnfa);
        hBoxrgx2nfa.setBackground(background);
        layoutrgx2nfa.setBottom(hBoxrgx2nfa);
        toexitrgxnfa.setOnAction(this);
        toanswernfa.setOnAction(this);
        newrgxnfa.setOnAction(this);

        /**
         * Huitième scène (FR -> Regex)
         */
        consignefr2regex.setText("Ecrire l'expression régulière de :");
        consignefr2regex.setFont(new Font(35));
        tooltitrefr2regex.getItems().addAll(consignefr2regex);
        layoutfr2regex.setTop(tooltitrefr2regex);
        gridcentrefr2regex.setAlignment(Pos.CENTER);
        debutfr2regex.setText(attentefr2regex);
        gridcentrefr2regex.setAlignment(Pos.CENTER);
        gridcentrefr2regex.setBackground(background);
        gridcentrefr2regex.add(debutfr2regex,0,0);
        layoutfr2regex.setCenter(gridcentrefr2regex);
        toexitfrregex.setPrefSize(300,100);
        reponsefr2regex.setPrefSize(300,100);
        newEnonce.setPrefSize(300,100);
        hboxfr2regex.getChildren().addAll(toexitfrregex, reponsefr2regex, newEnonce);
        hboxfr2regex.setBackground(background);
        layoutfr2regex.setBottom(hboxfr2regex);
        toexitfrregex.setOnAction(this);
        reponsefr2regex.setOnAction(this);
        newEnonce.setOnAction(this);

        /**
         * Neuvième scène (CHOIX FR->DFA ou FR->NFA)
         */

        tofrdfa.setPrefSize(300,100);
        tofrnfa.setPrefSize(300,100);
        //hbox4.getChildren().addAll(tofrnfa,tofrnfa);
        questionfr.setText("==> Choisissez le type d'automate :");
        questionfr.setFont(new Font(50));
        tooltitrechoixfr.getItems().addAll(questionfr);
        gridfrdfanfa.setAlignment(Pos.CENTER);
        gridfrdfanfa.setHgap(20);
        gridfrdfanfa.add(tofrdfa,0,0);
        gridfrdfanfa.add(tofrnfa,1,0);
        gridfrdfanfa.setBackground(background);
        layoutfrdfa.setTop(tooltitrechoixfr);
        layoutfrdfa.setCenter(gridfrdfanfa);
        tofrdfa.setOnAction(this);
        tofrnfa.setOnAction(this);

        /**
         * Dixième scène (FR -> DFA)
         */
        consignefr2dfa.setText("Ecrire l'automate du texte :");
        consignefr2dfa.setFont(new Font(30));
        toexitfrdfa.setPrefSize(200,100);
        reponsefr2dfa.setPrefSize(300,100);
        newenoncefr2dfa.setPrefSize(300,100);
        tooltitrefr2dfa.getItems().addAll(consignefr2dfa, toexitfrdfa, reponsefr2dfa, newenoncefr2dfa);
        layoutfr2dfa.setTop(tooltitrefr2dfa);
        gridcentrefr2dfa.setAlignment(Pos.CENTER);
        frdfasolution.setFitHeight(600);
        frdfasolution.setFitWidth(900);
        frdfasolution.setPreserveRatio(true);
        frdfasolution.setSmooth(true);
        frdfasolution.setCache(true);
        gridcentrefr2dfa.add(frdfasolution,0,0);
        gridcentrefr2dfa.setBackground(background);
        layoutfr2dfa.setCenter(gridcentrefr2dfa);
        toexitfrdfa.setOnAction(this);
        reponsefr2dfa.setOnAction(this);
        newenoncefr2dfa.setOnAction(this);


        /**
         * Onzième scène (FR -> NFA)
         */
        consignefr2nfa.setText("Ecrire l'automate du texte :");
        consignefr2nfa.setFont(new Font(30));
        toexitfrnfa.setPrefSize(300,100);
        reponsefr2nfa.setPrefSize(300,100);
        newenoncefr2nfa.setPrefSize(300,100);
        tooltitrefr2nfa.getItems().addAll(consignefr2nfa,toexitfrnfa,reponsefr2nfa,newenoncefr2nfa);
        layoutfr2nfa.setTop(tooltitrefr2nfa);
        gridcentrefr2nfa.setAlignment(Pos.CENTER);
        frnfasolution.setFitHeight(600);
        frnfasolution.setFitWidth(900);
        frnfasolution.setPreserveRatio(true);
        frnfasolution.setSmooth(true);
        frnfasolution.setCache(true);
        gridcentrefr2nfa.add(frnfasolution,0,0);
        gridcentrefr2nfa.setBackground(background);
        layoutfr2nfa.setCenter(gridcentrefr2nfa);
        toexitfrnfa.setOnAction(this);
        reponsefr2nfa.setOnAction(this);
        newenoncefr2nfa.setOnAction(this);

        /**
         * Douzième scène (Résultat de l'étudiant)
         */
        texteresultat.setText("Ecrire ici votre score");
        texteresultat.setFont(new Font(50));
        accepter.setPrefSize(1000,100);
        hboxresultat.getChildren().addAll(accepter);
        resultat.setPrefSize(200,20);
        layoutresultat.setBottom(hboxresultat);
        layoutresultat.setTop(toolresultat);
        gridresultat.setBackground(background);
        gridresultat.add(resultat,0,0);
        gridresultat.setAlignment(Pos.CENTER);
        layoutresultat.setCenter(gridresultat);
        accepter.setOnAction(this);



        window.setScene(accueil);
        window.setTitle("AUTOMA4U : Accueil");
        window.getIcons().add(new Image("/sample/logo.PNG"));
        window.show();
    }

    @Override
    public void handle(ActionEvent actionevent) {
        if(actionevent.getSource()== oui){
            if(compteur<10) {
                compteur++;
                valeur.setText(String.valueOf(compteur));
            }
        }
        if(actionevent.getSource()== non){
            if(compteur>1) {
                compteur = compteur - 1;
                valeur.setText(String.valueOf(compteur));
            }
        }
        if(actionevent.getSource()==choisir){
            lselection.setText(String.valueOf(getChoice(choix)));
            algo = getChoice(choix);
            System.out.println(compteur);
            switch(algo){
                case(1):
                    window.setScene(sceneSelection);
                    window.setTitle("AUTOMA4U : Choisir le type d'automate");
                    break;
                case(2):
                    window.setScene(sceneChoix);
                    window.setTitle("AUTOMA4U : Choisir le type d'automate");
                    break;
                case(3):
                    window.setScene(sceneselectionfrdfa);
                    window.setTitle("AUTOMA4U : Choisir le type d'automate");
                    break;
                case(4):
                    numprobleme=7;
                    window.setScene(fr2regex);
                    window.setTitle("AUTOMA4U : Français vers expression régulière");
                    try {
                        for (int i = 0; i < 21 ; i++) {
                            createfr2regex(String.valueOf(i));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
        if(actionevent.getSource()==todfa){
            try {
                for (int i = 0; i < 21 ; i++) {
                    createDfa(String.valueOf(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            numprobleme=1;
            window.setScene(dfa2regex);
            window.setTitle("AUTOMA4U : Automate déterministe vers expression régulière");
        }
        if(actionevent.getSource()==toDFAfrgx){
            try {
                for (int i = 0; i < 21 ; i++) {
                    createDfa(String.valueOf(i));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            numprobleme=3;
            window.setScene(regex2dfa);
            window.setTitle("AUTOMA4U : Expression régulière vers automate déterministe");
        }
        if(actionevent.getSource()==tofrdfa){
            try{
                for (int i = 0; i < 21 ; i++) {
                    createfr2dfa(String.valueOf(i));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            numprobleme=5;
            window.setScene(fr2dfa);
            window.setTitle("AUTOMA4U : Français vers automate déterministe");
        }
        if(actionevent.getSource()==tofrnfa){
            try{
                for (int i = 0; i < 21 ; i++) {
                    createfr2nfa(String.valueOf(i));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            numprobleme=6;
            window.setScene(fr2nfa);
            window.setTitle("AUTOMA4U : Français vers automate non déterministe");
        }

        if (actionevent.getSource()==tonfa){
            try {
                for (int i = 0; i < 21 ; i++) {
                    createNfa(String.valueOf(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            numprobleme=2;
            window.setScene(nfa2regex);
            window.setTitle("AUTOMA4U : Automate non déterministe vers expression régulière");
        }

        if(actionevent.getSource()==toNFArgx){
            try {
                for (int i = 0; i < 21 ; i++) {
                    createNfa(String.valueOf(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            numprobleme=4;
            window.setScene(regex2nfa);
            window.setTitle("AUTOMA4U : Expression régulière vers automate non déterministe");
        }


        if(actionevent.getSource() == accepter){
            String chaine = resultat.getText();
            int score = Integer.parseInt(chaine);
            switch(numprobleme){
                case(1):
                case(3):
                    if(score<10 && compteur > 1){
                        compteur-=1;
                        difficultyDFArgx();
                    }
                    else if(score>15 && compteur <10){
                        compteur+=1;
                        difficultyDFArgx();
                    }
                    else if((score>=10 && score <15)||compteur==1||compteur==10){
                        difficultyDFArgx();
                    }
                    break;
                case(2):
                case(4):
                    if(score<10 && compteur > 1){
                        compteur-=1;
                        difficultyNFArgx();
                    }
                    else if(score>15 && compteur <10){
                        compteur+=1;
                        difficultyNFArgx();
                    }
                    else if((score>=10 && score <15)||compteur==1||compteur==10){
                        difficultyNFArgx();
                    }
                    break;
                case(5):
                    if(score<10 && compteur > 1){
                        compteur-=1;
                        difficultyFRdfa();
                    }
                    else if(score>15 && compteur <10){
                        compteur+=1;
                        difficultyFRdfa();
                    }
                    else if((score>=10 && score <15)||compteur==1||compteur==10){
                        difficultyFRdfa();
                    }
                    break;
                case(6):
                    if(score<10 && compteur > 1){
                        compteur-=1;
                        difficultyFRnfa();
                    }
                    else if(score>15 && compteur <10){
                        compteur+=1;
                        difficultyFRnfa();
                    }
                    else if((score>=10 && score <15)||compteur==1||compteur==10){
                        difficultyFRnfa();
                    }
                    break;
                case(7):
                    if(score<10 && compteur > 1){
                        compteur-=1;
                        difficultyFRrgx();
                    }
                    else if(score>15 && compteur <10){
                        compteur+=1;
                        difficultyFRrgx();
                    }
                    else if((score>=10 && score <15)||compteur==1||compteur==10){
                        difficultyFRrgx();
                    }
                    break;


            }
        }

        /**
         * Problème (** DFA -> REGEX **)
         */
        if(actionevent.getSource()==newDfa){
            if(borne<5) {
                    gridImage.getChildren().remove(dfa);
                    gridImage.getChildren().remove(reponseregexdfa);
                    System.out.println(borne);
                    File img = new File("src/sample/ressources/dfa" + borne + ".png");
                    variable = borne;
                    borne++;
                    InputStream isImage = null;
                    try {
                        isImage = (InputStream) new FileInputStream(img);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    dfa.setImage(new Image(isImage));
                    gridImage.add(dfa, 0, 0);
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }

        if(actionevent.getSource()==toAfficherregex){
            File file = new File("src/sample/ressources/regexdfa"+variable+".txt");
            try {
                Scanner scanner = new Scanner(file);
                String regex = scanner.nextLine();
                gridImage.getChildren().remove(dfa);
                gridImage.getChildren().remove(reponseregexdfa);
                reponseregexdfa.setText(regex);
                reponseregexdfa.setFont(new Font(50));
                gridImage.add(reponseregexdfa,0,0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(actionevent.getSource()==toexitdfa){
            borne=0;
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }

        /**
         * PROBLEME (** NFA -> REGEX **)
         */

        if(actionevent.getSource()==newNfa){
            if(borne<5) {
                gridImageNFA.getChildren().remove(nfa);
                gridImageNFA.getChildren().remove(reponseregexnfa);
                borne++;
                File img = new File("src/sample/ressources/nfa" + borne + ".png");
                variablenfa = borne;
                InputStream isImage = null;
                try {
                    isImage = (InputStream) new FileInputStream(img);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                nfa.setImage(new Image(isImage));
                gridImageNFA.add(nfa, 0, 0);
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }

        if(actionevent.getSource()==toanswerRgxnfa){
            File file = new File("src/sample/ressources/regexnfa"+variablenfa+".txt");
            try{
                Scanner scanner = new Scanner(file);
                String regex = scanner.nextLine();
                gridImageNFA.getChildren().remove(nfa);
                gridImageNFA.getChildren().remove(reponseregexnfa);
                reponseregexnfa.setText(regex);
                reponseregexnfa.setFont(new Font(50));
                gridImageNFA.add(reponseregexnfa,0,0);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        if(actionevent.getSource() == toexitNfa){
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }

        /**
         * PROBLEME (** REGEX -> DFA **)
         */
        if(actionevent.getSource()==newrgxdfa){
            if(borne<5) {
                gridImagergxdfa.getChildren().remove(regexdfa);
                gridImagergxdfa.getChildren().remove(dfasolution);
                borne++;
                variablergxdfa = borne;
                File file = new File("src/sample/ressources/regexdfa" + borne + ".txt");
                try {
                    Scanner scanner = new Scanner(file);
                    String regex = scanner.nextLine();
                    regexdfa.setText(regex);
                    regexdfa.setFont(new Font(40));
                    gridImagergxdfa.add(regexdfa, 0, 0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }
        if(actionevent.getSource()==toanswerdfa){
            File img = new File("src/sample/ressources/dfa"+variablergxdfa+".png");
            gridImagergxdfa.getChildren().remove(dfasolution);
            gridImagergxdfa.getChildren().remove(regexdfa);
            InputStream isImage = null;
            try{
                isImage = (InputStream) new FileInputStream(img);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
            dfasolution.setImage(new Image(isImage));
            gridImagergxdfa.add(dfasolution,0,0);
        }
        if(actionevent.getSource()==toexitrgxdfa){
            borne=0;
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }


        /**
         * PROBLEME (** REGEX -> NFA **)
         */
        if(actionevent.getSource()==newrgxnfa){
            if(borne<5) {
                gridImageRGXnfa.getChildren().remove(regexnfa);
                gridImageRGXnfa.getChildren().remove(nfasolution);
                borne++;
                variablergxnfa = borne;
                File file = new File("src/sample/ressources/regexnfa" + borne + ".txt");
                try {
                    Scanner scanner = new Scanner(file);
                    String regex = scanner.nextLine();
                    regexnfa.setText(regex);
                    regexnfa.setFont(new Font(40));
                    gridImageRGXnfa.add(regexnfa, 0, 0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }
        if(actionevent.getSource()==toanswernfa){
            File img = new File("src/sample/ressources/nfa"+variablergxnfa+".png");
            gridImageRGXnfa.getChildren().remove(nfasolution);
            gridImageRGXnfa.getChildren().remove(regexnfa);
            InputStream isImage = null;
            try {
                isImage = (InputStream) new FileInputStream(img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            nfasolution.setImage(new Image(isImage));
            gridImageRGXnfa.add(nfasolution,0,0);
        }
        if(actionevent.getSource()==toexitrgxnfa){
            borne=0;
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }


        /**
         * Problème (** FR -> REGEX **)
         */
        if(actionevent.getSource()==newEnonce){
            if(borne<5) {
                gridcentrefr2regex.getChildren().clear();
                borne++;
                File file = new File("src/sample/ressourcefr2/enoncefrregex" + borne + ".txt");
                variablefr2regex = borne;
                try {
                    Scanner scanner = new Scanner(file);
                    String fr = scanner.nextLine();
                    enonceFrancais.setText(fr);
                    enonceFrancais.setFont(new Font(30));
                    enonceFrancais.setWrapText(true);
                    gridcentrefr2regex.add(enonceFrancais, 0, 0);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }
        if(actionevent.getSource()==reponsefr2regex){
            File texte = new File("src/sample/ressourcefr2/regexfr"+variablefr2regex+".txt");
            try {
                Scanner scanner = new Scanner(texte);
                String regex = scanner.nextLine();
                gridcentrefr2regex.getChildren().clear();
                regexsolution.setText(regex);
                regexsolution.setFont(new Font(40));
                gridcentrefr2regex.add(regexsolution,0,0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(actionevent.getSource()==toexitfrregex){
            borne=0;
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }


        /**
         * PROBLEME (** FR -> DFA **)
         */

        if(actionevent.getSource()==newenoncefr2dfa){
            if(borne<5) {
                gridcentrefr2dfa.getChildren().clear();
                borne++;
                File texte = new File("src/sample/ressourcefrdfa/enoncefrdfa" + borne + ".txt");
                variablefr2dfa = borne;
                try {
                    Scanner scanner = new Scanner(texte);
                    String enonce = scanner.nextLine();
                    enoncefrancaisdfa.setText(enonce);
                    enoncefrancaisdfa.setFont(new Font(50));
                    enoncefrancaisdfa.setWrapText(true);
                    gridcentrefr2dfa.add(enoncefrancaisdfa, 0, 0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }

        if(actionevent.getSource()==reponsefr2dfa){
            File img = new File("src/sample/ressourcefrdfa/frdfa"+variablefr2dfa+".png");
            gridcentrefr2dfa.getChildren().clear();
            InputStream isImage = null;
            try {
                isImage = (InputStream) new FileInputStream(img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            frdfasolution.setImage(new Image(isImage));
            gridcentrefr2dfa.add(frdfasolution,0,0);
        }
        if(actionevent.getSource()==toexitfrdfa){
            borne=0;
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }

        /**
         * PROBLEME (** FR -> NFA **)
         */
        if(actionevent.getSource()==newenoncefr2nfa){
            if(borne<5) {
                gridcentrefr2nfa.getChildren().clear();
                borne++;
                File file = new File("src/sample/ressourcefrnfa");
                if (!(file.exists())) file.mkdir();
                File texte = new File("src/sample/ressourcefrnfa/enoncefrnfa" + borne + ".txt");
                variablefr2nfa = borne;
                try {
                    Scanner scanner = new Scanner(texte);
                    String enonce = scanner.nextLine();
                    enoncefrancaisnfa.setText(enonce);
                    enoncefrancaisnfa.setFont(new Font(40));
                    enoncefrancaisnfa.setWrapText(true);
                    gridcentrefr2nfa.add(enoncefrancaisnfa, 0, 0);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                window.setScene(sceneresultat);
                window.setTitle("AUTOMA4U : Resultat");
            }
        }
        if(actionevent.getSource()==reponsefr2nfa){
            File img = new File("src/sample/ressourcefrnfa/frnfa"+variablefr2nfa+".png");
            gridcentrefr2nfa.getChildren().clear();
            InputStream isImage = null;
            try{
                isImage = (InputStream) new FileInputStream(img);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            frnfasolution.setImage(new Image(isImage));
            gridcentrefr2nfa.add(frnfasolution,0,0);
        }
        if(actionevent.getSource()==toexitfrnfa){
            borne=0;
            window.setScene(accueil);
            window.setTitle("AUTOMA4U : Accueil");
        }


    }

    public Integer getChoice(ComboBox<Integer> combobox) {
        return combobox.getValue();
    }

    public void createNfa(String number) throws Exception{
        PrintStream stdout = System.out;
        File file = new File("src/sample/ressources");
        if(!(file.exists())) file.mkdir();
        PrintStream regex = new PrintStream(new FileOutputStream("src/sample/ressources/regexnfa"+number+".txt", false));
        PrintStream nfa = new PrintStream(new FileOutputStream("dotfiles/NFA"+number+".dot",false));
        MoFormula moFormula = new MoFormula(compteur);
        RegExp regexp = new RegExp(moFormula.PutParentheses(moFormula.methodeMain()));
        Automaton a = regexp.toAutomaton(false);
        System.setOut(regex);
        System.out.println(regexp);
        System.setOut(nfa);
        System.out.println(a.toDot());
        ParsingDOT parsingDOT = new ParsingDOT();
        parsingDOT.ParsingNFA(number);
        System.setOut(stdout);
    }

    public void createDfa(String number) throws Exception {
        PrintStream stdout = System.out;
        File file = new File("src/sample/ressources");
        if(!(file.exists())) file.mkdir();
        PrintStream regex = new PrintStream(new FileOutputStream("src/sample/ressources/regexdfa"+number+".txt", false));
        PrintStream dfa = new PrintStream(new FileOutputStream("dotfiles/DFA"+number+".dot", false));
        MoFormula moformula = new MoFormula(compteur);
        RegExp regexp = new RegExp(moformula.methodeMain());
        Automaton a = regexp.toAutomaton(true);
        System.setOut(regex);
        System.out.println(regexp);
        System.setOut(dfa);
        System.out.println(a.toDot());
        ParsingDOT parsingDOT = new ParsingDOT();
        parsingDOT.ParsingDFA(number);
        System.setOut(stdout);
    }
    public void createfr2dfa(String number) throws Exception {
        FRtoAUTO fRtoAUTO = new FRtoAUTO(compteur);
        String[] resultat = fRtoAUTO.generate();
        fRtoAUTO.toString(resultat);
        PrintStream stdout = System.out;
        File file = new File("src/sample/ressourcefrdfa");
        if(!(file.exists())) file.mkdir();
        PrintStream fr = new PrintStream(new FileOutputStream("src/sample/ressourcefrdfa/enoncefrdfa"+number+".txt"));
        PrintStream dfa = new PrintStream(new FileOutputStream("dotfiles/frdfa"+number+".dot",false));
        System.setOut(fr);
        System.out.println(resultat[0]);
        System.setOut(stdout);
        if (resultat.length == 4){
            String exp1 = resultat[1];
            String exp2 = resultat[2];
            RegExp regexp1 = new RegExp(exp1);
            RegExp regexp2 = new RegExp(exp2);
            if (resultat[3].equals("O")){
                Automaton a = regexp1.toAutomaton(true);
                Automaton b = regexp1.toAutomaton(true);
                Automaton c = BasicOperations.union(a,b);
                c.determinize();
                System.setOut(dfa);
                System.out.println(c.toDot());
                ParsingDOT parsing = new ParsingDOT();
                parsing.ParsingFrDFA(number);
                System.setOut(stdout);
            }
            else if (resultat[3].equals("A")){
                Automaton a = regexp1.toAutomaton(true);
                Automaton b = regexp2.toAutomaton(true);
                Automaton c = BasicOperations.concatenate(a,b);
                c.determinize();
                System.setOut(dfa);
                System.out.println(c.toDot());
                ParsingDOT parsing = new ParsingDOT();
                parsing.ParsingFrDFA(number);
                System.setOut(stdout);
            }
        }
        else if (resultat.length == 2){
            String exp = resultat[1];
            RegExp regexp = new RegExp(exp);
            Automaton a = regexp.toAutomaton(true);
            System.setOut(dfa);
            a.determinize();
            System.out.println(a.toDot());
            ParsingDOT parsing = new ParsingDOT();
            parsing.ParsingFrDFA(number);
            System.setOut(stdout);
        }
    }
    public void createfr2nfa(String number) throws Exception {
        FRtoAUTO fRtoAUTO = new FRtoAUTO(compteur);
        String[] resultat = fRtoAUTO.generate();
        fRtoAUTO.toString(resultat);
        PrintStream stdout = System.out;
        File file = new File("src/sample/ressourcefrnfa");
        if(!(file.exists())) file.mkdir();
        PrintStream fr = new PrintStream(new FileOutputStream("src/sample/ressourcefrnfa/enoncefrnfa"+number+".txt"));
        PrintStream nfa = new PrintStream(new FileOutputStream("dotfiles/frnfa"+number+".dot",false));
        System.setOut(fr);
        System.out.println(resultat[0]);
        System.setOut(stdout);
        if (resultat.length == 4){
            String exp1 = resultat[1];
            String exp2 = resultat[2];
            RegExp regexp1 = new RegExp(exp1);
            RegExp regexp2 = new RegExp(exp2);
            if (resultat[3].equals("O")){
                Automaton a = regexp1.toAutomaton(false);
                Automaton b = regexp1.toAutomaton(false);
                Automaton c = BasicOperations.union(a,b);
                System.setOut(nfa);
                System.out.println(c.toDot());
                ParsingDOT parsing = new ParsingDOT();
                parsing.ParsingFrNFA(number);
                System.setOut(stdout);
            }
            else if (resultat[3].equals("A")){
                Automaton a = regexp1.toAutomaton(false);
                Automaton b = regexp2.toAutomaton(false);
                Automaton c = BasicOperations.concatenate(a,b);
                System.setOut(nfa);
                System.out.println(c.toDot());
                ParsingDOT parsing = new ParsingDOT();
                parsing.ParsingFrNFA(number);
                System.setOut(stdout);
            }
        }
        else if (resultat.length == 2){
            String exp = resultat[1];
            RegExp regexp = new RegExp(exp);
            Automaton a = regexp.toAutomaton(true);
            System.setOut(nfa);
            System.out.println(a.toDot());
            ParsingDOT parsing = new ParsingDOT();
            parsing.ParsingFrNFA(number);
            System.setOut(stdout);
        }
    }

    public void createfr2regex(String number) throws Exception {
        File file = new File("src/sample/ressourcefr2");
        if(!(file.exists())) file.mkdir();
        PrintStream regex = new PrintStream(new FileOutputStream("src/sample/ressourcefr2/regexfr"+number+".txt"));
        PrintStream fr = new PrintStream(new FileOutputStream("src/sample/ressourcefr2/enoncefrregex"+number+".txt"));
        PrintStream stdout = System.out;
        FRtoRE frtore = new FRtoRE(compteur);
        String[] resultat = frtore.generate();
        String str = resultat[0];
        String str2 = resultat[1];
        System.setOut(fr);
        System.out.println(str);
        System.setOut(regex);
        System.out.println(str2);
        System.setOut(stdout);
    }

    public void difficultyDFArgx(){
        for (int i = 0; i < 21; i++) {
            try {
                createDfa(String.valueOf(i));
                window.setScene(dfa2regex);
                window.setTitle("AUTOMA4U : Automate déterministe vers expression régulière");
                borne=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void difficultyNFArgx(){
        for (int i = 0; i < 21; i++) {
            try {
                createNfa(String.valueOf(i));
                window.setScene(nfa2regex);
                window.setTitle("AUTOMA4U : Automate non déterministe vers expression régulière");
                borne=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void difficultyFRdfa(){
        for (int i = 0; i < 21; i++) {
            try {
                createfr2dfa(String.valueOf(i));
                window.setScene(fr2dfa);
                window.setTitle("AUTOMA4U : Français vers automate déterministe");
                borne=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void difficultyFRnfa(){
        for (int i = 0; i < 21; i++) {
            try {
                createfr2nfa(String.valueOf(i));
                window.setScene(fr2nfa);
                window.setTitle("AUTOMA4U : Français vers automate non déterministe");
                borne=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void difficultyFRrgx(){
        for (int i = 0; i < 21; i++) {
            try {
                createfr2regex(String.valueOf(i));
                window.setScene(fr2regex);
                window.setTitle("AUTOMA4U : Français vers expression régulière");
                borne=0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
