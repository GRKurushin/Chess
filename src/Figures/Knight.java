package Figures;

import java.util.Objects;

public class Knight extends Figure{

    {
        super.figureLetter = "N";
    }

    public Knight(String color) {
        super(color);
    }

    public Knight(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY)) {
            int[] coordinates = this.getCoordinates();
            if ((newX == coordinates[0] - 2) || (newX == coordinates[0] + 2)) {
                if ((newY == coordinates[1] + 1) || (newY == coordinates[1] - 1)) {
                    if (Board.board[newX][newY] == null) { return true; }
                    else if (Board.board[newX][newY] != null &&
                            !Objects.equals(Board.board[newX][newY].color, this.color)) {
                        return true;
                    }
                    return false;
                }
            }
            else if ((newX == coordinates[0] - 1) || (newX == coordinates[0] + 1)) {
                if ((newY == coordinates[1] + 2) || (newY == coordinates[1] - 2)) {
                    if (Board.board[newX][newY] == null) { return true; }
                    else if (Board.board[newX][newY] != null &&
                            !Objects.equals(Board.board[newX][newY].color, this.color)) {
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
