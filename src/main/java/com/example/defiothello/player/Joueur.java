package com.example.defiothello.player;

import javafx.scene.paint.Color;

/**
 * Classe représentant un joueur.
 */
public class Joueur {
  /**
   * Nom du joueur.
   */
  private final String nom;
  /**
   * Couleur du joueur.
   */
  private final Color couleur;

  /**
   * Constructeur.
   *
   * @param nom Nom du joueur
   * @param couleur Couleur du joueur
   */
  public Joueur(String nom, Color couleur) {
    this.nom = nom;
    this.couleur = couleur;
  }

  /**
   * Méthode permettant de récupérer le nom du joueur.
   *
   * @return Nom du joueur
   */
  public String getNom() {
    return nom;
  }

  /**
   * Méthode permettant de récupérer la couleur du joueur.
   *
   * @return Couleur du joueur
   */
  public Color getCouleur() {
    return couleur;
  }
}
