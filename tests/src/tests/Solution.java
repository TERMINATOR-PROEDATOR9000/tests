package tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.github.abrarsyed.jastyle.ASFormatter;
import com.github.abrarsyed.jastyle.FormatterHelper;
import com.github.abrarsyed.jastyle.constants.EnumFormatStyle;

import java.lang.reflect.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Solution {

    public static void main(String... args) throws Exception {

	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}

	// new BinarySearch().recursionSearch(55, 0, 15);
	// new TreeApp().main(new String[] {});
	// new AppletViewer().init();
	// new Random().ints(Integer.MAX_VALUE / 2,
	// Integer.MAX_VALUE).limit(50).forEach(i -> System.out.println(i % 60));
	// new RealHashKarta();
	// new AfterPDFtoFB2Convertor();	
    }

}


/*class Pars {
    private static StringBuilder sb = new StringBuilder();
    private static String mainurl = "";
    public static void main(String... args) {
	try {
	    List<String> links = new ArrayList<String>();
	    Document doc = Jsoup.connect("").userAgent("Chrome/81.0.4044.138").get();
	    Elements text = doc.getElementsByTag("a");
	    for (Element el : text) {
		String aa = el.attr("href");
		if (aa.contains("books/")) {
		    links.add(mainurl + aa);
		}
	    }
	    System.out.println(links);
	    new Pars().worker(links);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    Files.write(Paths.get("C:\\Users\\*\\Desktop\\res.txt"), sb.toString().getBytes());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    private void worker(List<String> ls) {
	
	try {
	    //for (String s : ls) {
		String s=ls.get(10);		
		Document doc = Jsoup.connect(s).userAgent("Chrome/81.0.4044.138").get();
		Elements elem = doc.getElementsByTag("code");		
		AtomicInteger count = new AtomicInteger(0);
		// StringBuilder sb = new StringBuilder();
		String[] code =elem.text().split("~");
		System.err.println("-------->"+code.length);
		//for (int i = 0; i < code.length; i++) {
		//    System.out.println(code[i]);
		//}
		for (int i = 0; i < code.length; i++) {
		    code[i] = code[i] + "\n";
		}
		doc.body().html().lines().forEach(e -> {		    
		    e = e.trim();
		    if (e.startsWith("<p>")) {
			e = e.replace("<p>", "");
			if (e.endsWith("</p>")) {
			    e = e.replace("</p>", "");
			}
			sb.append(e + "\n");
		    }
		    if (e.startsWith("<li>") && !e.contains("href")) {
			e = e.replace("<li>", "");
			if (e.endsWith("</li>")) {
			    e = e.replace("</li>", "");
			}
			sb.append("\t* " + e + "\n");
		    }
		    if (e.contains("<code")) {
			sb.append("\n" + transform(code[count.getAndIncrement()]) + "\n");
			//sb.append("\n" + transform(elem.text()) + "\n");
			// System.out.println(elem.text());
		    }
		});
	    //}
	} catch (Exception e2) {
	     e2.printStackTrace();
	}
	//System.out.println(sb);
    }
    private static String transform(String s) {	
	List<String> arr = List.of(s.split(" "));
	String newS = "";
	for (String as : arr) {
	    if (as.equals("//") || as.equals("//:") || as.equals("/:") || as.equals("/ :")) {
		continue;
	    }
	    newS += as + " ";
	}
	System.out.println(newS);
	String result = " ";
	//s = s.replaceAll("//: ", "");
	//s = s.replaceAll("// ", "");
	BufferedReader in = null;
	try {
	    ASFormatter formatter = new ASFormatter();
	    in = new BufferedReader(new StringReader(newS));
	    formatter.setFormattingStyle(EnumFormatStyle.JAVA);
	    try {
	    result = FormatterHelper.format(in, formatter);
	    }catch (StringIndexOutOfBoundsException e) {
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		in.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	// StringBuilder sb = new StringBuilder();
	// result.lines().skip(1).forEach(e -> sb.append(e + "\n"));
	return result;
    }
}*/

enum Perech {
    HAHA, HEHE;

    public String toString() {
	return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}

class StaticCheck {
    static {
	System.out.println("StaticA");
    }
    {
	System.out.println("nonStaticA");
    }
    static {
	System.out.println("StaticB");
    }
    {
	System.out.println("nonStaticB");
    }
    static {
	System.out.println("StaticC");
    }
    {
	System.out.println("nonStaticC");
    }

