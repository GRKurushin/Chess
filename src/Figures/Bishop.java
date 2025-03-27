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

//    @Override
//    public boolean checkMove(int newX, int newY) {
//        if (super.checkMove(newX, newY)) {
//            System.out.println(this.getCoordinates()[0] + " " + this.getCoordinates()[1]);
//            int[] coordinates = this.getCoordinates();
//            System.out.println(coordinates[0] + " " + coordinates[1]);
//            System.out.println(newX + " " + newY);
//            System.out.println((coordinates[0] - newX) + " " + (coordinates[1] - newY));
//            System.out.println((coordinates[0] - newX) + " " + (coordinates[1] + newY));
//            System.out.println((newX - coordinates[0]) + " " + (coordinates[1] - newY));
//            System.out.println((newX - coordinates[0]) + " " + (coordinates[1] + newY));
//            if (coordinates[0] - newX == coordinates[1] - newY) {
//                for (int i = 1; i < coordinates[0] - newX; i++) {
//                    if (Board.board[coordinates[0] - i][coordinates[1] - i] != null) { return false; }
//                }
//                if (Board.board[newX][newY] != null &&
//                    !Objects.equals(Board.board[newX][newY].color, this.color)) {
//                    return true;
//                }
//                else if (Board.board[newX][newY] == null) { return true; }
//                return false;
//            }
//            else if (coordinates[0] - newX == coordinates[1] + newY) {
//                for (int i = 1; i < coordinates[0] - newX; i++) {
//                    if (Board.board[coordinates[0] - i][coordinates[1] + i] != null) { return false; }
//                }
//                if (Board.board[newX][newY] != null &&
//                        !Objects.equals(Board.board[newX][newY].color, this.color)) {
//                    return true;
//                }
//                else if (Board.board[newX][newY] == null) { return true; }
//                return false;
//            }
//            else if (newX - coordinates[0] == coordinates[1] - newY) {
//                for (int i = 1; i < newX - coordinates[0]; i++) {
//                    if (Board.board[newX + i][coordinates[1] - i] != null) { return false; }
//                }
//                if (Board.board[newX][newY] != null &&
//                        !Objects.equals(Board.board[newX][newY].color, this.color)) {
//                    return true;
//                }
//                else if (Board.board[newX][newY] == null) { return true; }
//                return false;
//            }
//            else if (newX - coordinates[0] == coordinates[1] + newY) {
//                for (int i = 1; i < newX - coordinates[0]; i++) {
//                    if (Board.board[newX + i][coordinates[1] + i] != null) { return false; }
//                }
//                if (Board.board[newX][newY] != null &&
//                        !Objects.equals(Board.board[newX][newY].color, this.color)) {
//                    return true;
//                }
//                else if (Board.board[newX][newY] == null) { return true; }
//                return false;
//            }
//        }
//        return false;
//    }

    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY)) {
            int[] coordinates = this.getCoordinates();
            if (Math.abs(newX - coordinates[0]) != Math.abs(newY - coordinates[1])) {
                return false;
            }
            int dx = (newX > coordinates[0]) ? 1 : -1;
            int dy = (newY > coordinates[1]) ? 1 : -1;
            for (int i = 1; i < Math.abs(newX - coordinates[0]); i++) {
                if (Board.board[coordinates[0] + i * dx][coordinates[1] + i * dy] != null) {
                    return false;
                }
            }
            if (Board.board[newX][newY] != null) {
                return !Objects.equals(Board.board[newX][newY].color, this.color);
            }
            return true;
        }
        return false;
    }
}
