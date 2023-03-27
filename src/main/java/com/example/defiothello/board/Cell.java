package com.example.defiothello.board;

import javafx.scene.layout.Pane;

/**
 * Classe représentant une cellule du plateau de jeu.
 */
public class Cell extends Pane {
  /**
   * Coordonnées de la cellule.
   */
  private int row;

  /**
   * Coordonnées de la cellule.
   */
  private int col;

  /**
   * Pion présent sur la cellule.
   */
  private Pion pion;

  /**
   * Constructeur.
   *
   * @param row Position de la cellule.
   * @param col Position de la cellule.
   */
  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
    setPrefSize(Othello.CELL_SIZE, Othello.CELL_SIZE);
    this.styleProperty().set("-fx-background-color: green");
    pion = null;
  }

  /**
   * Vérifie si la cellule est vide.
   *
   * @return true si la cellule est vide, false sinon
   */
  public boolean caseVide() {
    return pion == null;
  }

  /**
   * Pose un pion sur la cellule.
   *
   * @param pion Pion à poser
   */
  public void poserPion(Pion pion) {
    this.pion = pion;
    this.getChildren().add(pion);

    // Centrer le pion dans la cellule
    pion.setCenterX(getWidth() / 2);
    pion.setCenterY(getHeight() / 2);
  }

  /**
   * Méthode permettant de centrer le pion dans la cellule.
   */
  public void centrerPion() {
    if (pion != null) {
      pion.setCenterX(Othello.CELL_SIZE / 2);
      pion.setCenterY(Othello.CELL_SIZE / 2);
    }
  }

  /**
   * Getter du pion.
   *
   * @return Pion présent sur la cellule
   */
  public Pion getPion() {
    return pion;
  }

  /**
   * Getter de la position de la cellule.
   *
   * @return Position de la cellule
   */
  public int getRow() {
    return row;
  }

  /**
   * Getter de la position de la cellule.
   *
   * @return Position de la cellule
   */
  public int getCol() {
    return col;
  }

  /**
   * Vérifie si la cellule contient un pion.
   *
   * @return true si la cellule contient un pion, false sinon
   */
  public boolean hasPion() {
    return pion != null;
  }

  // A implémenter
  /**
   * Méthode affichant si on peut poser un pion sur la cellule.
   *
   * @param possibleMove true si le coup est possible, false sinon
   */
  public void setPossibleMove(boolean possibleMove) {
    if (possibleMove) {
      this.styleProperty().set("-fx-opacity: 0.5");
    } else {
      this.styleProperty().set("-fx-opacity: 1");
    }
  }

  /**
   * Retourne le pion présent sur la cellule.
   */
  public void retournerPion() {
    if (pion != null) {
      pion.retourner();
    }
  }
}
