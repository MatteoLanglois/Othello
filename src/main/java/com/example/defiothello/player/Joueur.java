package com.example.defiothello.player;

import javafx.scene.paint.Color;

public class Joueur {
  private final String nom;
  private final Color couleur;

  public Joueur(String nom, Color couleur) {
    this.nom = nom;
    this.couleur = couleur;
  }

  public String getNom() {
    return nom;
  }

  public Color getCouleur() {
    return couleur;
  }
}
