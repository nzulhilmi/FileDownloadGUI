import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * File downloader client. Downloads files of various types.
 * User can choose web address to download files.
 * User can set type of files to be download.
 * User can set folder path for the files to be downloaded.
 * All the files to be download will be stored in a queue.
 * @author nik
 * @dateCreated 23 November 2015
 */
public class fileDownload
{
	private static ConcurrentLinkedQueue<ArrayList<String>> jobQueue
		= new ConcurrentLinkedQueue<ArrayList<String>>();
	private static String webaddress = fileDownloadPanel.getWebAddress();
	private static String folderPath = fileDownloadPanel.getFolderPath();
	private static String fileType = fileDownloadPanel.getFileTypes();
	
	/**
	 * Main class that runs fileDownload.
	 * @param args
	 * @throws IOException If an input or output exception occur
	 */
	public static void main(String[] args) throws IOException
	{
		fileDownload();
		downloadRunnableFrame.main(null);
	}
	
	/**
	 * File download client.
	 * @throws IOException If an input or output exception occur
	 */
	public static void fileDownload() throws IOException
	{
		Document doc;
		
		try
		{	
			//split filetypes
			String[] types;
			types = fileType.split(",");
			//remove spaces after comma
			for(int i = 0; i < types.length; i++)
			{
				if(types[i].charAt(0) == ' ')
				{
					types[i] = types[i].substring(1, types[i].length());
				}
			}
			//merge the types into a single string
			if(types.length != 0)
			{
				fileType = types[0];	
				for(int i = 1; i < types.length; i++)
				{
					fileType = fileType + "|" + types[i];
				}
			}
			String select = "img[src~=\\.(" + fileType + ")]";
			
			//get all files
			doc = Jsoup.connect(webaddress).get();
			
			// selector uses CSS selector with regular expression	
			//download images
			
			System.out.println("\n" + "Downloading images..." +"\n");
			//Elements files = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
			Elements images = doc.select(select);
			for(Element image : images)
			{
				String urlstr = image.attr("src");

				System.out.println(urlstr);
				
				if(urlstr.indexOf(webaddress)<=0)
				{
					urlstr = webaddress + urlstr;
				}
				
				System.out.println(urlstr);
				String fileName = urlstr.substring(urlstr.lastIndexOf('/')+1, urlstr.length() );
				System.out.println(fileName);
				
				ArrayList<String> jobs = new ArrayList<String>();
				
				//add to job queue
				String downloadPath = folderPath + fileName;
				jobs.add(downloadPath);
				jobs.add(urlstr);
				jobQueue.add(jobs);
			}//end of for loop
			
			//download files
			System.out.println("\n" + "Downloading files..." +"\n");
			
			Elements files = doc.select("a[href]");
			for(Element file : files)
			{
				String urlstr = file.attr("href");
				System.out.println(urlstr);
				if(urlstr.length() >= 4)
				{
					if(!urlstr.substring(0, 4).equals("http"))
					{
						urlstr = webaddress + urlstr;
					}
					
					System.out.println(urlstr);

					String fileName = urlstr.substring(urlstr.lastIndexOf('/')+1, urlstr.length());
					System.out.println(fileName);
					
					//check filter
					if(fileName.length() >= 3)
					{
						String typeOfFile = fileName.substring(fileName.length()-3);
						if(Arrays.asList(types).contains(typeOfFile)) // || typeOfFile.equals("com")
						{
							//Open a URL Stream
							
							ArrayList<String> jobs = new ArrayList<String>();
							
							//add to job queue
							String downloadPath = folderPath + fileName;
							jobs.add(downloadPath);
							jobs.add(urlstr);
							jobQueue.add(jobs);
						}
					}
				}
			}
			
		}//end of try
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * User can choose the size of the buffer needed.
	 * @param is InputStream
	 * @param os OutputStream
	 * @throws IOException If an input or output exception occur
	 */
	private void writeFile(InputStream is, OutputStream os) throws IOException
	{
		byte[] buf = new byte[512]; //optimise the size of buffer to your need
		int num;
		while((num = is.read(buf)) != -1)
		{
			os.write(buf, 0, num);
		}
	}
	
	//get methods
	public static String getFolderPath()
	{
		return folderPath;
	}
	
	public static ConcurrentLinkedQueue<ArrayList<String>> getQueue()
	{
		return jobQueue;
	}
}
