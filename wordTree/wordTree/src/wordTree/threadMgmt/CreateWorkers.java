package wordTree.threadMgmt;

import wordTree.util.FileProcessor;
import wordTree.tree.Tree;
import java.lang.Thread;
import wordTree.util.MyLogger;

public class CreateWorkers
{

	PopulateThread populatethread;
	DeleteThread deletethread;
	FileProcessor fileprocessor;
	Tree tree;
	Thread[] threads_array;

	public CreateWorkers(FileProcessor filename, Tree temp_tree)
	{
		this.fileprocessor = filename;
		this.tree = temp_tree;
		MyLogger.writeMessage("CreateWorkers object is initialized",MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * This method create the Number of Threads
	 * @param numThreads specify the number of threads to be create
	 * @return null
	 */

	public void startPopulateWorkers(String numThreads)
	{

		threads_array = new Thread[Integer.parseInt(numThreads)];
		for(int i=0;i<(Integer.parseInt(numThreads));i++)
		{
			threads_array[i] = new Thread(new PopulateThread(fileprocessor,tree));
			//MyLogger.writeMessage("Thread Created",MyLogger.DebugLevel.THREAD_CREATE);
			threads_array[i].start();
		}

		for(int i = 0; i < threads_array.length; i++)
		{
			try
			{
				threads_array[i].join();
			}
			catch(InterruptedException e)
			{
				System.err.println("Thread InterruptedException occured!!");
				e.printStackTrace();
				System.exit(0);
			}
		}

	}

	/**
	 * This method create the Number of Threads
	 * @param numThreads specify the number of threads to be create
	 * @param deleteWords list of words which has to be deleted
	 * @return null
	 */

	public void startDeleteWorkers(String numThreads, String[] deleteWords)
	{
		threads_array = new Thread[Integer.parseInt(numThreads)];
		for(int i=0;i<(Integer.parseInt(numThreads));i++)
		{
			threads_array[i] = new Thread(new DeleteThread(tree,deleteWords[i]));
			//MyLogger.writeMessage("Thread Created",MyLogger.DebugLevel.THREAD_CREATE);
			threads_array[i].start();
		}

		for(int i = 0; i < threads_array.length; i++)
		{
			try
			{
				threads_array[i].join();
			}
			catch(InterruptedException e)
			{
				System.err.println("Thread InterruptedException occured!!");
				e.printStackTrace();
				System.exit(0);
			}
		}

	}

}
