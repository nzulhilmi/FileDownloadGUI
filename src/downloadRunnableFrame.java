import javax.swing.JFrame;

/**
 * Frame for downloadRunnableFrame GUI.
 * @author nik
 * @dateCreated 24 November 2015
 */
public class downloadRunnableFrame extends JFrame
{
	private downloadRunnablePanel currentPanel;
	
	/**
	 * Set the GUI properties.
	 * @param args
	 */
	public static void main(String[] args)
	{
		downloadRunnableFrame GUI = new downloadRunnableFrame();
		GUI.setVisible(true);
		GUI.setSize(500, 400);
		
		//GUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Set up the GUI frame.
	 */
	public downloadRunnableFrame()
	{
		currentPanel = new downloadRunnablePanel();
		
		setupFrame();
	}
	
	/**
	 * Set up the GUI frame.
	 */
	private void setupFrame()
	{
		getContentPane().setLayout(null);
		this.setContentPane(currentPanel);
	}
}
