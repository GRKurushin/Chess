package Figures;

public class Queen extends Figure{
    private Rook rook;
    private Bishop bishop;

    {
        super.figureLetter = "Q";
    }

    public Queen(String color) {
        super(color);
    }

    public Queen(String color, int x, int y) {
        super(color, x, y);
        this.rook = new Rook(color, x, y);
        this.bishop = new Bishop(color, x, y);
    }

    @Override
    public boolean checkMove(int newX, int newY) {
        if (super.checkMove(newX, newY) && (rook.checkMove(newX, newY) || bishop.checkMove(newX, newY))) {
            return true;
        }
        return false;
    }
}
