package Figures;

import java.util.Objects;

public class Bishop extends Figure {

    {
        super.figureLetter = "B";
    }

    public Bishop(String color) {
        super(color);
    }

    public Bishop(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY)) {
            int[] coordinates = this.getCoordinates();
            if (coordinates[0] - newX == coordinates[1] - newY) {
                for (int i = 1; i < coordinates[0] - newX; i++) {
                    if (Board.board[coordinates[0] - i][coordinates[1] - i] != null) { return false; }
                }
                if (Board.board[newX][newY] != null &&
                    !Objects.equals(Board.board[newX][newY].color, this.color)) {
                    return true;
                }
                else if (Board.board[newX][newY] == null) { return true; }
                return false;
            }
            else if (coordinates[0] - newX == coordinates[1] + newY) {
                for (int i = 1; i < coordinates[0] - newX; i++) {
                    if (Board.board[coordinates[0] - i][coordinates[1] + i] != null) { return false; }
                }
                if (Board.board[newX][newY] != null &&
                        !Objects.equals(Board.board[newX][newY].color, this.color)) {
                    return true;
                }
                else if (Board.board[newX][newY] == null) { return true; }
                return false;
            }
            else if (newX - coordinates[0] == coordinates[1] - newY) {
                for (int i = 1; i < newX - coordinates[0]; i++) {
                    if (Board.board[newX + i][coordinates[1] - i] != null) { return false; }
                }
                if (Board.board[newX][newY] != null &&
                        !Objects.equals(Board.board[newX][newY].color, this.color)) {
                    return true;
                }
                else if (Board.board[newX][newY] == null) { return true; }
                return false;
            }
            else if (newX - coordinates[0] == coordinates[1] + newY) {
                for (int i = 1; i < newX - coordinates[0]; i++) {
                    if (Board.board[newX + i][coordinates[1] + i] != null) { return false; }
                }
                if (Board.board[newX][newY] != null &&
                        !Objects.equals(Board.board[newX][newY].color, this.color)) {
                    return true;
                }
                else if (Board.board[newX][newY] == null) { return true; }
                return false;
            }
        }
        return false;
    }
}
