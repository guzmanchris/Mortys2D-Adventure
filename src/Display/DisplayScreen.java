package Display;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class DisplayScreen {

	private JFrame frame;
	private Canvas canvas;

	private URL iconURL;
	
	private String title;
	private int width, height;
	
	public DisplayScreen(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;



		createDisplay();
	}
	
	private void createDisplay(){
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setFocusTraversalKeysEnabled(false);
		frame.setBackground(Color.black);

		iconURL = getClass().getResource("/Sheets/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		frame.setIconImage(icon.getImage());

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBackground(Color.black);
		
		frame.add(canvas);
		frame.pack();
	}

	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
}
