package com.example.defiothello;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {

  private static final Pane launcher = new Pane();
  private static final Label title = new Label("Othello");
  private static final Label description = new Label("Comment voulez vous jouer ?");
  private static final Button button1 = new Button("Joueur contre joueur");
  private static final Button button2 = new Button("Joueur contre IA");
  private static final Button button3 = new Button("IA contre IA");

  private static final GridPane root = new GridPane();
  private static final VBox vbox = new VBox();
  private static final Label joueur = new Label("Joueur 1");

  private static final Label score = new Label("Partie non commencée");

  private static final Button reset = new Button("Reset");

  private static final Scene sceneLauncher = new Scene(launcher, 600, 300);
  private static final Scene sceneGame = new Scene(vbox);
  private static final Button boutonRegle = new Button("Règles");
  private static Stage PrimaryStage;
  Alert regle = new Alert(Alert.AlertType.INFORMATION);


  @Override
  public void start(Stage stage) {
    PrimaryStage = stage;
    PrimaryStage.setTitle("Othello!");
    initWindow();

    showLauncher();
  }

  private void initWindow() {
    title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
    title.setAlignment(javafx.geometry.Pos.CENTER);
    title.setLayoutY(40);
    title.setLayoutX(246);
    launcher.getChildren().add(title);

    description.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
    launcher.getChildren().add(description);
    description.setLayoutY(100);
    description.setLayoutX(182);
    description.setAlignment(javafx.geometry.Pos.CENTER);

    boutonRegle.setPrefSize(189, 39);
    boutonRegle.setLayoutX(397);
    boutonRegle.setLayoutY(50);

    button1.setPrefSize(189, 39);
    button1.setLayoutX(14);
    button1.setLayoutY(241);
    button2.setPrefSize(189, 39);
    button2.setLayoutX(205);
    button2.setLayoutY(241);
    button3.setPrefSize(189, 39);
    button3.setLayoutX(397);
    button3.setLayoutY(241);

    button1.setStyle("-fx-font-size: 18px");
    button2.setStyle("-fx-font-size: 18px");
    button3.setStyle("-fx-font-size: 18px");

    button1.setOnAction(event -> {
      initBoard(2);
    });

    button2.setOnAction(event -> {
      initBoard(1);
    });

    button3.setOnAction(event -> {
      initBoard(0);
    });

    boutonRegle.setOnAction(event -> {
      regle.showAndWait();
    });

    regle.setTitle("Règles");
    regle.setHeaderText("Règles du jeu");
    regle.setContentText("Le but du jeu est de capturer le plus de pions de l'adversaire que "
            + "possible. Pour cela, il faut placer un pion de sa couleur sur une case vide tout en "
            + "capturant des pions de l'adversaire. Un pion est capturé lorsqu'il est entouré "
            + "horizontalement, verticalement ou en diagonale par deux pions de la même couleur. "
            + "Le joueur qui commence est celui qui a les pions noirs. Les pions noirs commencent "
            + "la partie en haut à gauche et les pions blancs en bas à droite. Le joueur qui "
            + "capture le plus de pions de l'adversaire gagne la partie. Si aucun des deux "
            + "joueurs ne peut jouer, la partie est terminée. Le joueur qui a le plus de pions "
            + "gagne la partie. Si les deux joueurs ont le même nombre de pions, la partie est "
            + "nulle.");

    launcher.getChildren().addAll(button1, button2, button3, boutonRegle);
  }

  private void initBoard(int nbJoueurs) {
    vbox.getChildren().clear();
    Partie partie = new Partie(nbJoueurs, root, vbox, joueur, score, reset);
    vbox.getChildren().add(partie.getGridPane());
    vbox.getChildren().add(joueur);
    vbox.getChildren().add(score);
    vbox.getChildren().add(reset);
    PrimaryStage.setScene(sceneGame);
    PrimaryStage.show();
  }

  public static void showLauncher() {
    PrimaryStage.setScene(sceneLauncher);
    PrimaryStage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}