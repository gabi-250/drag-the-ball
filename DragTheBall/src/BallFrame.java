import javax.swing.JFrame;
import java.awt.Dimension;

public class BallFrame extends JFrame {

	private BallPanel panel;
	
	public BallFrame()
	{
		super("Drag the ball!");
		panel = new BallPanel();
		setSize(600, 600);
		panel.setPreferredSize(new Dimension(600, 600));
		getContentPane().add(panel);
		panel.setFrame(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

	}


}
