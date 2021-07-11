package ImageConvertor;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings(value = "serial")
class View extends JFrame {

    public final static Path HOME = Paths.get(System.getProperty("user.home") + "\\Desktop\\");
    int size;
    Controller controller;
    double workTime;

    public View() {
	super();
	init();
	setSize(600, 335);
	setResizable(false);
	// setLayout(null);
	setTitle("ImageConvertor v5");
	setLocationRelativeTo(null);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
    }

    private void init() {	

	JPanel mainContainer = new JPanel();
	mainContainer.setLayout(new GridLayout(1, 2));		

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
	JComponent figEditorCh = chunkSpinner.getEditor();
	JSpinner.DefaultEditor chunkSpinnerEditor = (JSpinner.DefaultEditor) figEditorCh;
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
	JSpinner jStrokeFactorSpinner = new JSpinner(new SpinnerNumberModel(1f, 0.0f, 5f, 0.5f));
	jStrokeFactorSpinner.setBorder(null);
	JComponent jStrokeFactorSpinnerComp = jStrokeFactorSpinner.getEditor();
	JSpinner.DefaultEditor strokeSpinnerEditor = (JSpinner.DefaultEditor) jStrokeFactorSpinnerComp;
	strokeSpinnerEditor.getTextField().setEditable(false);
	strokeSpinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	strokeFactor.add(jStrokeFactor);
	strokeFactor.add(jStrokeFactorSpinner);

	/*JPanel lumines = new JPanel();
	lumines.setLayout(new GridLayout(1, 2));
	JTextField jLumines = new JTextField("Lumines:");
	jLumines.setToolTipText("Maximum: 1");
	jLumines.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	jLumines.setFocusable(false);
	jLumines.setEditable(false);
	jLumines.setBorder(null);

	JSpinner minLum = new JSpinner(new SpinnerNumberModel(0, 0, 1f, 0.01f));
	minLum.setToolTipText("Minimal luminal pixel");
	minLum.setBorder(null);
	JComponent jLumFactorSpinner = minLum.getEditor();
	JSpinner.DefaultEditor lumSpinnerEditor = (JSpinner.DefaultEditor) jLumFactorSpinner;
	lumSpinnerEditor.getTextField().setEditable(false);
	lumSpinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	lumines.add(jLumines);
	lumines.add(minLum);
	
	JSpinner maxLum = new JSpinner(new SpinnerNumberModel(0.2, 0, 1f, 0.01f));
	maxLum.setBorder(null);
	maxLum.setToolTipText("Maximal luminal pixel");
	JComponent maxLumEditor = maxLum.getEditor();
	JSpinner.DefaultEditor maxLumSpinerEditor = (JSpinner.DefaultEditor) maxLumEditor;
	maxLumSpinerEditor.getTextField().setEditable(false);
	maxLumSpinerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);	
	
	JPanel lumPanel=new JPanel();
	lumPanel.setLayout(new GridLayout(1,2));
	lumPanel.add(minLum);
	lumPanel.add(maxLum);
	lumines.add(jLumines);
	lumines.add(lumPanel);*/
	
	JPanel layers=new JPanel();
	layers.setLayout(new GridLayout(1,2));
	
	JTextField layersText=new JTextField("Layers:");
	layersText.setToolTipText("Layers count");
	layersText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	layersText.setFocusable(false);
	layersText.setEditable(false);
	layersText.setBorder(null);
	
	JSpinner layerSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 30, 1));
	layerSpinner.setToolTipText("Minimal luminal pixel");
	layerSpinner.setBorder(null);
	JComponent layerSpinnerEditor = layerSpinner.getEditor();
	JSpinner.DefaultEditor lumSpinnerEditor = (JSpinner.DefaultEditor) layerSpinnerEditor;
	lumSpinnerEditor.getTextField().setEditable(false);
	lumSpinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	layers.add(layersText);
	layers.add(layerSpinner);

	JPanel chooseFigure = new JPanel(new GridLayout(1, 2));
	JTextField textFigure = new JTextField("Figure: ");
	textFigure.setToolTipText("The figure will be use for draw image");
	textFigure.setEditable(false);
	textFigure.setFocusable(false);
	textFigure.setBorder(null);
	textFigure.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	String[] arr = { "Line", "Circle", "X" };
	JSpinner figSpinner = new JSpinner(new SpinnerListModel(arr));
	figSpinner.setBorder(null);
	JComponent figEditor = figSpinner.getEditor();
	JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) figEditor;
	spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
	spinnerEditor.getTextField().setEditable(false);
	chooseFigure.add(textFigure);
	chooseFigure.add(figSpinner);

	JPanel parserChoser = new JPanel(new GridLayout(1, 2));
	JTextField parserChoserText = new JTextField("Parser: ");
	parserChoserText.setToolTipText("Singlethread or muntithread parser");
	parserChoserText.setEditable(false);
	parserChoserText.setFocusable(false);
	parserChoserText.setBorder(null);
	parserChoserText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	JPanel radButPanel = new JPanel();
	JRadioButton single = new JRadioButton("Single");
	single.setFocusable(false);
	single.setToolTipText("Stable. Usually working faster and less system load.");
	JRadioButton multi = new JRadioButton("Multi");
	multi.setFocusable(false);
	multi.setEnabled(false);
	multi.setToolTipText("VERY unstable. Working randomly with high system load.");
	single.setSelected(true);
	single.addActionListener(e -> {
	    multi.setSelected(false);
	    single.setSelected(true);
	});
	multi.addActionListener(e -> {
	    single.setSelected(false);
	    multi.setSelected(true);
	});
	radButPanel.add(single);
	radButPanel.add(multi);
	parserChoser.add(parserChoserText);
	parserChoser.add(radButPanel);
	
	JPanel rndChooser = new JPanel(new GridLayout(1, 2));
	JTextField rndChooserText = new JTextField("Random for draw:");
	rndChooserText.setToolTipText("Random for pixels");
	rndChooserText.setEditable(false);
	rndChooserText.setFocusable(false);
	rndChooserText.setBorder(null);
	rndChooserText.setHorizontalAlignment((int) CENTER_ALIGNMENT);
	JPanel rndChooserPan = new JPanel();	
	JCheckBox randChoose=new JCheckBox("Use");
	randChoose.setFocusable(false);
	randChoose.setSelected(true);
	randChoose.setToolTipText("Use random for draw image.");
	
	rndChooserPan.add(randChoose);
	rndChooser.add(rndChooserText);
	rndChooser.add(rndChooserPan);

	JButton processImage = new JButton("process image");
	JButton imageChooser = new JButton("choose image");
	JButton saveImage = new JButton("save image");

	imageChooser.setFocusable(false);
	imageChooser.addActionListener(e -> {
	    setTitle("ImageConvertor");
	    controller = new Controller();
	    controller.loadImage();
	    if (controller.isLoaded()) {
		processImage.setEnabled(true);
		JPanel temp = new PreView(controller);
		rightContainer.add(temp);
		CardLayout cl = (CardLayout) rightContainer.getLayout();
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
	   /* float lumfactorMin = Float.valueOf(minLum.getValue().toString());
	    float lumfactorMax = Float.valueOf(maxLum.getValue().toString());
	    if(lumfactorMax<=lumfactorMin) {
		JOptionPane.showMessageDialog(this, "Min lum cant be greater than min lum!", "Error", JOptionPane.ERROR_MESSAGE);		
		return;
	    }
	    controller.setMaxLum(lumfactorMax);
	    controller.setMinLum(lumfactorMin);*/
	    controller.setLayers(Integer.valueOf(layerSpinner.getValue().toString()));
	    if (single.isSelected()) {
		controller.setSingle(true);
	    } else {
		controller.setSingle(false);
	    }
	    controller.setChunkSize((short) chunk);
	    controller.setStroke(stroke);
	    controller.setFigure(figSpinner.getValue().toString());
	    controller.setRandom(randChoose.isSelected());
	    if (single.isSelected()) {
		setTitle("Working with singlethread parser");
	    } else {
		setTitle("Working with multithread parser");
	    }
	    double time = System.currentTimeMillis();
	    controller.showImage();
	    workTime = (System.currentTimeMillis() - time) / 1000d;
	    setTitle("Previrw image. Work done for " + workTime + " seconds.");
	    saveImage.setEnabled(true);
	});
	processImage.setFocusable(false);

	saveImage.setEnabled(false);
	saveImage.setFocusable(false);
	saveImage.addActionListener(e -> {
	    controller.saveImage();
	    setTitle("Saved. Work done for " + workTime + " seconds.");
	});	
	
	List<JComponent> components =new ArrayList<JComponent>();
	
	components.add(chunkSize);
	components.add(strokeFactor);
	//compontnes.add(lumines);
	components.add(layers);
	components.add(chooseFigure);
	components.add(parserChoser);
	components.add(rndChooser);
	components.add(imageChooser);
	components.add(processImage);
	components.add(saveImage);
	
	JPanel leftContainer = new JPanel();
	leftContainer.setSize(300, 300);	
	leftContainer.setLayout(new GridLayout(components.size(), 1));
	for(JComponent j:components) {
	   leftContainer.add(j);
	}
	
	/*leftContainer.add(chunkSize);
	leftContainer.add(strokeFactor);
	leftContainer.add(lumines);
	leftContainer.add(chooseFigure);
	leftContainer.add(parserChoser);
	leftContainer.add(rndChooser);

	leftContainer.add(imageChooser);
	leftContainer.add(processImage);
	leftContainer.add(saveImage);*/

	mainContainer.add(leftContainer);
	mainContainer.add(rightContainer);

	this.add(mainContainer);
    }

    public int getChungSize() {
	return size;
    }

}