    public static void invoke() {
	System.out.println("in parent");
    }

    public String toString() {
	return "StaticCheck toString";
    }

    private void x() {
    }

    public String x(int a) {
	return "";
    }

}

class StaticCheck2 extends StaticCheck {
    public static void invoke() {
	System.out.println("in child");
    }

    public static void invoke(int a) {
	System.out.println("in child " + a);
    }

    public StaticCheck2() {
	System.out.println("HAHA");
    }

    public final void str() {
	System.out.println("final meth");
    }
}

class AfterPDFtoFB2Convertor {

    AfterPDFtoFB2Convertor() throws Exception {
	init();
    }

    public void init() throws IOException {
	long start = System.nanoTime();
	StringBuilder sb = new StringBuilder();
	List<Boolean> check = Arrays.asList(false);
	String home = System.getProperty("user.home");

	Path in = Paths.get("");
	Path out = Paths.get("");

	Files.lines(in).limit(199).forEach(i -> {
	    i = i.replaceAll("\\.{3,}", "...");
	    sb.append(i + "\n");
	});

	Files.lines(in).skip(199).limit(3875).forEach(i -> {
	    if (check.get(0) == false) {
		sb.append("<p>");
		check.set(0, true);
	    }
	    i = i.substring(3, i.length() - 4);
	    if (i.endsWith(".") && check.get(0) == true) {
		check.set(0, false);
		sb.append(i + "</p>\n");
		return;
	    } else {
		sb.append(i.trim());
		sb.append("\s");
	    }
	});

	Files.lines(in).skip(199 + 3875).forEach(i -> sb.append(i + "\n"));

	byte[] b = sb.toString().getBytes();
	Files.write(out, b);
	double end = System.nanoTime() - start;
	System.out.println("Done in " + (end / 1_000_000_000) + " seconds.");
    }
}

class RealHashKarta {

    private Link[] map = new Link[97];
    private int t = new Random().nextInt(1001);
    private boolean check = false;

    public RealHashKarta() {
	init();
	show();
	search(new Link(t));
    }

    private class Link {
	private int data;
	public Link next;

	Link(int d) {
	    data = d;
	}

	public int getData() {
	    return data;
	}

	public String toString() {
	    return String.valueOf(data);
	}
    }

    private void init() {
	new Random().ints(1, 1000).limit(200).forEach(i -> {
	    if (i == t) {
		check = true;
	    }
	    Link m = new Link(i);
	    int hash = hash(m);
	    if (map[hash] != null) {
		Link cur = map[hash];
		Link prev = null;
		while (cur != null) {
		    prev = cur;
		    cur = cur.next;
		}
		prev.next = m;
	    } else {
		map[hash] = m;
	    }
	});
    }

    private int hash(Link m) {
	return m.getData() % map.length;
    }

    private void show() {
	for (int i = 0; i < map.length; i++) {
	    Link cur = map[i];
	    System.out.print("Data in bucket [" + i + "]: ");
	    while (cur != null) {
		System.out.print(" " + cur);
		cur = cur.next;
	    }
	    System.out.println();
	}
    }

    private void search(Link key) {
	int hash = hash(key);
	int position = 0;
	Link temp = map[hash];
	if (temp == null) {
	    System.err.println(
		    Thread.currentThread().getStackTrace()[1].getLineNumber() + " \tCat't find key " + key + ".");
	} else {
	    while (temp != null) {
		position++;
		if (temp.getData() == key.getData()) {
		    System.err.println("\tKey " + key + " found.\n\tBucket: [" + hash + "].\n\tPosition in bucket: "
			    + position + "." + "\n\tChecked: " + check);
		    return;
		} else {
		    temp = temp.next;
		}
	    }
	    System.err.println(
		    Thread.currentThread().getStackTrace()[1].getLineNumber() + " \tCat't find key " + key + ".");
	}
	System.err.println("\tCheck: " + check);
    }
}

class AppletViewer {

    private Path path = Paths.get("C:\\Program Files\\Java\\");
    private JFrame jFrame;
    private static Dimension size;
    private JPanel jPanel;
    private JLabel jText;
    private JFileChooser jfc;
    private JButton button;
    private String appletviewerPath;
    private String choosedFile;
    private boolean found;

