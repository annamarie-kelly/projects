import java.awt.BorderLayout;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tetris extends JPanel { //JFrame {

	private JLabel statusBar;
	private Board board;

	public Tetris() {
		statusBar = new JLabel("Score: 0");
		setLayout(new BorderLayout());
		add(statusBar, BorderLayout.SOUTH);
		board = new Board(this);
		add(board);
		board.setBackground(Color.BLACK);
		board.start();
		this.setSize(200, 400);
	}

	public JLabel getStatusBar() {
		return statusBar;
	}
	
	public void pause(){
		board.pause();
	}
	


}
