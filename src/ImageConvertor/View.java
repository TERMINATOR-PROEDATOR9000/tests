package ImageConvertor;

import java.awt.CardLayout;

import java.awt.GridLayout;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings(value = "serial")
class View extends JFrame {

    public final static Path HOME = Paths.get(System.getProperty("user.home") + "\\Desktop\\");
    int size;
    Controller controller;

    public View() {
	super();
	init();
	setSize(600, 300);
	setResizable(false);
	//setLayout(null);
	setTitle("ImageConvertor");
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }

    private void init() {

	JPanel mainContainer = new JPanel();
	mainContainer.setLayout(new GridLayout(1,2));

	JPanel leftContainer = new JPanel();
	leftContainer.setSize(300, 300);
	leftContainer.setLayout(new GridLayout(7, 1));
	
	JPanel rightContainer = new JPanel();
	rightContainer.setLayout(new CardLayout());
	rightContainer.setSize(300, 300);
	
	rightContainer.add(new StubImage());	

	JPanel chunkSize = new JPanel();
	chunkSize.setLayout(new GridLayout(1, 2));

	JTextField jChunkSize = new JTextField();	
	jChunkSize.setText("Chunk size:");
	jChunkSize.setToolTipText("Maximum: 25");
	jChunkSize.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	jChunkSize.setFocusable(false);
	jChunkSize.setBorder(null);
	jChunkSize.setEditable(false);	

	JSpinner chunkSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 25, 1));	
	JComponent figEditorCh=chunkSpinner.getEditor();	
	JSpinner.DefaultEditor chunkSpinnerEditor = (JSpinner.DefaultEditor)figEditorCh;
	chunkSpinner.setBorder(null);	
	chunkSpinnerEditor.getTextField().setEditable(false);
	chunkSpinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);	

	chunkSize.add(jChunkSize);
	chunkSize.add(chunkSpinner);

	JPanel strokeFactor = new JPanel();
	strokeFactor.setLayout(new GridLayout(1, 2));
	JTextField jStrokeFactor = new JTextField("Stroke factor:");
	jStrokeFactor.setToolTipText("Maximum: 5");
	jStrokeFactor.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	jStrokeFactor.setFocusable(false);
	jStrokeFactor.setBorder(null);
	jStrokeFactor.setEditable(false);
	JSpinner jStrokeFactorSpinner = new JSpinner(new SpinnerNumberModel(1f, 0.1f, 5f, 0.1f));
	jStrokeFactorSpinner.setBorder(null);
	JComponent jStrokeFactorSpinnerComp=jStrokeFactorSpinner.getEditor();
	JSpinner.DefaultEditor strokeSpinnerEditor = (JSpinner.DefaultEditor)jStrokeFactorSpinnerComp;
	strokeSpinnerEditor.getTextField().setEditable(false);
	strokeSpinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	strokeFactor.add(jStrokeFactor);
	strokeFactor.add(jStrokeFactorSpinner);

	JPanel lumines = new JPanel();
	lumines.setLayout(new GridLayout(1, 2));
	JTextField jLumines = new JTextField("Lumines:");
	jLumines.setToolTipText("Maximum: 4");
	jLumines.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	jLumines.setFocusable(false);
	jLumines.setEditable(false);
	jLumines.setBorder(null);

	JSpinner jluminesSpiner = new JSpinner(new SpinnerNumberModel(0.2, 0, 4f, 0.01f));
	jluminesSpiner.setBorder(null);
	JComponent jLumFactorSpinner=jluminesSpiner.getEditor();
	JSpinner.DefaultEditor lumSpinnerEditor = (JSpinner.DefaultEditor)jLumFactorSpinner;
	lumSpinnerEditor.getTextField().setEditable(false);
	lumSpinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	lumines.add(jLumines);
	lumines.add(jluminesSpiner);
	
	JPanel chooseFigure=new JPanel(new GridLayout(1,2));	
	JTextField textFigure=new JTextField("Figure: ");
	textFigure.setToolTipText("The figure will be use for draw image");
	textFigure.setEditable(false);
	textFigure.setFocusable(false);
	textFigure.setBorder(null);
	textFigure.setHorizontalAlignment((int) CENTER_ALIGNMENT);	
	String [] arr= {"Line","Circle", "X"};		
	JSpinner figSpinner=new JSpinner(new SpinnerListModel(arr));
	figSpinner.setBorder(null);
	JComponent figEditor=figSpinner.getEditor();
	JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)figEditor;
	spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	spinnerEditor.getTextField().setEditable(false);	
	chooseFigure.add(textFigure);
	chooseFigure.add(figSpinner);

	JButton processImage = new JButton("process image");
	JButton imageChooser = new JButton("choose image");
	JButton saveImage = new JButton("save image");

	imageChooser.setFocusable(false);
	imageChooser.addActionListener(e -> {
	    setTitle("ImageConvertor");
	    int chunk = Integer.valueOf(chunkSpinner.getValue().toString());
	    float stroke = Float.valueOf(jStrokeFactorSpinner.getValue().toString());
	    float lumfactor = Float.valueOf(jluminesSpiner.getValue().toString());
	    controller = new Controller();
	    controller.setChunkSize(chunk);
	    controller.setStroke(stroke);
	    controller.setLumfactor(lumfactor);
	    controller.setFigure(figSpinner.getValue().toString());
	    controller.loadImage();	    
	    if (controller.isLoaded()) {
		processImage.setEnabled(true);		
		JPanel temp=new PreView(controller);		
		rightContainer.add(temp);
		CardLayout cl=(CardLayout)rightContainer.getLayout();
		cl.show(rightContainer, "");
		validate();
	    } else {
		processImage.setEnabled(false);
	    }
	});	

	processImage.setFocusable(false);
	processImage.setEnabled(false);
	processImage.addActionListener(e -> {
	    int chunk = Integer.valueOf(chunkSpinner.getValue().toString());
	    float stroke = Float.valueOf(jStrokeFactorSpinner.getValue().toString());
	    float lumfactor = Float.valueOf(jluminesSpiner.getValue().toString());
	    controller.setChunkSize(chunk);
	    controller.setStroke(stroke);
	    controller.setLumfactor(lumfactor);
	    controller.setFigure(figSpinner.getValue().toString());
	    setTitle("working...");
	    controller.showImage();	    
	    setTitle("previrw image");
	    saveImage.setEnabled(true);
	});
	processImage.setFocusable(false);

	saveImage.setEnabled(false);
	saveImage.setFocusable(false);
	saveImage.addActionListener(e -> {
	    controller.saveImage();
	    setTitle("saved");
	   // processImage.setEnabled(false);
	   // saveImage.setEnabled(false);
	});	
	

	leftContainer.add(chunkSize);
	leftContainer.add(strokeFactor);
	leftContainer.add(lumines);
	leftContainer.add(chooseFigure);

	leftContainer.add(imageChooser);	
	leftContainer.add(processImage);
	leftContainer.add(saveImage);	

	mainContainer.add(leftContainer);
	mainContainer.add(rightContainer);	

	this.add(mainContainer);
    }

    public int getChungSize() {
	return size;
    }   

}