    public void init() {
	jFrame = new JFrame();
	jPanel = new JPanel();
	jText = new JLabel();
	jfc = new JFileChooser();
	button = new JButton();
	appletviewerPath = "";
	choosedFile = "";
	found = false;
	size = Toolkit.getDefaultToolkit().getScreenSize();

	jFrame.setBounds((int) size.getWidth() / 2 - 550, (int) size.getHeight() / 2 - 300, 350, 200);
	jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

	String text = "Введите путь к JavaApplet HTML файлу: ";
	jText.setText(text);
	jText.setHorizontalAlignment(JLabel.CENTER);
	jText.setFocusable(false);

	jfc.setDialogTitle("Выбор файла...");
	jfc.setCurrentDirectory(new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop"));
	FileNameExtensionFilter htmlFilter = new FileNameExtensionFilter("HTML files only (*.html)", "html");
	jfc.setFileFilter(htmlFilter);

	jPanel.setLayout(new GridLayout(2, 0));

	button.setFocusable(false);
	button.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int result = jfc.showOpenDialog(jfc);
		if (result == jfc.APPROVE_OPTION) {
		    jText.setText("Выбран файл " + jfc.getSelectedFile().getName());
		    choosedFile = jfc.getSelectedFile().toString();
		    if (!choosedFile.endsWith(".html")) {
			jFrame.setTitle("Ошибка");
			jText.setText("Не верный файл. Попробуйте снова");
			return;
		    }
		    if (!appletviewerPath.equals("") && !choosedFile.equals("")) {
			String tryer = appletviewerPath + " " + choosedFile;
			try {
			    Runtime.getRuntime().exec(tryer);
			    jFrame.setTitle("Appletviewer запущен...");
			    button.setText("Выбрать новый файл...");
			} catch (IOException e1) {
			    e1.printStackTrace();
			}
		    }
		}
	    }
	});
	jPanel.add(jText);
	jPanel.add(button);
	jFrame.setTitle("Помощник запуска appletviewer");
	jFrame.add(jPanel);
	jFrame.setVisible(true);
	if (!search()) {
	    jText.setText("Не найден appletviewer. Скачайте и установите JDK 8 с официального сайта.");
	    button.setVisible(false);
	    jFrame.setTitle("Не найден appletviewer.");
	    return;
	} else {
	    button.setText("Выбрать файл");
	}
	;
    }

    private boolean search() {
	try {
	    Files.walkFileTree(path, new FileVisitor<Path>() {

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		    return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		    if (file.getFileName().toString().equals("appletviewer.exe")) {
			appletviewerPath = file.toString();
			found = true;
			return FileVisitResult.TERMINATE;
		    }
		    return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		    return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		    return FileVisitResult.CONTINUE;
		}
	    });
	} catch (Exception e) {
	    e.getStackTrace();
	}
	return found;
    }
}

class Node {
    public int iData; // data item (key)
    public double dData; // data item
    public Node leftChild; // this node's left child
    public Node rightChild; // this node's right child

    public void displayNode() // display ourself
    {
	System.out.print('{');
	System.out.print(iData);
	System.out.print(", ");
	System.out.print(dData);
	System.out.print("} ");
    }
} // end class Node
////////////////////////////////////////////////////////////////

class Tree {
    private Node root; // first node of tree

//-------------------------------------------------------------
    public Tree() // constructor
    {
	root = null;
    } // no nodes in tree yet
//-------------------------------------------------------------

    public Node find(int key) // find node with given key
    { // (assumes non-empty tree)
	Node current = root; // start at root
	while (current.iData != key) // while no match,
	{
	    if (key < current.iData) // go left?
		current = current.leftChild;
	    else // or go right?
		current = current.rightChild;
	    if (current == null) // if no child,
		return null; // didn't find it
	}
	return current; // found it
    } // end find()
//-------------------------------------------------------------

