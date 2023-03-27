package com.example.defiothello.board;

import javafx.scene.layout.Pane;

public class Cell extends Pane {
  private int row;
  private int col;

  private Pion pion;

  public Cell(int row, int col) {
    this.row = row;
    this.col = col;
    setPrefSize(Othello.CELL_SIZE, Othello.CELL_SIZE);
    this.styleProperty().set("-fx-background-color: green");
    pion = null;
  }

  public boolean caseVide() {
    return pion == null;
  }

  public void poserPion(Pion pion) {
    this.pion = pion;
    this.getChildren().add(pion);

    // Centrer le pion dans la cellule
    pion.setCenterX(getWidth() / 2);
    pion.setCenterY(getHeight() / 2);
}

  public void centrerPion() {
    if (pion != null) {
      pion.setCenterX(Othello.CELL_SIZE / 2);
      pion.setCenterY(Othello.CELL_SIZE / 2);
    }
  }

  public Pion getPion() {
    return pion;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public boolean hasPion() {
    return pion != null;
  }

  public void setPossibleMove(boolean possibleMove) {
    if (possibleMove) {
      this.styleProperty().set("-fx-opacity: 0.5");
    } else {
      this.styleProperty().set("-fx-opacity: 1");
    }
  }

  public void retournerPion() {
    if (pion != null) {
      pion.retourner();
    }
  }
}
