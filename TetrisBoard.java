import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TetrisBoard extends JPanel {

	private Tetris tetris;

	public TetrisBoard() {
		tetris = new Tetris();
		setLayout(new BorderLayout());
		add(tetris, BorderLayout.CENTER);
		tetris.setBackground(Color.GRAY);		 
	}
	
	public Tetris getGame(){
		return tetris;
	}
	
	public void newGame(){
		tetris = new Tetris();
	}

	public static void main(String[] args){
		JFrame window = new JFrame("Tetris Game");

		JMenuBar menuBar = new JMenuBar();

		JMenu tetrisMenu = new JMenu("Tetris");
		JMenu options = new JMenu("Options");

		JMenuItem instructions = new JMenuItem("Instructions");
		JMenuItem objective = new JMenuItem("Objective");
		JMenuItem quit = new JMenuItem("Quit");
		JMenuItem motivation = new JMenuItem("Motivation");
		
		TetrisBoard myTetris = new TetrisBoard();
		window.setContentPane(myTetris);
		window.setLocation(450,100);
		window.setVisible(true);
		window.setResizable(false);
		window.setSize(200, 420);
		window.setTitle("My Tetris");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		instructions.addActionListener(new Listener(myTetris));
		objective.addActionListener(new Listener(myTetris));
		quit.addActionListener(new Listener(myTetris));
		motivation.addActionListener(new Listener(myTetris));
		
		tetrisMenu.add(instructions);
		tetrisMenu.addSeparator();
		tetrisMenu.add(objective);



		options.add(motivation);
		options.addSeparator();
		options.add(quit);
		menuBar.add(tetrisMenu);
		menuBar.add(options);
		window.setJMenuBar(menuBar);



		

	}
}