    public void insert(int id, double dd) {
	Node newNode = new Node(); // make new node
	newNode.iData = id; // insert data
	newNode.dData = dd;
	if (root == null) // no node in root
	    root = newNode;
	else // root occupied
	{
	    Node current = root; // start at root
	    Node parent;
	    while (true) // (exits internally)
	    {
		parent = current;
		if (id < current.iData) // go left?
		{
		    current = current.leftChild;
		    if (current == null) // if end of the line,
		    { // insert on left
			parent.leftChild = newNode;
			return;
		    }
		} // end if go left
		else // or go right?
		{
		    current = current.rightChild;
		    if (current == null) // if end of the line
		    { // insert on right
			parent.rightChild = newNode;
			return;
		    }
		} // end else go right
	    } // end while
	} // end else not root
    } // end insert()
//-------------------------------------------------------------

    public boolean delete(int key) // delete node with given key
    { // (assumes non-empty list)
	Node current = root;
	Node parent = root;
	boolean isLeftChild = true;

	while (current.iData != key) // search for node
	{
	    parent = current;
	    if (key < current.iData) // go left?
	    {
		isLeftChild = true;
		current = current.leftChild;
	    } else // or go right?
	    {
		isLeftChild = false;
		current = current.rightChild;
	    }
	    if (current == null) // end of the line,
		return false; // didn't find it
	} // end while
	  // found node to delete

	// if no children, simply delete it
	if (current.leftChild == null && current.rightChild == null) {
	    if (current == root) // if root,
		root = null; // tree is empty
	    else if (isLeftChild)
		parent.leftChild = null; // disconnect
	    else // from parent
		parent.rightChild = null;
	}

	// if no right child, replace with left subtree
	else if (current.rightChild == null)
	    if (current == root)
		root = current.leftChild;
	    else if (isLeftChild)
		parent.leftChild = current.leftChild;
	    else
		parent.rightChild = current.leftChild;

	// if no left child, replace with right subtree
	else if (current.leftChild == null)
	    if (current == root)
		root = current.rightChild;
	    else if (isLeftChild)
		parent.leftChild = current.rightChild;
	    else
		parent.rightChild = current.rightChild;

	else // two children, so replace with inorder successor
	{
	    // get successor of node to delete (current)
	    Node successor = getSuccessor(current);

	    // connect parent of current to successor instead
	    if (current == root)
		root = successor;
	    else if (isLeftChild)
		parent.leftChild = successor;
	    else
		parent.rightChild = successor;

	    // connect successor to current's left child
	    successor.leftChild = current.leftChild;
	} // end else two children
	  // (successor cannot have a left child)
	return true; // success
    } // end delete()
//-------------------------------------------------------------
// returns node with next-highest value after delNode
// goes to right child, then right child's left descendents

    private Node getSuccessor(Node delNode) {
	Node successorParent = delNode;
	Node successor = delNode;
	Node current = delNode.rightChild; // go to right child
	while (current != null) // until no more
	{ // left children,
	    successorParent = successor;
	    successor = current;
	    current = current.leftChild; // go to left child
	}
	// if successor not
	if (successor != delNode.rightChild) // right child,
	{ // make connections
	    successorParent.leftChild = successor.rightChild;
	    successor.rightChild = delNode.rightChild;
	}
	return successor;
    }

//-------------------------------------------------------------
    public void traverse(int traverseType) {
	switch (traverseType) {
	case 1:
	    System.out.print("\nPreorder traversal: ");
	    preOrder(root);
	    break;
	case 2:
	    System.out.print("\nInorder traversal:  ");
	    inOrder(root);
	    break;
	case 3:
	    System.out.print("\nPostorder traversal: ");
	    postOrder(root);
	    break;
	}
	System.out.println();
    }

//-------------------------------------------------------------
    private void preOrder(Node localRoot) {
	if (localRoot != null) {
	    System.out.print(localRoot.iData + " ");
	    preOrder(localRoot.leftChild);
	    preOrder(localRoot.rightChild);
	}
    }

//-------------------------------------------------------------
    private void inOrder(Node localRoot) {
	if (localRoot != null) {
	    inOrder(localRoot.leftChild);
	    System.out.print(localRoot.iData + " ");
	    inOrder(localRoot.rightChild);
	}
    }

//-------------------------------------------------------------
    private void postOrder(Node localRoot) {
	if (localRoot != null) {
	    postOrder(localRoot.leftChild);
	    postOrder(localRoot.rightChild);
	    System.out.print(localRoot.iData + " ");
	}
    }

//-------------------------------------------------------------
    public void displayTree() {
	Stack globalStack = new Stack();
	globalStack.push(root);
	int nBlanks = 32;
	boolean isRowEmpty = false;
	System.out.println("......................................................");
	while (isRowEmpty == false) {
	    Stack localStack = new Stack();
	    isRowEmpty = true;

	    for (int j = 0; j < nBlanks; j++)
		System.out.print(' ');

	    while (globalStack.isEmpty() == false) {
		Node temp = (Node) globalStack.pop();
		if (temp != null) {
		    System.out.print(temp.iData);
		    localStack.push(temp.leftChild);
		    localStack.push(temp.rightChild);

		    if (temp.leftChild != null || temp.rightChild != null)
			isRowEmpty = false;
		} else {
		    System.out.print("--");
		    localStack.push(null);
		    localStack.push(null);
		}
		for (int j = 0; j < nBlanks * 2 - 2; j++)
		    System.out.print(' ');
	    } // end while globalStack not empty
	    System.out.println();
	    nBlanks /= 2;
	    while (localStack.isEmpty() == false)
		globalStack.push(localStack.pop());
	} // end while isRowEmpty is false
	System.out.println("......................................................");
    } // end displayTree()
//-------------------------------------------------------------
} // end class Tree
////////////////////////////////////////////////////////////////

