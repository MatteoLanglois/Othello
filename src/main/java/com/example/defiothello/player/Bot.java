package com.example.defiothello.player;

import com.example.defiothello.board.Cell;
import com.example.defiothello.board.Othello;
import com.example.defiothello.player.Joueur;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Bot extends Joueur {
  public Bot(String nom, Color couleur) {
    super(nom, couleur);
  }

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
