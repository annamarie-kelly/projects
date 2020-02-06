import java.awt.*;
import javax.swing.*;


public class SubKillerMain {
	
	 public static void main(String[] args) {
	        JFrame window = new JFrame("Sub Killer Game");
	        
	        JPanel container = new JPanel();
	        container.setLayout(new BorderLayout());

	        
	       
	        ScorePanel scorePanel = new ScorePanel();
	        SubKillerPanel content = new SubKillerPanel(scorePanel);
	        SubKillerListener listener = new SubKillerListener(content, scorePanel);

	        
	        container.add(content, BorderLayout.CENTER);
	        container.add(scorePanel,BorderLayout.SOUTH);

	        window.setContentPane(container);
	        
	        JMenuBar menuBar = new JMenuBar();
	        JMenu subKillerMenu = new JMenu("Sub Killer");
	        JMenu options = new JMenu("Options");
	        
	        JMenuItem about = new JMenuItem("About");
	        JMenuItem quit = new JMenuItem("Quit");
	        JMenuItem restart = new JMenuItem("Restart");
	        
	        about.addActionListener(listener);
	        quit.addActionListener(listener);
	        restart.addActionListener(listener);
	        
	        subKillerMenu.add(about);
	        subKillerMenu.addSeparator();
	        subKillerMenu.add(quit);
	        
	        options.add(restart);
	        menuBar.add(subKillerMenu);
	        menuBar.add(options);
	        
	        window.setJMenuBar(menuBar);
	        
	       
	        
	        window.setSize(600, 480);
	        window.setLocation(100,100);
	        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	        window.setResizable(false);  // User can't change the window's size.
	        window.setVisible(true);
	    }
}
