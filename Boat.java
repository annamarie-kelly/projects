import java.awt.Color;
import java.awt.Graphics;

public class Boat {
	
	private int centerX, centerY;  // Current position of the center of the boat.
	private SubKillerPanel gamePanel;
	
	Boat(SubKillerPanel gamePanel) { // Constructor centers the boat horizontally, 80 pixels from top.
        this.gamePanel = gamePanel;
		centerX = gamePanel.getWidth()/2;
        centerY = 80;
    }
	
    void updateForNewFrame() { // Makes sure boat has not moved off screen.
        if (centerX < 0)
            centerX = 0;
        else if (centerX > gamePanel.getWidth())
            centerX = gamePanel.getHeight();
    }
    void draw(Graphics g) {  // Draws the boat at its current location.
        g.setColor(Color.BLUE);
        g.fillRoundRect(centerX - 40, centerY - 20, 80, 40, 20, 20);
    }

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
}