class TreeApp {
    public static void main(String[] args) throws IOException {
	int value;
	Tree theTree = new Tree();

	theTree.insert(50, 1.5);
	theTree.insert(25, 1.2);
	theTree.insert(75, 1.7);
	theTree.insert(12, 1.5);
	theTree.insert(37, 1.2);
	theTree.insert(43, 1.7);
	theTree.insert(30, 1.5);
	theTree.insert(33, 1.2);
	theTree.insert(87, 1.7);
	theTree.insert(93, 1.5);
	theTree.insert(97, 1.5);

	while (true) {
	    System.out.print("Enter first letter of show, ");
	    System.out.print("insert, find, delete, or traverse: ");
	    int choice = getChar();
	    switch (choice) {
	    case 's':
		theTree.displayTree();
		break;
	    case 'i':
		System.out.print("Enter value to insert: ");
		value = getInt();
		theTree.insert(value, value + 0.9);
		break;
	    case 'f':
		System.out.print("Enter value to find: ");
		value = getInt();
		Node found = theTree.find(value);
		if (found != null) {
		    System.out.print("Found: ");
		    found.displayNode();
		    System.out.print("\n");
		} else
		    System.out.print("Could not find ");
		System.out.print(value + '\n');
		break;
	    case 'd':
		System.out.print("Enter value to delete: ");
		value = getInt();
		boolean didDelete = theTree.delete(value);
		if (didDelete)
		    System.out.print("Deleted " + value + '\n');
		else
		    System.out.print("Could not delete ");
		System.out.print(value + '\n');
		break;
	    case 't':
		System.out.print("Enter type 1, 2 or 3: ");
		value = getInt();
		theTree.traverse(value);
		break;
	    default:
		System.out.print("Invalid entry\n");
	    } // end switch
	} // end while
    } // end main()
//-------------------------------------------------------------

    public static String getString() throws IOException {
	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);
	String s = br.readLine();
	return s;
    }

//-------------------------------------------------------------
    public static char getChar() throws IOException {
	String s = getString();
	return s.charAt(0);
    }

//-------------------------------------------------------------
    public static int getInt() throws IOException {
	String s = getString();
	return Integer.parseInt(s);
    }
//-------------------------------------------------------------
}

class BagChooser {

    private int arr[];
    private int tWeight;
    private int length;
    private int nStart = 0;
    private List<Integer> resultList = new ArrayList<Integer>();
    private int recursionLevel = 0;

    public BagChooser(int[] res, int weight) {
	super();
	this.arr = res;
	this.tWeight = weight;
	this.length = arr.length - 1;
    }

    public void recursionChooser(int index) throws InterruptedException {
	finder(0, index);
    }

