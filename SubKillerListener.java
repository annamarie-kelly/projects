import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SubKillerListener implements KeyListener, FocusListener, 
MouseListener, ActionListener, ChangeListener{
	SubKillerPanel gamePanel;
	Timer timer;

	public SubKillerListener(SubKillerPanel gamePanel, ScorePanel scorePanel){
		this.gamePanel = gamePanel;
		// register listener
		gamePanel.addMouseListener(this);
		gamePanel.addKeyListener(this);
		gamePanel.addFocusListener(this);
		// no gamePanel.addActionListener()!!
		// JPanel doesn't generate ActionEvents
		// ActionEvents are generate by timers, JButtons, JMenus, JTextfeilds...
		timer = new Timer(30, this);
		timer.start();
		// score panel is a JPanel. JPaneldoes not generate ChangeEvents.
		scorePanel.getSlider().addChangeListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //called for timer ticks
		String s = e.getActionCommand();
		if(s == null){
			if (gamePanel.getBoat() != null) {
				gamePanel.getBoat().updateForNewFrame();
				gamePanel.getBomb().updateForNewFrame();
				gamePanel.getSub().updateForNewFrame();
			}
		}
		else if(s.equals("About")){
			JOptionPane.showMessageDialog(gamePanel, "This game rocks!");
		}
		else if(s.equals("Quit")){
			System.exit(0);
		}
		else if(s.equals("Restart")){
			gamePanel.restart();
		}
		gamePanel.repaint();
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		gamePanel.requestFocus();
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void focusGained(FocusEvent e) {
		timer.start();
		gamePanel.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		timer.stop();
		gamePanel.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();  // Which key was pressed?
		if (code == KeyEvent.VK_LEFT) {
			// Move the boat left.  (If this moves the boat out of the frame, its
			// position will be adjusted in the boat.updateForNewFrame() method.)
			gamePanel.getBoat().setCenterX(gamePanel.getBoat().getCenterX() - 15);
		}
		else if (code == KeyEvent.VK_RIGHT) {  
			// Move the boat right.  (If this moves boat out of the frame, its
			// position will be adjusted in the boat.updateForNewFrame() method.)
			gamePanel.getBoat().setCenterX(gamePanel.getBoat().getCenterX() + 15);
		}
		else if (code == KeyEvent.VK_DOWN) {
			// Start the bomb falling, if it is not already falling.
			if ( gamePanel.getBomb().isFalling() == false )
				gamePanel.getBomb().setFalling(true);
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		if(!source.getValueIsAdjusting()){ //only once they have stopped adjusting slider
			int difficultyLevel = source.getValue();
			gamePanel.setSubSpeed(difficultyLevel);
			gamePanel.requestFocusInWindow();
		}
	}

}
