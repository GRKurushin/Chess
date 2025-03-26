package Figures;

import java.util.Arrays;
import java.util.Objects;

public class Board {
    public static Figure[][] board = new Figure[8][8];
    public static Figure[] topSide;
    public static Figure[] bottomSide;
    public static String whosMoves = "White";

    public static void main(String[] args) {
        initializeBoard("White");
        printBoard();
        move(board[6][4], 5, 4);
        printBoard();
        move(board[1][3], 2, 3);
        printBoard();
        move(board[7][5], 2, 0);
        printBoard();
        move(board[1][7], 2, 7);
        printBoard();
        move(board[2][0], 4, 2);
        printBoard();
        move(board[2][7], 3, 7);
        printBoard();
        move(board[7][3], 3, 7);
        printBoard();

    }

    public static void initializeBoard(String playersColor) {
        if (Objects.equals(playersColor, "White")) {
            Figure bR1 = new Rook("Black", 0, 0);
            Figure bN1 = new Knight("Black", 0, 1);
            Figure bB1 = new Bishop("Black", 0, 2);
            Figure bQ1 = new Queen("Black", 0, 3);
            Figure bK1 = new King("Black", 0, 4);
            Figure bB2 = new Bishop("Black", 0, 5);
            Figure bN2 = new Knight("Black", 0, 6);
            Figure bR2 = new Rook("Black", 0, 7);
            Figure b1 = new Pawn("Black", 1, 0);
            Figure b2 = new Pawn("Black", 1, 1);
            Figure b3 = new Pawn("Black", 1, 2);
            Figure b4 = new Pawn("Black", 1, 3);
            Figure b5 = new Pawn("Black", 1, 4);
            Figure b6 = new Pawn("Black", 1, 5);
            Figure b7 = new Pawn("Black", 1, 6);
            Figure b8 = new Pawn("Black", 1, 7);
            topSide = new Figure[]{bR1, bN1, bB1, bQ1, bK1, bB2, bN2, bR2, b1, b2, b3, b4, b5, b6, b7, b8};

            Figure wR1 = new Rook("White", 7, 0);
            Figure wN1 = new Knight("White", 7, 1);
            Figure wB1 = new Bishop("White", 7, 2);
            Figure wQ1 = new Queen("White", 7, 3);
            Figure wK1 = new King("White", 7, 4);
            Figure wB2 = new Bishop("White", 7, 5);
            Figure wN2 = new Knight("White", 7, 6);
            Figure wR2 = new Rook("White", 7, 7);
            Figure w1 = new Pawn("White", 6, 0);
            Figure w2 = new Pawn("White", 6, 1);
            Figure w3 = new Pawn("White", 6, 2);
            Figure w4 = new Pawn("White", 6, 3);
            Figure w5 = new Pawn("White", 6, 4);
            Figure w6 = new Pawn("White", 6, 5);
            Figure w7 = new Pawn("White", 6, 6);
            Figure w8 = new Pawn("White", 6, 7);
            bottomSide = new Figure[]{wR1, wN1, wB1, wQ1, wK1, wB2, wN2, wR2, w1, w2, w3, w4, w5, w6, w7, w8};
        }
        else if (Objects.equals(playersColor, "Black")) {
            Figure bR1 = new Rook("Black", 7, 0);
            Figure bN1 = new Knight("Black", 7, 1);
            Figure bB1 = new Bishop("Black", 7, 2);
            Figure bQ1 = new Queen("Black", 7, 3);
            Figure bK1 = new King("Black", 7, 4);
            Figure bB2 = new Bishop("Black", 7, 5);
            Figure bN2 = new Knight("Black", 7, 6);
            Figure bR2 = new Rook("Black", 7, 7);
            Figure b1 = new Pawn("Black", 6, 0);
            Figure b2 = new Pawn("Black", 6, 1);
            Figure b3 = new Pawn("Black", 6, 2);
            Figure b4 = new Pawn("Black", 6, 3);
            Figure b5 = new Pawn("Black", 6, 4);
            Figure b6 = new Pawn("Black", 6, 5);
            Figure b7 = new Pawn("Black", 6, 6);
            Figure b8 = new Pawn("Black", 6, 7);
            bottomSide = new Figure[]{bR1, bN1, bB1, bQ1, bK1, bB2, bN2, bR2, b1, b2, b3, b4, b5, b6, b7, b8};

            Figure wR1 = new Rook("White", 0, 0);
            Figure wN1 = new Knight("White", 0, 1);
            Figure wB1 = new Bishop("White", 0, 2);
            Figure wQ1 = new Queen("White", 0, 3);
            Figure wK1 = new King("White", 0, 4);
            Figure wB2 = new Bishop("White", 0, 5);
            Figure wN2 = new Knight("White", 0, 6);
            Figure wR2 = new Rook("White", 0, 7);
            Figure w1 = new Pawn("White", 1, 0);
            Figure w2 = new Pawn("White", 1, 1);
            Figure w3 = new Pawn("White", 1, 2);
            Figure w4 = new Pawn("White", 1, 3);
            Figure w5 = new Pawn("White", 1, 4);
            Figure w6 = new Pawn("White", 1, 5);
            Figure w7 = new Pawn("White", 1, 6);
            Figure w8 = new Pawn("White", 1, 7);
            topSide = new Figure[]{wR1, wN1, wB1, wQ1, wK1, wB2, wN2, wR2, w1, w2, w3, w4, w5, w6, w7, w8};
        }
        for (int i = 0; i < 16; ++i) {
            board[topSide[i].getCoordinates()[0]][topSide[i].getCoordinates()[1]] = topSide[i];
            board[bottomSide[i].getCoordinates()[0]][bottomSide[i].getCoordinates()[1]] = bottomSide[i];
        }
    }

    public static void changeFigurePosition(int x, int y, int newX, int newY) {
        board[newX][newY] = board[x][y];
        board[x][y] = null;
    }

    public static boolean move(Figure obj, int newX, int newY) {
        if (obj != null) {return obj.move(newX, newY);}
        return false;
    }

    public static void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    System.out.printf("%s ", board[i][j].getFigureLetter());
                }
                else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
