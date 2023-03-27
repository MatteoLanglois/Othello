package com.example.defiothello.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Classe représentant un pion.
 */
public class Pion extends Circle {
  private Color color;
  public static final int PION_SIZE = 20;

  /**
   * Constructeur.
   *
   * @param color Couleur du pion
   */
  public Pion(Color color) {
    super(PION_SIZE);
    this.color = color;
    setFill(color);
  }

  /**
   * Méthode permettant de récupérer la couleur du pion.
   *
   * @return Couleur du pion
   */
  public Color getColor() {
    return color;
  }

  /**
   * Méthode permettant de retourner le pion.
   */
  public void retourner() {
    if (color == Color.BLACK) {
      color = Color.WHITE;
    } else {
      color = Color.BLACK;
    }
    setFill(color);
  }

  /**
   * Méthode permettant de récupérer la représentation du pion sous forme de chaîne de caractères.
   *
   * @return Représentation du pion sous forme de chaîne de caractères
   */
  public String toString() {
    return color == Color.BLACK ? "B" : "W";
  }
}
