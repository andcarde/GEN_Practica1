package graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.math.plot.Plot2DPanel;

import control.Client;
import control.Controller;
import control.InvalidInputException;
import control.Request;
import control.RequestMaker;
import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;

public class Window extends JFrame implements RequestMaker, Client {
	
	private static final double DEFAULT_CROSSOVER_RATE = 60;
	private static final double DEFAULT_MUTATION_RATE = 5;
	private static final int DEFAULT_POPULATION_AMOUNT = 100;
	private static final int DEFAULT_GENERATION_AMOUNT = 100;
	private static final double DEFAULT_ELITISM_RATE = 0;
	private static final CrossoverMethod DEFAULT_CROSSOVER_METHOD = CrossoverMethod.CrossoverTree;
	private static final MutationMethod DEFAULT_MUTATION_METHOD = MutationMethod.TERMINAL;

	
	private static final long serialVersionUID = 8815627840243675666L;
	
	private static final int OUTER_LEFT_MARGIN = 22;
	private static final int LEFT_MARGIN = 12;
	private static final int LABEL_HEIGHT = 20;
	private static final int LABEL_WIDTH = 150;
	private static final int TEXT_FIELD_HIGH_SIZE = 20;
	private static final int TEXT_FIELD_WIDTH = 150;
	private static final int SPINNER_HIGH_SIZE = 20;
	private static final int SPINNER_WIDTH = 80;
	private static final int COMBO_BOX_HEIGHT = 20;
	private static final int COMBO_BOX_WIDTH = 200;
	private static final int MAX_WIDTH = OBTAIN_MAX_WIDTH();
	private static final int PANEL_WIDTH = MAX_WIDTH + 2 * LEFT_MARGIN;
	private static final int BORDER_THICKNESS = 1;
	private static final int VERTICAL_MARGIN = 5;
	private static final int BIG_VERTICAL_MARGIN = 10;
	private static final int SMALL_VERTICAL_MARGIN = 1;
	
	private static int OBTAIN_MAX_WIDTH() {
		int[] widthArray = {LABEL_WIDTH, TEXT_FIELD_WIDTH, SPINNER_WIDTH, COMBO_BOX_WIDTH};
		Arrays.sort(widthArray);
		return widthArray[widthArray.length - 1];
	}
	
	class MyPanel extends JPanel {
		
		private static final long serialVersionUID = -236885179794726316L;
		private Integer innerLeftMargin;
		private Integer height;
		
		private MyPanel(LayoutManager layoutManager, Integer innerLeftMargin) {
			super(layoutManager);
			this.innerLeftMargin = innerLeftMargin;
			this.height = 0;
		}
		void addHeight(Integer toAdd) {
			this.height += toAdd;
		}
		Integer getMyHeight() {
			return this.height;
		}
		Integer getInnerLeftMargin() {
			return this.innerLeftMargin;
		}
	}
	
	private MyPanel contentPane;
	private JTextField populationAmountTF, generationAmountTF;
	private JSpinner crossoverRateSpinner, mutationRateSpinner, elitismRateSpinner, truncationSpinner;
	private JComboBox<String> functionCB;
	
	private MyPanel tournamentPanel, probabilisticTournamentPanel, function4Panel;
	
	private MyPanel methodPanel;
	private JComboBox<String> crossCB, selectionCB, mutationCB;
	
	private double[] gens;
	private Plot2DPanel plot, plotP3;
	private JTextArea textValue;
	private int labelTruncPos;

	/**
	 * Create the frame.
	 */
	public Window() {
		setTitle("Genetic Algoritm Runner [by Group 13] <Practice 03>");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 703);
		contentPane = new MyPanel(null, Window.OUTER_LEFT_MARGIN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.addHeight(BIG_VERTICAL_MARGIN);
		initSettings(contentPane);
		contentPane.addHeight(BIG_VERTICAL_MARGIN);
		initMethodPanel(contentPane);
		initGraphicPanel();
		initResultTextArea();
		// initScrollPane();
		initStartButton();
	}
	
	private JLabel createLabel(String name, MyPanel panel) {
		JLabel label = new JLabel(name);
		label.setBounds(panel.getInnerLeftMargin(), panel.getMyHeight(),
				Window.LABEL_WIDTH, Window.LABEL_HEIGHT);
		panel.addHeight(Window.LABEL_HEIGHT);
		panel.add(label);
		return label;
	}
	
