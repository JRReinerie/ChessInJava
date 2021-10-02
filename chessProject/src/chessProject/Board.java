package chessProject;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Board extends JPanel implements MouseListener, MouseMotionListener{

	private GameWindow gameWindow;
	private Square[][] board;
	
	private Piece currentPiece;
	
	public LinkedList<Piece> Bpieces;
    public LinkedList<Piece> Wpieces;
    public List<Square> movable;
    
    private boolean whiteTurn;
   
    private int currX;
    private int currY;
	
	public Board(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		
		board = new Square[8][8];
		
		Bpieces = new LinkedList<Piece>();
        Wpieces = new LinkedList<Piece>();
        
        setLayout(new GridLayout(8, 8, 0, 0));
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
		
		
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
					board[x][y] = new Square(this, 1, y, x);
					this.add(board[x][y]);
				}else {
					board[x][y] = new Square(this, 0, y, x);
					this.add(board[x][y]);
				}
			}
		}
		
		initializePieces();
		
//		this.setPreferredSize(new Dimension(400, 400));
//        this.setMaximumSize(new Dimension(400, 400));
//        this.setMinimumSize(this.getPreferredSize());
//        this.setSize(new Dimension(400, 400));
        
        whiteTurn = true;
	}
	
	private void initializePieces() {
		for(int x = 0; x < 8; x++){
			//black pawns
			board[1][x].put(new Pawn(0, board[1][x], "/bpawn.png", 1));
			
			//white pawns
			board[6][x].put(new Pawn(1, board[6][x], "/wpawn.png", 1));
		}
		
		//black pieces
		board[0][0].put(new Rook(0, board[0][0], "/brook.png", -50));
		board[0][7].put(new Rook(0, board[0][7], "/brook.png", -50));
		board[0][4].put(new King(0, board[0][4], "/bking.png", -900));
		board[0][3].put(new Queen(0, board[0][3], "/bqueen.png", -90));
		board[0][5].put(new Bishop(0, board[0][5], "/bbishop.png", -30));
		board[0][2].put(new Bishop(0, board[0][2], "/bbishop.png", -30));
		board[0][1].put(new Knight(0, board[0][1], "/bknight.png", -30));
		board[0][6].put(new Knight(0, board[0][6], "/bknight.png", -30));
		
		//white pieces
		board[7][4].put(new King(1, board[7][4], "/wking.png", 900));
		board[7][3].put(new Queen(1, board[7][3], "/wqueen.png", 90));
		board[7][5].put(new Bishop(1, board[7][5], "/wbishop.png", 30));
		board[7][2].put(new Bishop(1, board[7][2], "/wbishop.png", 30));
		board[7][1].put(new Knight(1, board[7][1], "/wknight.png", 30));
		board[7][6].put(new Knight(1, board[7][6], "/wknight.png", 30));
		board[7][0].put(new Rook(1, board[7][0], "/wrook.png", 50));
		board[7][7].put(new Rook(1, board[7][7], "/wrook.png", 50));
		
		for(int y = 0; y < 2; y++) {
            for (int x = 0; x < 8; x++) {
                Bpieces.add(board[y][x].getOccupyingPiece());
                Wpieces.add(board[7-y][x].getOccupyingPiece());
            }
        }
	}
	
	public boolean getTurn() {
        return whiteTurn;
    }
	
	public void setCurrentPiece(Piece p) {
	       this.currentPiece = p;
	}
	
	public Piece getCurrentPiece() {
        return this.currentPiece;
	}
	
	public Square[][] getSquareArray() {
        return this.board;
    }
	
	public void paintComponent(Graphics g) {
		for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
            	Square sq = board[y][x];
            	sq.paintComponent(g);
            }
        }
		
		if (currentPiece != null) {
            if (currentPiece.getColor() == 1 && whiteTurn) {
                final Image img = currentPiece.getImage();
                
                g.drawImage(img, currX, currY, null);
            }
        }
	}
	 
	@Override
	public void mousePressed(MouseEvent e) {
		currX = e.getX();
		currY = e.getY();
		
		Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
		
		if (sq.isOccupied()) {
            currentPiece = sq.getOccupyingPiece();
            if (currentPiece.getColor() == 0 && whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
		
	}
	
	public void mouseReleased(MouseEvent e) {
		Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
		
		if(currentPiece != null) {
			if (currentPiece.getColor() == 0 && whiteTurn)
                return;
			
			List<Square> legalMoves = currentPiece.getLegalMoves(this);
			
			if(legalMoves.contains(sq)) {
				sq.setDisplay(true);
		        currentPiece.move(sq);
		        
		        currentPiece = null;
                whiteTurn = !whiteTurn;
			}else {
				currentPiece.getPosition().setDisplay(true);
				currentPiece = null;
			}
			
		}
		
        repaint();
	}
	
	public void mouseDragged(MouseEvent e) {
		currX = e.getX() - 48;
        currY = e.getY() - 48;

        repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
		
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	

	
}
