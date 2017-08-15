import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI Panel for file downloader.
 * @author nik
 * @dateCreated 24 November 2015
 */
public class fileDownloadPanel extends JPanel
{
	//button to start download
	private JButton startDownload;
	
	//text fields
	private JTextField webAddress;
	private JTextField folderPath;
	private JTextField fileTypes;
	
	//labels for text fields
	private JLabel web;
	private JLabel path;
	private JLabel type;
	
	private JLabel webLabel;
	private JLabel pathLabel;
	private JLabel typeLabel;
	
	//strings for downloader
	private static String website = "";
	private static String downloadPath = "";
	private static String fileFormat = "";
	
	/**
	 * Configure the GUI panel with buttons, labels, etc.
	 */
	public fileDownloadPanel()
	{
		//GUI for web address
		web = new JLabel("Enter web address: ");
		web.setBounds(100, 20, 300, 30);
		
		webLabel = new JLabel("Type 1 for address: http://melvynmathews.co.uk/test/");
		webLabel.setBounds(50, 32, 400, 30);
		
		webAddress = new JTextField();
		webAddress.setBounds(50, 61, 400, 50);
		webAddress.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set web address
				if(webAddress.getText().equals("1"))
				{
					website = "http://melvynmathews.co.uk/test/";
				}
				else
				{
					website = webAddress.getText();
				}
			}
		});
		
		//GUI for download path
		path = new JLabel("Enter download path: ");
		path.setBounds(100, 130, 300, 30);
		
		pathLabel = new JLabel("Type 1 for path: /home/nik/work/ssc/Exercise4/testFolder/");
		pathLabel.setBounds(40, 142, 420, 30);
		
		folderPath = new JTextField();
		folderPath.setBounds(50, 171, 400, 50);
		folderPath.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set folder path
				if(folderPath.getText().equals("1"))
				{
					downloadPath = "/home/nik/work/ssc/Exercise4/testFolder/";
				}
				else
				{
					downloadPath = folderPath.getText();
				}
			}
		});
		
		//GUI for file types
		type = new JLabel("Enter file types: ");
		type.setBounds(100, 240, 300, 30);
		
		typeLabel = new JLabel("Type 1 for all file types");
		typeLabel.setBounds(50, 252, 400, 30);
		
		fileTypes = new JTextField();
		fileTypes.setBounds(50, 281, 400, 50);
		fileTypes.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//set file types
				if(fileTypes.getText().equals("1"))
				{
					fileFormat = "png,jpeg,jpg,gif,pdf,com,zip";
				}
				else
				{
					fileFormat = fileTypes.getText();
				}
			}
		});
		
		startDownload = new JButton("Extract links");
		startDownload.setBounds(100, 400, 300, 50);
		startDownload.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//run file downloader
				try
				{
					fileDownload.main(null);
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		setupPanel();
	}

	/**
	 * Adds all the features to the panel.
	 */
	private void setupPanel()
	{
		this.setLayout(null);
		this.add(web);
		this.add(webAddress);
		this.add(webLabel);
		this.add(path);
		this.add(folderPath);
		this.add(pathLabel);
		this.add(type);
		this.add(fileTypes);
		this.add(typeLabel);
		this.add(startDownload);
	}
	
	//get methods for the strings
	/**
	 * Get the website address.
	 * @return Returns website.
	 */
	public static String getWebAddress()
	{
		return fileDownloadPanel.website;
	}
	
	/**
	 * Get the folder path.
	 * @return Returns downloadPath.
	 */
	public static String getFolderPath()
	{
		return fileDownloadPanel.downloadPath;
	}
	
	/**
	 * Get the file types.
	 * @return Returns filFormat.
	 */
	public static String getFileTypes()
	{
		return fileDownloadPanel.fileFormat;
	}
}