    private void finder(int weight, int index) {
	int start = arr[index];
	int check = start + weight;
	if (check == tWeight) {
	    resultList.add(start);
	    sL("Result:\n\t\tWeight " + tWeight + " found in mix: " + resultList.toString() + ".\n\t\tCheck sum: "
		    + resultList.stream().mapToInt(v -> v).sum() + ".\n\t\tRecursion level: " + recursionLevel
		    + ".\n\t\tLast array index: " + index+".");
	    return;
	} else if (index >= length) {
	    if (nStart == length) {
		sL("Can't find weight: " + tWeight + ". Recursion level: " + recursionLevel+".");
		return;
	    }
	    resultList.clear();
	    recursionLevel++;
	    sL("Index: " + index + ". Weignt: " + weight + ". nStart: " + nStart + ". Check array index: " + arr[index]
		    + ". Recursion level: " + recursionLevel+".");
	    finder(0, ++nStart);
	} else if (check < tWeight) {
	    resultList.add(start);
	    recursionLevel++;
	    sL("Index: " + index + ". Weignt: " + weight + ". nStart: " + nStart + ". Check array index: " + arr[index]
		    + ". Recursion level: " + recursionLevel+".");
	    finder(check, ++index);
	} else if (check > tWeight) {
	    recursionLevel++;
	    sL("Index: " + index + ". Weignt: " + weight + ". nStart: " + nStart + ". Check array index: " + arr[index]
		    + ". Recursion level: " + recursionLevel+".");
	    finder(weight, ++index);
	}

    }

    private void sL(String msg) {
	System.err.print("Line: " + Thread.currentThread().getStackTrace()[2].getLineNumber() + " --> ");
	System.out.println(msg);
    }

}

class RecursionTriangle {

    public static int recursionTriangle(int number) {
	StackTraceElement[] st = Thread.currentThread().getStackTrace();
	System.out.println();
	for (StackTraceElement o : st) {
	    System.out.println(o);
	}
	if (number == 1) {
	    return 1;
	}
	return number + recursionTriangle(number - 1);
    }

}

class BinarySearch {
    private int[] arr = { 0, 2, 4, 5, 6, 8, 12, 22, 65, 235, 633, 1111, 2112, 3313, 12314, 144125, 1124146, 1124127,
	    1124124458, 612341319 };

    public void search(int number) {
	int result = 0;
	int low = 0;
	int max = arr.length - 1;
	while (true) {
	    int index = (low + max) / 2;
	    if (arr[index] == number) {
		result = index;
		break;
	    } else if (low > max) {
		System.out.println("Can't find number: " + number);
		return;
	    } else {
		if (arr[index] < number) {
		    low = index + 1;
		} else {
		    max = index - 1;
		}
	    }
	}
	System.out.println("Found in position: " + (result + 1) + ", number is: " + arr[result]);
    }

    public void recursionSearch(int k, int l, int u) {
	StackTraceElement[] el = Thread.currentThread().getStackTrace();
	for (StackTraceElement st : el) {
	    System.out.println(st);
	}
	System.out.println();
	int cur = (l + u) / 2;
	if (arr[cur] == k) {
	    System.out.println("Found in position: " + (cur + 1) + ", number is: " + arr[cur]);
	    return;
	} else if (l > u) {
	    System.out.println("Can't find number: " + k);
	    return;
	} else {
	    if (arr[cur] < k) {
		recursionSearch(k, cur + 1, u);
	    } else {
		recursionSearch(k, l, cur - 1);
	    }
	}
    }

}

class Cherck {
    int i;
    String t;
    Object o;

    @Override
    public String toString() {
	return this.getClass().getSimpleName() + ":\n\t" + i + "\n\t" + t + "\n\t" + o.getClass().getSimpleName()
		+ "\n";
    }
}

class ChanellTsest {
    private static final int bsize = 1024;
    StringBuilder sb = new StringBuilder();

    public ChanellTsest() throws Exception {

	/*
	 * BufferedReader br=new BufferedReader(new
	 * FileReader("C:\\Users\\"+System.getProperty("user.name")+"Desktop\\ee
	 * .txt")); while(br.ready()) { sb.append(br.readLine()); }
	 */

	FileChannel fc = new FileInputStream("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\dd.txt")
		.getChannel();
	ByteBuffer bf = ByteBuffer.allocate(bsize);
	fc.read(bf);
	bf.flip();
	while (bf.hasRemaining()) {
	    System.out.print((char) bf.get());
	}

	bf.rewind();

	String encoder = System.getProperty("file.encoding");
	sb.append("\n" + encoder + "\n" + Charset.forName(encoder).decode(bf));

	bf.rewind();

	fc = new RandomAccessFile("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop\\ee.txt", "rw")
		.getChannel();
	fc.position(fc.size() / 2);
	fc.write(ByteBuffer.wrap(sb.toString().getBytes()));

	/*
	 * DataOutputStream dos=new DataOutputStream(new
	 * FileOutputStream("C:\\Users\\"+System.getProperty("user.name")+"\\
	 * Desktop\\dd.txt")); dos.write(sb.toString().getBytes()); dos.close();
	 */
	fc.close();
    }

}

