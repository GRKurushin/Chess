package Figures;

import java.util.Objects;

public class Rook extends Figure{

    {
        super.figureLetter = "R";
    }

    public Rook(String color) {
        super(color);
    }

    public Rook(String color, int x, int y) { super(color, x, y); }

    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY)) {
            int[] coordinates = this.getCoordinates();
            if (coordinates[0] == newX && coordinates[1] != newY) {
                if (newY > coordinates[1]) {
                    for (int y = coordinates[1] + 1; y < newY; y++) {
                        if (Board.board[newX][y] != null) { return false; }
                    }
                }
                else {
                    for (int y = coordinates[1] - 1; y > newY; y--) {
                        if (Board.board[newX][y] != null) { return false; }
                    }
                }
                if (Board.board[newX][newY] == null) {
                    return true;
                }
                else {
                    return !Objects.equals(Board.board[newX][newY].color, this.color);
                }
            }
            else if (coordinates[0] != newX && coordinates[1] == newY) {
                if (newX > coordinates[0]) {
                    for (int x = coordinates[0] + 1; x < newX; x++) {
                        if (Board.board[x][newY] != null) { return false; }
                    }
                }
                else {
                    for (int x = coordinates[0] - 1; x > newX; x--) {
                        if (Board.board[x][newY] != null) { return false; }
                    }
                }
                if (Board.board[newX][newY] == null) {
                    return true;
                }
                else {
                    return !Objects.equals(Board.board[newX][newY].color, this.color);
                }
            }
        }
        return false;
    }
}
