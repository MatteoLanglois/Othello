package com.example.defiothello.board;

import java.util.ArrayList;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Othello {

  private static Othello instance;
  public static final int BOARD_SIZE = 8;
  public static final int CELL_SIZE = 50;
  private final Cell[][] board = new Cell[BOARD_SIZE][BOARD_SIZE];

  public Othello() {
    instance = this;
  }

  public static Othello getInstance() {
    if (instance == null) {
      instance = new Othello();
    }
    return instance;
  }

  public GridPane reset(GridPane gridPane) {
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {
        Cell cell = new Cell(row, col);
        board[row][col] = cell;
        gridPane.add(cell, cell.getRow(), cell.getCol());
        if (row == 3 && col == 3) {
          cell.poserPion(new Pion(Color.WHITE));
        } else if (row == 4 && col == 4) {
          cell.poserPion(new Pion(Color.WHITE));
        } else if (row == 3 && col == 4) {
          cell.poserPion(new Pion(Color.BLACK));
        } else if (row == 4 && col == 3) {
          cell.poserPion(new Pion(Color.BLACK));
        }
        if (cell.hasPion()) {
          cell.centrerPion();
        }
      }
    }

    return gridPane;
  }

  public boolean grillePleine() {
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {
        if (board[row][col].caseVide()) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean canCatch(int row, int col, Color couleur) {
    return !getCatchable(row, col, couleur).isEmpty();
  }

  private ArrayList<Cell> getCatchable(int row, int col, Color couleur) {
    ArrayList<Cell> cellulesCapturables = new ArrayList<>();
    cellulesCapturables.addAll(checkRow(board, row, col, couleur));
    cellulesCapturables.addAll(checkCol(board, row, col, couleur));
    cellulesCapturables.addAll(checkDiag(board, row, col, couleur));

    return cellulesCapturables;
  }

  public ArrayList<Cell> getPossibleMoves(Color couleur) {
    ArrayList<Cell> possibleMoves = new ArrayList<>();
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {
        if (canCatch(row, col, couleur) && board[row][col].caseVide()) {
          possibleMoves.add(board[row][col]);
        }
      }
    }
    return possibleMoves;
  }

  private void catchCell(int row, int col, Color couleur) {
    ArrayList<Cell> cellulesCapturables = getCatchable(row, col, couleur);
    for (Cell cell : cellulesCapturables) {
      cell.retournerPion();
    }
  }

  public int getScore(Color couleur) {
    int score = 0;
    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {
        if (board[row][col].getPion() != null && board[row][col].getPion().getColor() == couleur) {
          score++;
        }
      }
    }
    return score;
  }

  public boolean jouer(Cell cell, Color couleur) {
    boolean pionPose = false;
    if (cell.caseVide() && !grillePleine() &&
        canCatch(cell.getRow(), cell.getCol(), Color.BLACK) ||
        canCatch(cell.getRow(), cell.getCol(), Color.WHITE)) {
      if (canCatch(cell.getRow(), cell.getCol(), couleur)) {
        cell.poserPion(new Pion(couleur));
        catchCell(cell.getRow(), cell.getCol(), couleur);
        pionPose = true;
      }
    }
    return pionPose;
  }

  public Cell getCell(int row, int col) {
    return board[row][col];
  }

  public Cell[][] getBoard() {
    return board;
  }

  public static ArrayList<Cell> checkRow(Cell[][] board, int row, int col, Color couleur) {
    ArrayList<Cell> cellulesCapturables = new ArrayList<>();
    ArrayList<Cell> pionsLigne = new ArrayList<>();
    for (int c = col - 1; c >= 0; c--) {
      Cell cellule = board[row][c];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsLigne);
        break;
      } else {
        pionsLigne.add(cellule);
      }
    }
    pionsLigne.clear();
    for (int c = col + 1; c < Othello.BOARD_SIZE; c++) {
      Cell cellule = board[row][c];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsLigne);
        break;
      } else {
        pionsLigne.add(cellule);
      }
    }
    return cellulesCapturables;
  }

  public static ArrayList<Cell> checkCol(Cell[][] board, int row, int col, Color couleur) {
    ArrayList<Cell> cellulesCapturables = new ArrayList<>();
    ArrayList<Cell> pionsColonne = new ArrayList<>();
    for (int r = row - 1; r >= 0; r--) {
      Cell cellule = board[r][col];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsColonne);
        break;
      } else {
        pionsColonne.add(cellule);
      }
    }
    pionsColonne.clear();
    for (int r = row + 1; r < Othello.BOARD_SIZE; r++) {
      Cell cellule = board[r][col];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsColonne);
        break;
      } else {
        pionsColonne.add(cellule);
      }
    }
    return cellulesCapturables;
  }

  public static ArrayList<Cell> checkDiag(Cell[][] board, int row, int col, Color couleur) {
    ArrayList<Cell> cellulesCapturables = new ArrayList<>();
    ArrayList<Cell> pionsDiag = new ArrayList<>();

    for (int i = 1 ; row - i >= 0 && col -i >= 0 ; i++) {
      Cell cellule = board[row - i][col - i];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsDiag);
        break;
      } else {
        pionsDiag.add(cellule);
      }
    }
    pionsDiag.clear();
    for (int i = 1 ; row + i < Othello.BOARD_SIZE && col + i < Othello.BOARD_SIZE ; i++) {
      Cell cellule = board[row + i][col + i];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsDiag);
        break;
      } else {
        pionsDiag.add(cellule);
      }
    }
    pionsDiag.clear();
    for (int i = 1 ; row - i >= 0 && col + i < Othello.BOARD_SIZE ; i++) {
      Cell cellule = board[row - i][col + i];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsDiag);
        break;
      } else {
        pionsDiag.add(cellule);
      }
    }
    pionsDiag.clear();
    for (int i = 1 ; row + i < Othello.BOARD_SIZE && col - i >= 0 ; i++) {
      Cell cellule = board[row + i][col - i];
      if (cellule.getPion() == null) {
        break;
      } else if (cellule.getPion().getColor() == couleur) {
        cellulesCapturables.addAll(pionsDiag);
        break;
      } else {
        pionsDiag.add(cellule);
      }
    }
    return cellulesCapturables;
  }
}
