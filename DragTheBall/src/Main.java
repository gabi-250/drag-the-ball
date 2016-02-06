
public class Main implements Runnable { 

	/**
	 * @param args
	 */
	
	public void run()
	{
		
	}
	public static void main(String[] args) {

		java.awt.EventQueue.invokeLater( new Runnable() {
			
			public void run()
			{
				BallFrame game = new BallFrame();
			}
			
		});
		
		
		
	}

}
