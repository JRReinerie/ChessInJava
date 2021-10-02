package chessProject;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Square extends JComponent{
	
	private Board board;
	
	private int color;
	
	private int xNum;
	private int yNum;
	
	private Piece occupyingPiece;
    private boolean dispPiece;
	
	public Square(Board board, int color, int xNum, int yNum) {
		this.board = board;
		this.color = color;
		this.xNum = xNum;
		this.yNum = yNum;
		this.dispPiece = true;
		
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	public int getXNum() {
		return xNum;
	}

	public int getYNum() {
		return yNum;
	}
	
	public int getColor() {
		return color;
	}
	
	public Piece getOccupyingPiece() {
        return occupyingPiece;
    }
	
	public boolean isOccupied() {
        return (this.occupyingPiece != null);
    }
	
	public void setDisplay(boolean v) {
        this.dispPiece = v;
    }
	
	public void put(Piece p) {
        this.occupyingPiece = p;
        p.setPosition(this);
    }
	
	public Piece removePiece() {
        Piece p = this.occupyingPiece;
        this.occupyingPiece = null;
        return p;
    }
	
	public void capture(Piece p) {
        Piece k = getOccupyingPiece();
        System.out.println(k.getPieceValue());
        if (k.getColor() == 0) board.Bpieces.remove(k);
        if (k.getColor() == 1) board.Wpieces.remove(k);
        this.occupyingPiece = p;
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (this.color == 1) {
            g.setColor(new Color(232, 235 ,239));
        } else {
            g.setColor(new Color(118, 150, 86));
        }
		
		g.fillRect(this.getX(), this.getY(), this.getWidth(), getHeight());
		
		if(occupyingPiece != null && dispPiece) {
            occupyingPiece.draw(g);
        }
	}
	
	public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + xNum;
        result = prime * result + yNum;
        return result;
    }
	
}
