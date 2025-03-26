package Figures;

import java.util.Objects;

public class Figure {
    protected final String color;
    protected int[] coordinates = new int[2];
    protected final int id;
    protected static int count;
    protected String lastMove;

    protected final String[] letterCoordinate = {"a", "b", "c", "d", "e", "f", "g", "h"};
    public String figureLetter = "";

    {
        this.id = ++count;
    }

    public Figure() {
        this.color = "Undefined";
        this.coordinates[0] = -1;
        this.coordinates[1] = -1;
    }

    public Figure(String color) {
        if (Objects.equals(color, "White") || Objects.equals(color, "Black")) {
            this.color = color;
        }
        else {
            this.color = "Undefined";
        }
        this.coordinates[0] = -1;
        this.coordinates[1] = -1;
    }

    public Figure(String color, int x, int y) {
        if (Objects.equals(color, "White") || Objects.equals(color, "Black")) {
            this.color = color;
        }
        else {
            this.color = "Undefined";
        }

        if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
            this.coordinates[0] = x;
            this.coordinates[1] = y;
        }
        else {
            this.coordinates[0] = -1;
            this.coordinates[1] = -1;
        }
    }

    public boolean checkMove(int newX, int newY) {
        return (newX >= 0 && newX <= 7) && (newY >= 0 && newY <= 7);
    }

    protected void changeLastMove(int newX, int newY) {
        this.lastMove = figureLetter + letterCoordinate[this.getCoordinates()[0]] + Integer.toString(this.getCoordinates()[1] + 1) +
                letterCoordinate[newX] + Integer.toString(newY + 1);
    };

    public boolean move(int newX, int newY) {
        if (checkMove(newX, newY) && Objects.equals(Board.whosMoves, this.color)) {
            Board.changeFigurePosition(this.getCoordinates()[0], this.getCoordinates()[1], newX, newY);
            changeLastMove(newX, newY);
            this.coordinates[0] = newX;
            this.coordinates[1] = newY;
            Board.whosMoves = Objects.equals(Board.whosMoves, "White") ? "Black" : "White";
            return true;
        }
        return false;
    }

    public void remove() {
        this.coordinates[0] = -1;
        this.coordinates[1] = -1;
    }

    public int[] getCoordinates() {
        return this.coordinates;
    }

    public String getLastMove() {
        return this.lastMove;
    }

    public int getId() {
        return this.id;
    }

    public String getFigureLetter() {
        return this.figureLetter;
    }
}