	private JTextField createTextField(String initialValue, MyPanel panel) {
		JTextField textField = new JTextField(initialValue);
		textField.setBounds(panel.getInnerLeftMargin(), panel.getMyHeight(),
				Window.TEXT_FIELD_WIDTH, Window.TEXT_FIELD_HIGH_SIZE);
		panel.addHeight(Window.TEXT_FIELD_HIGH_SIZE);
		panel.add(textField);
		return textField;
	}
	
	private JSpinner createSpinner(Object initialValue, MyPanel panel) {
		JSpinner spinner = new JSpinner();
		spinner.setBounds(panel.innerLeftMargin, panel.getMyHeight(),
				SPINNER_WIDTH, SPINNER_HIGH_SIZE);
		panel.addHeight(SPINNER_HIGH_SIZE);
		spinner.setValue(initialValue);
		panel.add(spinner);
		return spinner;
	}
	
	private <T extends Enum<T>> JComboBox<String> createComboBox(Class<T> enumType, MyPanel panel) {
		JComboBox<String> comboBox = new JComboBox<>();
		for (T item : enumType.getEnumConstants())
			comboBox.addItem(item.name());
		comboBox.setBounds(panel.getInnerLeftMargin(), panel.getMyHeight(),
				COMBO_BOX_WIDTH, COMBO_BOX_HEIGHT);
		panel.addHeight(COMBO_BOX_HEIGHT);
		panel.add(comboBox);
		return comboBox;
	}
	