class ThreadA extends Thread {

    static volatile int q = 0;
    static final Object o = new Object();

    @Override
    public void run() {
	for (int i = 0; i < 1_000_000; i++) {
	    synchronized (o) {
		q++;
	    }
	}
    }
}

class TestThreadA {
    public TestThreadA() throws Exception {
	System.out.println("Start multithread part of test...");
	long result = 0;
	long time = System.nanoTime();
	for (int i = 0; i < 10; i++) {
	    new ThreadA().start();
	}
	Thread.sleep(4000);
	System.out.println(
		"q = " + ThreadA.q + ", time = " + (((double) (System.nanoTime() - time) / 1_000_000_000) - 4d));
	System.out.println("Start singlethread part of test...");
	long time2 = System.nanoTime();
	for (int i = 0; i < ThreadA.q; i++) {
	    result++;
	}
	System.out.println("r = " + result + ", time = " + (double) (System.nanoTime() - time2) / 1_000_000_000);
    }
}

enum Podor {
    HEHE, HAHA;

    public String toString() {
	String id = name();
	String low = id.substring(1).toLowerCase();
	return id.charAt(0) + low;
    }
}

interface A1 {
}

interface A2 {
}

class A3 implements A1, A2 {
    public <A1> void meth1() {
	System.out.println("meth1");
    }

    public <A2> void meth2() {
	System.out.println("meth2");
    }

    public <T> void meth5(T t) {
	System.out.println(t.getClass().getName());
    }
}

interface CheChe {
    Che create(int q);

}

class Che {
    private int q;

    Che(int q) {
	this.q = q;
    }

    void show() {
	System.out.println(q);
    }
}

class Qf<T> {
    private T[] arr;

    public Qf(int s) {
	arr = (T[]) new Object[s];
    }

    public T get(int i) {
	return arr[i];
    }

    public void add(int s, T t) {
	arr[s] = t;
    }

    public T[] arrget() {
	return arr;
    }
}

class Take extends JPanel {

    private static final int SCALEFACTOR = 200;
    private int cycles, points;
    private double[] sines;
    private int[] pts;

    public Take() {
	setCycles(5);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	int maxWidth = getWidth();
	int maxHeight = getHeight();
	double hstep = (double) maxWidth / (double) points;
	pts = new int[points];
	for (int i = 0; i < points; i++) {
	    pts[i] = (int) (sines[i] * maxHeight / 2 * .95 + maxWidth / 2);
	}
	g.setColor(Color.RED);
	for (int i = 1; i < points; i++) {
	    int x1 = (int) ((i - 1) * hstep);
	    int x2 = (int) (i * hstep);
	    int y1 = pts[i - 1];
	    int y2 = pts[i];
	    g.drawLine(x1, y1, x2, y2);
	}

    }

    void setCycles(int newCycles) {
	cycles = newCycles;
	points = SCALEFACTOR * cycles * 2;
	sines = new double[points];
	for (int i = 0; i < points; i++) {
	    double rad = (Math.PI / SCALEFACTOR) * i;
	    sines[i] = Math.sin(rad);
	}
	repaint();
    }

}

