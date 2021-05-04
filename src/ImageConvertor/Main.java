package ImageConvertor;

import javax.swing.UIManager;

public class Main {
    public static void main(String... args) throws Exception {
	try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (Exception e) {
	}		
	new View();	 
    }
}


