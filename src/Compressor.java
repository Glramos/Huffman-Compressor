import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.soap.Node;

import org.w3c.dom.NamedNodeMap;

public class Compressor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String option = "-c";
		
		// get one path to the file to be compressed or decompressed
		Path path = Paths.get("C:\\Users\\Gabriel Ramos\\Desktop\\texttest2.txt");
		
		if (option.equals("-c"))
			encode(path);
		else if (option.equals("-d"))
			decode(path);
		else {
			System.err.println("Command do not recognized by the program, system will be closed.");
			System.exit(0);
		}
	}
	
	private static void encode(Path path) {
		// TODO Auto-generated method stub

		byte[] data = null;
		
		// tries to extract the bytes of data from the file
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("File do not exist in this path, system will be closed.");
			e.printStackTrace();
			
			System.exit(0);
		}
		
		ArrayList<HuffmanNode> words = new ArrayList<HuffmanNode>();
		Map < Byte, Integer > dataFrequency = new TreeMap < Byte, Integer > ();
		
		// calculates the frequency for each byte
		for (Byte b : data) {
			Integer frequency = dataFrequency.get(b);
			
			if (frequency == null) {
				frequency = 0;
			}
			
			dataFrequency.put(b, frequency + 1);
		}
		
		// creates the leafs of the tree
		for (Map.Entry<Byte, Integer> entry : dataFrequency.entrySet()) {
		    words.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		    
		}
		
		int totalFrequency = 0;
		
		// calculates the total frequency of the tree
		for (HuffmanNode h : words) {
			totalFrequency += h.getFrequency();
		}
		
		HuffmanNode tree;
		
		// Mount the Huffman Tree while the root node doesn't have the total frequency of the tree
		do {
			// sorts nodes from the smallest frequency to the biggest
			Collections.sort(words, new Comparator<HuffmanNode>() {
			    @Override
			    public int compare(HuffmanNode lhs, HuffmanNode rhs) {
			        return lhs.getFrequency() < rhs.getFrequency() ? -1 : (lhs.getFrequency() > rhs.getFrequency()) ? 1 : 0;
			    }
			});
			
			/*
			 * for (HuffmanNode h : words) {
				System.out.printf("Data: %d \tType: %c \tFrequency: %d\n", h.getData(), h.getType(), h.getFrequency());
			}
			 */
			
			// creates the superior node of the tree by linking the inferiors in its right and left side
			tree = new HuffmanNode();
			
			tree.setLeft(words.get(0));
			tree.setRight(words.get(1));
			
			tree.setFrequency(words.get(0).getFrequency() + words.get(1).getFrequency());
			
			tree.setType('n');
			
			// removes the inferior nodes from the array once they are already represented in its superior node
			words.remove(1);
			words.remove(0);
			
			words.add(tree);
			
			//System.out.println();
			
		}while(tree.getFrequency() != totalFrequency);
		
		// indicates tree is the root node
		tree.setType('r');
		
		//System.out.printf("Data: %d \tType: %c \tFrequency: %d\n\n", node.getData(), node.getType(), node.getFrequency());
		
		Map<Byte, String> dictionary = new TreeMap<Byte, String>();
		
		// creates the huffman dictionary based on the tree built
		tree.makeDictionary(dictionary);
		
		System.out.printf("\tByte\t\tCode\t\n");
		
		for (Map.Entry<Byte, String> entry : dictionary.entrySet()) {
			System.out.printf("\t%d\t\t%S\t\n", entry.getKey(), entry.getValue());
		    
		}
		
		String rawData = "";
		
		// codificates data into a string just for visualization
		for (Byte b : data) {
			rawData += dictionary.get(b);
		}
		
		System.out.println();
		System.out.println(rawData);
		
		System.exit(0);
	}
	
	private static void decode(Path path) {
		// TODO Auto-generated method stub
		
	}


}
