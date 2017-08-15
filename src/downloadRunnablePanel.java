import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI Panel for download runnable.
 * @author nik
 * @dateCreated 24 November 2015
 */
public class downloadRunnablePanel extends JPanel
{
	private JLabel threadLabel;
	private JTextField threadNo;
	private JButton startDownload;
	private static int nThread;
	
	/**
	 * Set up the panel, fill in with buttons, text fields, etc.
	 */
	public downloadRunnablePanel()
	{
		//GUI for downloadRunnable
		threadLabel = new JLabel("Select no. of threads");
		threadLabel.setBounds(50, 20, 400, 30);
		
		threadNo = new JTextField();
		threadNo.setBounds(50, 49, 400, 50);
		threadNo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				nThread = Integer.parseInt(threadNo.getText());
			}
		});
		
		startDownload = new JButton("Start download");
		startDownload.setBounds(100, 300, 300, 50);
		startDownload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//run download runnable
				downloadRunnable.main(null);
			}
		});
		
		setupPanel();
	}
	
	/**
	 * Adds all the buttons, text fields into the panel.
	 */
	private void setupPanel()
	{
		this.setLayout(null);
		this.add(threadLabel);
		this.add(threadNo);
		this.add(startDownload);
	}
	
	//get method for number of threads
	/**
	 * Method to get the number of threads.
	 * @return Returns the number of threads.
	 */
	public static int getThreadNo()
	{
		return nThread;
	}
}
