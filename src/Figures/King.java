package Figures;

import java.util.Objects;

public class King extends Figure{

    {
        firstMove = true;
        super.figureLetter = "K";
    }

    public King(String color) { super(color); }

    public King(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY)) {
            int[] coordinates = this.getCoordinates();
            if ((newX == coordinates[0] - 1 && newY == coordinates[1]) ||
                (newX == coordinates[0] + 1 && newY == coordinates[1])||
                (newX == coordinates[0] && newY == coordinates[1] - 1) ||
                (newX == coordinates[0] && newY == coordinates[1] + 1) ||
                (newX == coordinates[0] - 1 && newY == coordinates[1] - 1) ||
                (newX == coordinates[0] - 1 && newY == coordinates[1] + 1) ||
                (newX == coordinates[0] + 1 && newY == coordinates[1] - 1) ||
                (newX == coordinates[0] + 1 && newY == coordinates[1] + 1)) {
                if (Board.board[newX][newY] == null) { return true; }
                else if (Board.board[newX][newY] != null &&
                        Objects.equals(Board.board[newX][newY].color, this.color)) {
                    return true;
                }
            }
            else if (newX == coordinates[0] && // Рокировка
                    (newY == coordinates[1] + 3) &&
                    (Objects.equals(Board.board[newX][newY].getFigureLetter(), "R")) &&
                    this.firstMove && Board.board[newX][newY].firstMove &&
                    Objects.equals(Board.board[newX][newY].color, this.color) &&
                    Board.board[newX][coordinates[1] + 1] == null &&
                    Board.board[newX][coordinates[1] + 2] == null &&
                    !checkCheck(newX, newY) &&
                    !checkCheck(newX, coordinates[1] + 1) &&
                    !checkCheck(newX, coordinates[1] + 2)){
                moveWhenCastle(newX, newY, true);
                return false;
            }
            else if (newX == coordinates[0] && // Рокировка
                    (newY == coordinates[1] - 4) &&
                    (Objects.equals(Board.board[newX][newY].getFigureLetter(), "R")) &&
                    this.firstMove && Board.board[newX][newY].firstMove &&
                    Objects.equals(Board.board[newX][newY].color, this.color) &&
                    Board.board[newX][coordinates[1] - 1] == null &&
                    Board.board[newX][coordinates[1] - 2] == null &&
                    Board.board[newX][coordinates[1] - 3] == null &&
                    !checkCheck(newX, newY) &&
                    !checkCheck(newX, coordinates[1] - 1) &&
                    !checkCheck(newX, coordinates[1] - 2)) {
                moveWhenCastle(newX, newY, false);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean move(int newX, int newY) {
        if (checkMove(newX, newY) && Objects.equals(Board.whosMoves, this.color) && !checkCheck(newX, newY)) {
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

    public boolean moveWhenCastle(int newX, int newY, boolean shortLong) {
        if (Objects.equals(Board.whosMoves, this.color)) {
            if (shortLong) {
                Board.changeFigurePosition(this.getCoordinates()[0], this.getCoordinates()[1], newX, newY - 1);
                Board.changeFigurePosition(newX, newY, newX, newY - 2);
                Board.board[newX][newY - 1].coordinates[1] = newY - 1;
                Board.board[newX][newY - 2].coordinates[1] = newY - 2;
            }
            else {
                Board.changeFigurePosition(this.getCoordinates()[0], this.getCoordinates()[1], newX, newY + 2);
                Board.changeFigurePosition(newX, newY, newX, newY + 3);
                Board.board[newX][newY + 2].coordinates[1] = newY + 2;
                Board.board[newX][newY + 3].coordinates[1] = newY + 3;
            }
//            changeLastMove(newX, newY);
            firstMove = false;
            Board.whosMoves = Objects.equals(Board.whosMoves, "White") ? "Black" : "White";
            return true;
        }
        return false;
    }
}
