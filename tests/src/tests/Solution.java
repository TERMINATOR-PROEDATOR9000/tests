package tests;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
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

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.basic.BasicArrowButton;

import java.lang.reflect.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class Solution {

	public static void main(String... args) throws Exception {
		/*
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (Exception e) {}
		 */
		// hehe
		Cherck a = new Cherck();
		a.i = 1;
		a.t = "1";
		a.o = new DD();
		Cherck b = new Cherck();
		b.i = 2;
		b.t = "2";
		b.o = new Rome();
		System.out.println(a);
		System.out.println(b);
	}

}

class Cherck {
	int i;
	String t;
	Object o;
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+":\n\t"+i+"\n\t"+t+"\n\t"+o.getClass().getSimpleName()+"\n";
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

		FileChannel fc = new FileInputStream("C:\\Users\\"
				+ System.getProperty("user.name") + "\\Desktop\\dd.txt")
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

		fc = new RandomAccessFile("C:\\Users\\"
				+ System.getProperty("user.name") + "\\Desktop\\ee.txt", "rw")
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
		System.out.println("q = " + ThreadA.q + ", time = "
				+ (((double) (System.nanoTime() - time) / 1_000_000_000) - 4d));
		System.out.println("Start singlethread part of test...");
		long time2 = System.nanoTime();
		for (int i = 0; i < ThreadA.q; i++) {
			result++;
		}
		System.out.println("r = " + result + ", time = "
				+ (double) (System.nanoTime() - time2) / 1_000_000_000);
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
	JButton exit = new JButton(
			"<html><b><i><font size=-2>Exit baton</i></b></html>");
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
		 * try {
		 * UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 * } catch (Exception e) {}
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
							JOptionPane.showMessageDialog(this,
									"Operation been canceled", "Error",
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
					lab.setText(String.format("<html><font size=+2>%s",
							sqe.toString()));
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
		System.out.println(x.getClass().getName() + " " + y.getClass().getName()
				+ " " + z.getClass().getName());
	}
}

class Rome {

	/*
	 * public static void main(String[] args) throws IOException {
	 * BufferedReader bufferedReader = new BufferedReader(new
	 * InputStreamReader(System.in));
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
				case "I" : {
					result += 1;
					break;
				}
				case "IV" : {
					result += 4;
					break;
				}
				case "V" : {
					result += 5;
					break;
				}
				case "IX" : {
					result += 9;
					break;
				}
				case "X" : {
					result += 10;
					break;
				}
				case "XL" : {
					result += 40;
					break;
				}
				case "L" : {
					result += 50;
					break;
				}
				case "XC" : {
					result += 90;
					break;
				}
				case "C" : {
					result += 100;
					break;
				}
				case "CD" : {
					result += 400;
					break;
				}
				case "D" : {
					result += 500;
					break;
				}
				case "CM" : {
					result += 900;
					break;
				}
				case "M" : {
					result += 1000;
					break;
				}
				default :
					throw new IllegalArgumentException(
							"Unexpected value: " + c);
			}
		}
		return result;
	}
}

class ASFASF {

}
