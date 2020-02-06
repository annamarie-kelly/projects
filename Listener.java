import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//http://playfreetetris.com directions from here

import javax.swing.JOptionPane;

public class Listener implements ActionListener{

	private TetrisBoard tetris;

	public Listener(TetrisBoard tetris){
		this.tetris = tetris;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("Instructions")) {
			tetris.getGame().pause();
			JOptionPane.showMessageDialog(tetris, "Left arrow and right arrow position the block\n"
					+ "Up arrow changes the rotation of the block\n"
					+ "Down arrow makes the block drop\n"
					+ "Use your space bar to hard drop");
			tetris.getGame().pause();
		}
		else if(s.equals("Objective")) {
			tetris.getGame().pause();
			JOptionPane.showMessageDialog(tetris, "The objective of Tetris is to complete full solid lines (no gaps).\n"
					+ "When you make a line it disappears and all the blocks shift accordingly. If you let the blocks\n"
					+ "reach the top of the Tetris board, you lose. You cannot win a Tetris game, though you can attempt\n"
					+ "to get the high score.");
			tetris.getGame().pause();
		}
		else if(s.equals("Quit")) {
			System.exit(0);
		}
		else if(s.equals("Motivation")) {
			tetris.getGame().pause();
			int rand = (int) (Math.random()*6)+1;
			switch(rand) {
				case 1:
					JOptionPane.showMessageDialog(tetris, "IDK what to say here...");
					break;
				case 2:
					JOptionPane.showMessageDialog(tetris, "I am a nobody, nobody is perfect, therefore I am perfect.\n(lol, you thought I was gonna give you motivation... SIKEEEEE)");
					break;
				case 3:
					JOptionPane.showMessageDialog(tetris, "You got this!");
					break;
				case 4:
					JOptionPane.showMessageDialog(tetris, "Just try harder,\n we both know you're not doing your best.");
					break;
				default: 
					JOptionPane.showMessageDialog(tetris, "Even if you suck at Tetris,\n I'll still love you");
					break;
			}
			tetris.getGame().pause();
		}




	}
}
