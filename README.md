AABID Mohsine, BERAHIA Fayssal, FARYSSY Mohammed, REGA Eve

AUTOMA4U
Le programme se lance depuis la classe Main.java dans le dossier sample. Le chemin exact est "src/sample/Main.java"

Le projet nécessite d'avoir GraphViz sur son ordinateur pour générer les images des automates, télécharger GraphViz sur :
https://graphviz.org/download/

Modifier l'emplacement de GraphViz dans les différentes méthodes de la classe à la position "src/sample/ParsingDOT.java"
Modifier le système d'exploitation dans la classe ParsingDot.java aussi.

L'interface graphique étant implémentée en JavaFX, il est nécessaire d'avoir une librairie JavaFX sur son ordinateur.
Il est possible de la télécharger en suivant ce lien : https://gluonhq.com/products/javafx/
Il faut ensuite :

-Ajouter JavaFX dans la liste des librairies du projet (Sur IntelliJ : File -> Project Structure -> Libraries, appuyer sur le + et mettre le dossier "lib" se trouvant dans le dossier "JavaFX" téléchargé sur le lien précédent.

-Ajouter JavaFX aux VM Options de l'IDE (Sur IntelliJ : Onglet Run -> Edit configurations -> Ecrire dans VM options :

--module-path
C:/openjfx-11.0.2_windows-x64_bin-sdk/javafx-sdk-11.0.2/lib (** Modifier le chemin **)
--add-modules
javafx.controls,javafx.fxml
--add-exports
javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED

Le programme peut maintenant être lancé. Les boutons + et - permettent d'augmenter / réduire la difficulté. Le bouton de choix (1,2,3,4) correspond au type de problème, légendé sur la page d'accueil de l'application. Le reste est normalement indiqué dans l'application.

Quelques exemples d'automates / d'expressions régulières / d'énoncés sont présents dans les dossiers respectivement "ressources", "ressourcesfrdfa"
