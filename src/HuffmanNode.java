import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanNode {
	
	private char type = 'l';
	private Byte data = null;
	private int frequency = 0;
	
	private HuffmanNode right = null;
	private HuffmanNode left = null;
	
	public HuffmanNode(Byte data, Integer frequency) {
		// TODO Auto-generated constructor stub
		this.setData(data);
		this.setFrequency(frequency);
		
	}

	public HuffmanNode() {
		// TODO Auto-generated constructor stub
	}
	
	public void makeDictionary(Map<Byte, String> dictionary) {
		// saves the path through the tree so we can make the code 
		ArrayList<Character> codeMaker = new ArrayList<Character>();
		
		String code = "";
		
		// if node is a leaf, add to the dictionary with its code
		if (this.type == 'l') {
			
			// build the code from the array
			for (Character c : codeMaker) {
				code += c;
			}
			
			dictionary.put(this.data, code);
			
		}
		// if not
		else {
			
			// indicates that goes left and goes left
			codeMaker.add('0');
			this.left.makeDictionary(dictionary, codeMaker);
			
			// indicates that is going back up
			codeMaker.remove(codeMaker.size() - 1);
			
			// indicates thas goes right and goes right
			codeMaker.add('1');
			this.right.makeDictionary(dictionary, codeMaker);
			
		}
	}

	private void makeDictionary(Map<Byte, String> dictionary, ArrayList<Character> codeMaker) {
		String code = "";
		
		// if node is a leaf, add to the dictionary with its code
		if (this.type == 'l') {
			
			// build the code from the array
			for (Character c : codeMaker) {
				code += c;
			}
			
			dictionary.put(this.data, code);
			
		}
		else {
			// indicates that goes left and goes lef
			codeMaker.add('0');
			this.left.makeDictionary(dictionary, codeMaker);
			
			// indicates that is going back up
			codeMaker.remove(codeMaker.size() - 1);

			// indicates that goes right and goes right
			codeMaker.add('1');
			this.right.makeDictionary(dictionary, codeMaker);
			
			// indicates that is going back up
			codeMaker.remove(codeMaker.size() - 1);
			
		}
		
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public Byte getData() {
		return data;
	}

	public void setData(Byte data) {
		this.data = data;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public HuffmanNode getRight() {
		return right;
	}

	public void setRight(HuffmanNode right) {
		this.right = right;
	}

	public HuffmanNode getLeft() {
		return left;
	}

	public void setLeft(HuffmanNode left) {
		this.left = left;
	}
	
}
