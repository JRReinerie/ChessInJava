package chessProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameWindow {

	private JFrame gameWindow;
	private Board board;
	
	public GameWindow() {
		gameWindow = new JFrame("Chess Game v1.0 by Jason");
		
		gameWindow.setPreferredSize(new Dimension(768, 768));
		
		gameWindow.setLocation(100, 100);
		gameWindow.setLayout(new BorderLayout(20,20));
		
//		gameWindow.setLayout(new BorderLayout());
		
//		JPanel gameData = gameDataPanel();
//        gameData.setSize(gameData.getPreferredSize());
		
		this.board = new Board(this);
		gameWindow.add(board, BorderLayout.CENTER);
        
        gameWindow.setMinimumSize(gameWindow.getPreferredSize());
        gameWindow.setSize(gameWindow.getPreferredSize());
        gameWindow.setResizable(false);
        
        gameWindow.pack();
        gameWindow.setVisible(true);
        gameWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
}
