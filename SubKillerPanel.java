import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;




public class SubKillerPanel extends JPanel {


	// private int width, height;  // The size of the panel -- the values are set
	//    the first time the paintComponent() method
	//    is called.  This class is not designed to
	//    handle changes in size; once the width and
	//    height have been set, they are not changed.
	//    Note that width and height cannot be set
	//    in the constructor because the width and
	//    height of the panel have not been set at
	//    the time that the constructor is called.

	private Boat boat;          // The boat, bomb, and sub objects are defined
	private Bomb bomb;          //    by nested classes Boat, Bomb, and Submarine,
	private Submarine sub;      //    which are defined later in this class.
	//    Note that the objects are created in the
	//    paintComponent() method, after the width
	private int subSpeed = 1;
	private ScorePanel scorePanel; //needs reference to inform when to increment score
	
	public SubKillerPanel(ScorePanel s){
		setBackground( new Color(0,200,0) ); 
		scorePanel = s;
	}

	public void incrementScore(){
		scorePanel.setScore(scorePanel.getScore() + 1);
		scorePanel.getLabel().setText("Score: " + scorePanel.getScore());
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);  // Fill panel with background color, green.

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//*** Initialize if it's the first time
		// The reason we don't do it in the constructor is because we don't know the 
		// panel's getHeight() and getWidth() at that time, and the sub would be drawn off the screen
		if (boat == null) {
			// The first time that paintComponent is called, it assigns
			// values to the instance variables.
			boat = new Boat(this);
			sub = new Submarine(this);
			bomb = new Bomb(this, boat, sub);
		}

		if (hasFocus())
			g.setColor(Color.CYAN);
		else {
			g.setColor(Color.BLACK);
			g.drawString("CLICK TO ACTIVATE", 20, 30);
			g.setColor(Color.GRAY);
		}
		g.drawRect(0,0,this.getWidth()-1,this.getHeight()-1);  // Draw a 3-pixel border.
		g.drawRect(1,1,this.getWidth()-3,this.getHeight()-3);
		g.drawRect(2,2,this.getWidth()-5,this.getHeight()-5);

		//** Like we saw with the Rectangle class, it makes good object-oriented style
		// and also helps readability of the paintComponent method if we have
		boat.draw(g); // Boat, Sub, Bomb objects knowing how to draw themselves
		sub.draw(g);
		for(int i = 0; i < subSpeed; i++)
			sub.updateForNewFrame();
			sub.draw(g);		
		bomb.draw(g);

	}
	public void restart(){
		boat = null;
		bomb = null;
		sub = null;
		scorePanel.setScore(0);
		scorePanel.getLabel().setText("Score: " + scorePanel.getScore());
		this.subSpeed = 1;
		scorePanel.getSlider().setValue(1);
	}

	public Boat getBoat() {
		return boat;
	}

	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public Submarine getSub() {
		return sub;
	}

	public void setSub(Submarine sub) {
		this.sub = sub;
	}

	public void setSubSpeed(int speed){
		subSpeed = speed;
	}
}
