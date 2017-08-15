import javax.swing.JFrame;

/**
 * Frame for the file downloader GUI.
 * @author nik
 * @dateCreated 24 November 2015
 */
public class fileDownloadFrame extends JFrame
{
	private fileDownloadPanel currentPanel;
	
	/**
	 * Set the GUI properties.
	 * @param args
	 */
	public static void main(String[] args)
	{
		fileDownloadFrame GUI = new fileDownloadFrame();
		GUI.setVisible(true);
		GUI.setSize(500, 500);
		
		GUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Creates new panel object.
	 */
	public fileDownloadFrame()
	{
		currentPanel = new fileDownloadPanel();
		
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
