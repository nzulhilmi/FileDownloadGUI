import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
//import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread used to download files from job queue.
 * @author nik
 * @dateCreated 23 November 2015
 */
class myThread extends Thread
{
	private static ConcurrentLinkedQueue<ArrayList<String>> jobQueue = fileDownload.getQueue();
	private String urlstr = "";
	private String downloadPath = fileDownload.getFolderPath();
	private ArrayList<String> list = new ArrayList<String>();
	
	/**
	 * The thread will retrieve files from the queue, download them,
	 * and save them in local hard disk.
	 */
	public synchronized void run()
	{	
		try
		{	
			if(jobQueue.size() > 0)
			{
				System.out.println("Retrieving file from the queue");
				//get size of the queue
				int size = jobQueue.size();
				
				/*
				if(size == 1)
				{
					downloadRunnable.running = false;
				}
				*/
				
				//get task from the job queue
				list = jobQueue.poll();
				
				notify(); //to start next thread
				
				//get details of the file to be downloaded
				downloadPath = list.get(0);
				urlstr = list.get(1);
				
				String fileName = downloadPath.substring(downloadPath.lastIndexOf('/')+1, downloadPath.length());
				
				//tell the user the download is starting
				System.out.println("Starting download " + fileName + ". Remaining: " + size);
				
				//Open a URL Stream
				URL url = new URL(urlstr);
				InputStream in = url.openStream();
				OutputStream out = new BufferedOutputStream(new FileOutputStream(downloadPath));
				
				for(int b; (b = in.read()) != -1;)
				{
					out.write(b);
				}
				out.close();
				in.close();
				
				System.out.println("Download " + fileName + " finished.");
			}
			else
			{
				downloadRunnable.running = false;
			}
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the size of the queue
	 * @return Returns and integer, the size of the queue
	 */
	public static int getSizeQueue()
	{
		return jobQueue.size();
	}
}

/**
 * Creates thread pool and run the threads to download files.
 * User can specify the number of threads desired.
 * Each thread download a single file.
 * @author nik
 * @dateCreated 23 November 2015
 */
public class downloadRunnable
{	
	public static Boolean running = true;
	
	public static void main(String[] args)
	{
		downloadRunnable();
	}
	
	/**
	 * Implementation of thread pool
	 */
	public static void downloadRunnable()
	{
		//create thread objects
		myThread m1 = new myThread();
		myThread m2 = new myThread();
		myThread m3 = new myThread();
		myThread m4 = new myThread();
		
		//get number of threads from download runnable panel
		int nThread = downloadRunnablePanel.getThreadNo();
		
		//create thread pool
		ExecutorService pool = Executors.newFixedThreadPool(nThread);
		
		//execute threads
		while(running)
		{
			if(myThread.getSizeQueue() > 0)
			{
				pool.execute(m1);
				pool.execute(m2);
				pool.execute(m3);
				pool.execute(m4);
			}
			if(myThread.getSizeQueue() < 1)
			{
				pool.shutdown();
				System.out.println("Thread pool has been shut down");
				break;
			}
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
}