package Figures;

import java.util.Arrays;
import java.util.Objects;

public class Pawn extends Figure{
    private boolean firstMove;

    {
        firstMove = true;
        super.figureLetter = "P";
    }

    public Pawn(String color) {
        super(color);
    }

    public Pawn(String color, int x, int y) {
        super(color, x, y);
    }

    // TODO: Check not to move on the same pos
    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY)) {
            int[] coordinates = this.getCoordinates();
            if (Arrays.asList(Board.bottomSide).contains(this)) {
                if ((newY == coordinates[1]) &&
                    (Board.board[newX][newY] == null) &&
                    ((newX == coordinates[0] - 1) ||
                    (newX == coordinates[0] - 2 && firstMove &&
                    Board.board[newX + 1][newY] == null))) {
                    return true;
                }
                else if ((Board.board[newX][newY] != null) &&
                        (!Objects.equals(Board.board[newX][newY].color, this.color)) &&
                        (newX == coordinates[0] - 1) &&
                        ((newY == coordinates[1] + 1) ||
                        (newY == coordinates[1] - 1))) {
                    return true;
                }
                return false;
            } else {
                if ((newY == coordinates[1]) &&
                    (Board.board[newX][newY] == null) &&
                    ((newX == coordinates[0] + 1) ||
                    (newX == coordinates[0] + 2 && firstMove &&
                    Board.board[newX - 1][newY] == null))) {
                    return true;
                }
                else if ((Board.board[newX][newY] != null) &&
                        (!Objects.equals(Board.board[newX][newY].color, this.color)) &&
                        (newX == coordinates[0] + 1) &&
                        ((newY == coordinates[1] + 1) ||
                        (newY == coordinates[1] - 1))) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean move(int newX, int newY) {
        if (checkMove(newX, newY) && Objects.equals(Board.whosMoves, this.color)) {
            Board.changeFigurePosition(this.getCoordinates()[0], this.getCoordinates()[1], newX, newY);
            changeLastMove(newX, newY);
            this.coordinates[0] = newX;
            this.coordinates[1] = newY;
            firstMove = false;
            Board.whosMoves = Objects.equals(Board.whosMoves, "White") ? "Black" : "White";
            return true;
        }
        return false;
    }
}
