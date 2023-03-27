package com.example.defiothello.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Pion extends Circle {
  private Color color;
  public static final int PION_SIZE = 20;

  public Pion(Color color) {
    super(PION_SIZE);
    this.color = color;
    setFill(color);
  }

  public Color getColor() {
    return color;
  }

  public void retourner() {
    if (color == Color.BLACK) {
      color = Color.WHITE;
    } else {
      color = Color.BLACK;
    }
    setFill(color);
  }

  public String toString() {
    return color == Color.BLACK ? "B" : "W";
  }
}