class Add extends JFrame {
    JColorChooser j = new JColorChooser();
    JProgressBar sb = new JProgressBar();
    ProgressMonitor pm;
    JButton b = new JButton();
    JLabel lab = new JLabel("HAHA", JLabel.CENTER);
    JButton exit = new JButton("<html><b><i><font size=-2>Exit baton</i></b></html>");
    final Object o = new Object();
    JSlider js = new JSlider();
    volatile boolean x = false;
    int state = 0;
    Color sqe;
    {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}
    }

    public Add() {
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize(500, 200);
	setLocationRelativeTo(null);
	setLayout(new GridLayout(4, 1));
	b.setText("Baton obichnii");
	b.setSize(100, 50);
	sb.setStringPainted(true);
	exit.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	b.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Container cc = getContentPane();
		sqe = JColorChooser.showDialog(cc, "1", lab.getBackground());
		x = true;
		// setVisible(false);
		progress();
		change();
	    }
	});
	b.setFocusable(false);
	exit.setFocusable(false);
	add(sb);
	add(exit);
	add(b);
	add(lab);
	/*
	 * try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
	 * catch (Exception e) {}
	 */
	setVisible(true);

    }

    void progress() {
	if (x) {
	    new Thread(() -> {
		pm = new ProgressMonitor(this, "Monotor", "test", 0, 100);
		synchronized (o) {
		    for (int i = 0; i < 101; i++) {
			if (pm.isCanceled()) {
			    o.notifyAll();
			    JOptionPane.showMessageDialog(this, "Operation been canceled", "Error",
				    JOptionPane.ERROR_MESSAGE);
			    break;
			}
			Color c = new Color(i);
			pm.setProgress(i);
			sb.setValue(i);
			sb.setForeground(c);
			try {
			    Thread.sleep((int) (Math.random() * 100));
			} catch (InterruptedException esa) {
			}
		    }
		    x = false;
		    o.notifyAll();
		}
		// setVisible(true);
	    }).start();
	}
    }

    void change() {
	new Thread(() -> {
	    synchronized (o) {
		while (x) {
		    try {
			o.wait();
		    } catch (Exception e1) {

		    }
		}
		if (!pm.isCanceled()) {
		    lab.setForeground(sqe);
		    exit.setBackground(sqe);
		    lab.setText(String.format("<html><font size=+2>%s", sqe.toString()));
		    try {
			Thread.sleep(3000);
		    } catch (InterruptedException e) {

		    }
		    lab.setText(Double.toString(Math.random()));
		}
	    }
	}).start();
    }

}

class DD {
    public <T> void viod(T x, T y, T z) {
	System.out.println(x.getClass().getName() + " " + y.getClass().getName() + " " + z.getClass().getName());
    }
}

class Rome {

    /*
     * public static void main(String[] args) throws IOException { BufferedReader
     * bufferedReader = new BufferedReader(new InputStreamReader(System.in));
     * System.out.println("Input a roman number to be converted to decimal: ");
     * String romanString = bufferedReader.readLine();
     * System.out.println("Conversion result equals " +
     * romanToInteger(romanString)); }
     */

    public static int romanToInteger(String s) {
	int result = 0;
	s = s.trim().toUpperCase();
	String[] temp = s.split("");
	for (int i = 0; i < temp.length; i++) {
	    // System.err.println(i);
	    String c = temp[i];
	    if (i < temp.length - 1) {
		if (c.equals("I") && temp[i + 1].equals("V")) {
		    c = "IV";
		    i++;// 4
		}

		if (c.equals("I") && temp[i + 1].equals("X")) {
		    c = "IX";
		    i++;// 9
		}
		if (c.equals("X") && temp[i + 1].equals("L")) {
		    c = "XL";
		    i++;// 40
		}
		if (c.equals("X") && temp[i + 1].equals("C")) {
		    c = "XC";
		    i++;// 90
		}
		if (c.equals("C") && temp[i + 1].equals("D")) {
		    c = "CD";
		    i++;// 400
		}
		if (c.equals("C") && temp[i + 1].equals("M")) {
		    c = "CM";
		    i++;// 900
		}
	    }

	    switch (c) {
	    case "I": {
		result += 1;
		break;
	    }
	    case "IV": {
		result += 4;
		break;
	    }
	    case "V": {
		result += 5;
		break;
	    }
	    case "IX": {
		result += 9;
		break;
	    }
	    case "X": {
		result += 10;
		break;
	    }
	    case "XL": {
		result += 40;
		break;
	    }
	    case "L": {
		result += 50;
		break;
	    }
	    case "XC": {
		result += 90;
		break;
	    }
	    case "C": {
		result += 100;
		break;
	    }
	    case "CD": {
		result += 400;
		break;
	    }
	    case "D": {
		result += 500;
		break;
	    }
	    case "CM": {
		result += 900;
		break;
	    }
	    case "M": {
		result += 1000;
		break;
	    }
	    default:
		throw new IllegalArgumentException("Unexpected value: " + c);
	    }
	}
	return result;
    }
}

class ASFASF {

}
