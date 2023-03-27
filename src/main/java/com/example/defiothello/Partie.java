package com.example.defiothello;

import com.example.defiothello.board.Cell;
import com.example.defiothello.board.Othello;
import com.example.defiothello.player.Bot;
import com.example.defiothello.player.Joueur;
import java.util.concurrent.TimeUnit;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Classe représentant une partie.
 */
public class Partie {
  private final Othello othello;
  private final Joueur joueur1;
  private final Joueur joueur2;
  private Joueur joueurCourant;
  private int nbCoups;
  private GridPane gridPane;
  private final Label lblJoueur;
  private Label score;
  private VBox vbox;

  /**
   * Constructeur.
   *
   * @param nbJoueurs Nombre de joueurs humains
   * @param root Grille de jeu
   * @param vbox Conteneur des boutons
   * @param joueur Label du joueur courant
   * @param score Label du score
   * @param reset Bouton de reset
   */
  public Partie(int nbJoueurs, GridPane root, VBox vbox,
                Label joueur, Label score, Button reset) {
    this.othello = new Othello();
    this.gridPane = root;
    this.vbox = vbox;
    this.lblJoueur = joueur;
    this.score = score;
    if (nbJoueurs == 2) {
      joueur1 = new Joueur("Joueur 1", Color.BLACK);
      joueur2 = new Joueur("Joueur 2", Color.WHITE);
    } else if (nbJoueurs == 1) {
      joueur1 = new Joueur("Joueur 2", Color.BLACK);
      joueur2 = new Bot("Bot", Color.WHITE);
    } else {
      joueur1 = new Bot("Bot", Color.BLACK);
      joueur2 = new Bot("Bot", Color.WHITE);
    }
    joueurCourant = joueur1;
    nbCoups = 1;

    this.gridPane.setGridLinesVisible(true);
    this.gridPane.setHgap(2);
    this.gridPane.setVgap(2);
    this.gridPane.setPadding(new Insets(5));
    this.score = score;
    this.vbox = vbox;

    this.gridPane = othello.reset(this.gridPane);
    this.lblJoueur.setText("Le joueur noir commence");
    this.score.setText("Partie non commencée");
    this.vbox.layout();

    reset.setOnAction(e -> {
      this.gridPane = othello.reset(this.gridPane);
      this.lblJoueur.setText("Le joueur noir commence");
      this.score.setText("Partie non commencée");
      this.vbox.layout();
      joueurCourant = joueur1;
      nbCoups = 1;

      for (int i = 0; i < Othello.BOARD_SIZE; i++) {
        for (int j = 0; j < Othello.BOARD_SIZE; j++) {
          Cell cell = othello.getBoard()[i][j];
          cell.setOnMouseClicked(event -> {
            if (joueurCourant instanceof Bot) {
              return;
            }
            jouerTour(cell);
          });
        }
      }
      commencer();
    });

    for (int i = 0; i < Othello.BOARD_SIZE; i++) {
      for (int j = 0; j < Othello.BOARD_SIZE; j++) {
        Cell cell = othello.getBoard()[i][j];
        cell.setOnMouseClicked(e -> {
          if (joueurCourant instanceof Bot) {
            return;
          }
          jouerTour(cell);
        });
      }
    }

    commencer();
  }

  /**
   * Méthode lançant la partie.
   */
  public void commencer() {
    if (joueurCourant instanceof Bot) {
      jouerTour(((Bot) joueurCourant).jouer(othello));
    }
  }

  /**
   * Méthode s'activant à chaque tour.
   *
   * @param celluleChoisie Cellule choisie par le joueur
   */
  public void jouerTour(Cell celluleChoisie) {
    gridPane.layout();
    vbox.layout();
    boolean pionPose = false;
    if (partieTerminee()) {
      terminePartie();
    } else {
      if (joueurCourant instanceof Bot) {
        try {
          TimeUnit.MICROSECONDS.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        pionPose = othello.jouer(celluleChoisie, joueurCourant.getCouleur());
      } else if (celluleChoisie != null && celluleChoisie.getPion() == null) {
        pionPose = othello.jouer(celluleChoisie, joueurCourant.getCouleur());
      }
    }
    if (pionPose) {
      nbCoups++;
      joueurCourant = joueurCourant == joueur1 ? joueur2 : joueur1;
      gridPane.layout();
      this.lblJoueur.setText("Tour n°" + nbCoups + " : Au tour de " + joueurCourant.getNom());
      score.setText("Score : Noir " + othello.getScore(Color.BLACK) + " - Blanc "
          + othello.getScore(Color.WHITE));
      vbox.layout();
      if (partieTerminee()) {
        terminePartie();
        return;
      }
    }
    if (joueurCourant instanceof Bot && pionPose) {
      jouerTour(((Bot) joueurCourant).jouer(othello));
      gridPane.layout();
      vbox.layout();
    }
    gridPane.layout();
    vbox.layout();
  }

  private void terminePartie() {
    this.lblJoueur.setText("Partie terminée");
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Partie terminée !");
    alert.setHeaderText("La partie est terminée !");
    alert.setContentText("Le gagnant est le joueur "
        + (othello.getScore(Color.BLACK) > othello.getScore(Color.WHITE) ? "Noir" : "Blanc"));
    alert.showAndWait();
    othello.reset(gridPane);
    gridPane.layout();
    vbox.layout();
    HelloApplication.showLauncher();
  }

  public boolean partieTerminee() {
    return othello.grillePleine()
        || othello.getPossibleMoves(joueurCourant.getCouleur()).isEmpty();
  }

  public GridPane getGridPane() {
    return gridPane;
  }
}