	public void initSettings(MyPanel superPanel) {
		createLabel("Population Amount", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		populationAmountTF = createTextField(String.valueOf(Window.DEFAULT_POPULATION_AMOUNT), superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Generation Amount", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		generationAmountTF = createTextField(String.valueOf(Window.DEFAULT_GENERATION_AMOUNT), superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Crossover Rate (%)", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		crossoverRateSpinner = createSpinner(Window.DEFAULT_CROSSOVER_RATE, superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Mutation Rate (%)", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		mutationRateSpinner = createSpinner(Window.DEFAULT_MUTATION_RATE, superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Elitism Rate (%)", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		elitismRateSpinner = createSpinner(Window.DEFAULT_ELITISM_RATE, superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Function", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		functionCB = createComboBox(FitnessFunction.class, superPanel);
		functionCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	if (e.getItem().equals(FitnessFunction.FUNCTION4a.name()) || e.getItem().equals(FitnessFunction.FUNCTION4b.name()))
            		function4Panel.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            }
        });
		functionCB.setSelectedItem(FitnessFunction.CITIES.toString());
		
		
	}
	
	private void initGraphicPanel() {
		JTabbedPane generalPanel = new JTabbedPane();
		generalPanel.setBackground(new Color(222, 221, 218));
		generalPanel.setBounds(287, 41, 835, 450);
		contentPane.add(generalPanel);
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		generalPanel.addTab("Progresion", plot);
		plot.setBounds(228, 12, 430, 315);
		plot.setPreferredSize(new Dimension(835, 450));
		plot.setVisible(false);
		plotP3 = new Plot2DPanel();
		plot.addLegend("SOUTH");
		generalPanel.addTab("Resultado Final", plotP3);
		plotP3.setBounds(228, 12, 430, 315);
		plotP3.setPreferredSize(new Dimension(835, 450));
		plotP3.setVisible(false);
	}
	
	private void initMethodPanel(MyPanel superPanel) {
		methodPanel = new MyPanel(null, Window.LEFT_MARGIN);
		methodPanel.setBorder(new LineBorder(Color.BLACK, Window.BORDER_THICKNESS, true));
		methodPanel.addHeight(VERTICAL_MARGIN);
		methodPanel.setLocation(superPanel.getInnerLeftMargin(), superPanel.getMyHeight());
		superPanel.add(methodPanel);
		
		createLabel("Selection Method", methodPanel);
		selectionCB = createComboBox(SelectionMethod.class, methodPanel);
		selectionCB.addItemListener(new ItemListener() {
			@Override
            public void itemStateChanged(ItemEvent e) {
            	if (e.getItem().equals(SelectionMethod.DETERMINISTIC_TOURNAMENT.name()) ||
            			e.getItem().equals(SelectionMethod.PROBABILISTIC_TOURNAMENT.name())) {
            		tournamentPanel.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            		if (e.getItem().equals(SelectionMethod.PROBABILISTIC_TOURNAMENT.name()))
            				probabilisticTournamentPanel.setVisible(e.getStateChange() == ItemEvent.SELECTED);
            	}
            	if (e.getItem().equals(SelectionMethod.TRUNCATION.name())) {
            		superPanel.getComponent(labelTruncPos).setVisible(true);
            		truncationSpinner.setVisible(true);
            	}
            	else {
            		superPanel.getComponent(labelTruncPos).setVisible(false);
            		truncationSpinner.setVisible(false);
            	}

            }
        });
		methodPanel.addHeight(SMALL_VERTICAL_MARGIN);
		createLabel("Crossover Method", methodPanel);
		crossCB = createComboBox(CrossoverMethod.class, methodPanel);
		crossCB.setSelectedItem(DEFAULT_CROSSOVER_METHOD.toString());
		methodPanel.addHeight(SMALL_VERTICAL_MARGIN);
		createLabel("Mutation Method", methodPanel);
		mutationCB = createComboBox(MutationMethod.class, methodPanel);
		mutationCB.setSelectedItem(DEFAULT_MUTATION_METHOD.toString());
		
		methodPanel.addHeight(VERTICAL_MARGIN);
		methodPanel.setSize(Window.PANEL_WIDTH, methodPanel.getMyHeight());
		superPanel.addHeight(methodPanel.getMyHeight());
	}

	private void initStartButton() {
		JButton btnStart = new JButton("Start Genetic Algorithm");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});
		btnStart.setBounds(968, 563, 117, 25);
		contentPane.add(btnStart);
	}
	
	private void initResultTextArea() {
		textValue = new JTextArea();
		textValue.setBounds(300, 554, 559, 84);
		contentPane.add(textValue);	
	}
	
	/* ++ The scroll pane is not necessary in our view +++++++++++++++++++++++++
	private void initScrollPane() {
		scroll = new JScrollPane(textValue);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	*/

	private void start() {
		try {
			Request request = new Request(this);
			Integer generationAmount = request.getGenerationAmount();
			this.gens = new double[generationAmount];
			for (int i = 0; i < generationAmount; i++)
				gens[i] = i;
			Controller.getInstance().execute(request);
			Controller.getInstance().updateView(this, functionCB.getSelectedItem() == FitnessFunction.ADAPTATION);
		} catch (InvalidInputException iie) {
			JOptionPane.showMessageDialog(this, iie.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void paintResult(double[] generationAverage, double[] generationLeaders,
			double[] bestAbsoluteValue, double[] selectivePressure, String bestResult) {
		plot.resetMapData();
		plot.setVisible(true);
		plot.removeAllPlots();
		plot.addLinePlot("media", Color.green, gens, generationAverage);
		plot.addLinePlot("mejor de la generacion", Color.red, gens, generationLeaders);
		plot.addLinePlot("mejor absoluto", Color.blue, gens, bestAbsoluteValue);
		plot.addLinePlot("presi�n selectiva", Color.black, gens, selectivePressure);
		plot.repaint();
		textValue.setText(bestResult);
	}
	
	public void paintP3Graphics(double[] idealFunction, double[] obtainedFunction, double[] xvalues) {
		plot.resetMapData();
		plot.setVisible(true);
		plot.removeAllPlots();
		plot.addLinePlot("Funcion Objetivo", Color.red, xvalues, idealFunction);
		plot.addLinePlot("Funcion Obtenida", Color.blue, xvalues, obtainedFunction);
		plot.repaint();
	}
	
	@Override
	public String getPopulationAmount() {
		return this.populationAmountTF.getText();
	}

	@Override
	public String getGenerationAmount() {
		return this.generationAmountTF.getText();
	}

	@Override
	public String getCrossoverPercentage() {
		return this.crossoverRateSpinner.getValue().toString();
	}

	@Override
	public String getMutationPercentage() {
		return this.mutationRateSpinner.getValue().toString();
	}

	@Override
	public String getSelectionMethod() {
		return (String) selectionCB.getSelectedItem();
	}

	@Override
	public String getMutationMethod() {
		return (String) mutationCB.getSelectedItem();
	}

	@Override
	public String getElitismPercentage() {
		return this.elitismRateSpinner.getValue().toString();
	}

	@Override
	public String getFitnessFunction() {
		return this.functionCB.getSelectedItem().toString();
	}

	@Override
	public String getCrossoverMethod() {
		return this.crossCB.getSelectedItem().toString();
	}


}
