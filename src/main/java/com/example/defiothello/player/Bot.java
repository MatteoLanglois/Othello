package com.example.defiothello.player;

import com.example.defiothello.board.Cell;
import com.example.defiothello.board.Othello;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Classe représentant un joueur virtuel.
 */
public class Bot extends Joueur {
  /**
   * Constructeur.
   *
   * @param nom Nom du joueur
   * @param couleur Couleur du joueur
   */
  public Bot(String nom, Color couleur) {
    super(nom, couleur);
  }

  /**
   * Méthode permettant de choisir un coup aléatoire parmi les coups possibles.
   *
   * @param othello Plateau de jeu
   * @return Cellule qui va être jouée
   */
  public Cell jouer(Othello othello) {
    ArrayList<Cell> coupsPossible = othello.getPossibleMoves(this.getCouleur());

    Cell cell = null;
    if (coupsPossible.size() > 0) {
      int random = (int) (Math.random() * coupsPossible.size());
      cell = coupsPossible.get(random);
    }

    return cell;
  }
}
