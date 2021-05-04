package ImageConvertor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageLoader { 
    
    Path path=null;
    
    public BufferedImage loadImage() {	
	
	JFileChooser jFileChooser = new JFileChooser();
	
	FileNameExtensionFilter images = new FileNameExtensionFilter("images", "png", "jpeg", "bmp", "jpg");
	jFileChooser.setFileFilter(images);

	jFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "\\Desktop\\"));
	jFileChooser.showDialog(null, "Select image");
	jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

	if (jFileChooser.getSelectedFile() == null) {
	    JOptionPane.showMessageDialog(null, "You not choose the file.", "Error", JOptionPane.INFORMATION_MESSAGE);
	    return null;
	} else {
	    path = Paths.get(jFileChooser.getSelectedFile().getAbsolutePath());   
	}
	
	BufferedImage bufferedImage = null;
	try {
	    bufferedImage = ImageIO.read(path.toFile());
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ImageIO.write(bufferedImage, "jpg", bos);
	   // ColorConvertOp colorConvertOp = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
	   // colorConvertOp.filter(bufferedImage, bufferedImage);
	} catch (Exception e) {
	    //e.printStackTrace();
	    String text=jFileChooser.getSelectedFile().getName();
	    String result=text.substring(text.lastIndexOf("."));
	    JOptionPane.showMessageDialog(jFileChooser, "Unsuported file format "+result, result +" is not support.", JOptionPane.ERROR_MESSAGE);
	    return null;
	}	
	return bufferedImage;
    }  
    
    public Path getPath() {
	return path;
    }
    
}
