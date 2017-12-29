package wordTree.computeResult;

import wordTree.tree.Tree;
import wordTree.store.Results;
import wordTree.node.Node;
import wordTree.util.MyLogger;


public class ComputeResult
{

	private Node root = null;
	private int totalWord = 0;
	private int totalChar = 0;
	private int uniqueWord = 0;

	public ComputeResult()
	{
		MyLogger.writeMessage("ComputeResult object is initialized",MyLogger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * This method write the total number of words, characters and unique words.
	 * @param tree the input tree
	 * @param rs the instance of Results class
	 * @return null
	 */

	public void compute(Tree tree, Results rs)
	{
		root = tree.getRoot();
		computeHelper(root);
		rs.storeNewResult("The total number of words: " + totalWord);
		rs.storeNewResult("The total number of characters: " + totalChar);
		rs.storeNewResult("The total number of unique words: " + uniqueWord);
	}

	/**
	 * This method computes the total number of words, characters and unique words.
	 * @param root the root of the tree
	 * @return null
	 */

	public void computeHelper(Node root)
	{
		if(root != null)
		{
			totalWord = totalWord + root.count;
			//System.out.println("word: "+root.word+" Count: "+root.count +" totalWord: " +totalWord);
			totalChar = totalChar + (root.word.length() * root.count);
			uniqueWord++;	
			computeHelper(root.left);
			computeHelper(root.right);
		}


	}


}
