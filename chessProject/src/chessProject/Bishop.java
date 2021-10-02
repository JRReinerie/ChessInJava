package chessProject;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(int color, Square initSq, String img_file, int pieceValue) {
        super(color, initSq, img_file, pieceValue);
    }
    
    @Override
    public List<Square> getLegalMoves(Board b) {
        Square[][] board = b.getSquareArray();
        int x = this.getPosition().getXNum();
        int y = this.getPosition().getYNum();
        
        return getDiagonalOccupations(board, x, y);
    }
}
