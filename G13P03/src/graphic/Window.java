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
import javax.swing.JCheckBox;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.math.plot.Plot2DPanel;

import control.Client;
import control.Controller;
import control.InvalidInputException;
import control.Request;
import control.RequestMaker;
import model.crossover.CrossoverMethod;
import model.fitness.FitnessFunction;
import model.gen.practice3.GenType;
import model.initialization.practice3.TreeInitializerEnum;
import model.mutation.MutationMethod;
import model.selection.SelectionMethod;

public class Window extends JFrame implements RequestMaker, Client {
	
	private static final double DEFAULT_CROSSOVER_RATE = 60;
	private static final double DEFAULT_MUTATION_RATE = 5;
	private static final int DEFAULT_POPULATION_AMOUNT = 100;
	private static final int DEFAULT_GENERATION_AMOUNT = 100;
	private static final double DEFAULT_ELITISM_RATE = 0;
	private static final TreeInitializerEnum DEFAULT_INITIALIZATION_METHOD = TreeInitializerEnum.FULL;
	private static final CrossoverMethod DEFAULT_CROSSOVER_METHOD = CrossoverMethod.CROSSOVER_TREE;
	private static final MutationMethod DEFAULT_MUTATION_METHOD = MutationMethod.TERMINAL;
	private static final GenType DEFAULT_GEN_TYPE = GenType.PROGRAMACION_EVOLUTIVA;

	
	private static final long serialVersionUID = 8815627840243675666L;
	
	private static final int OUTER_LEFT_MARGIN = 22;
	private static final int LEFT_MARGIN = 12;
	private static final int LABEL_HEIGHT = 20;
	private static final int LABEL_WIDTH = 150;
	private static final int TEXT_FIELD_HIGH_SIZE = 20;
	private static final int TEXT_FIELD_WIDTH = 150;
	private static final int SPINNER_HIGH_SIZE = 20;
	private static final int SPINNER_WIDTH = 80;
	private static final int CHECKBOX_HIGH_SIZE = 20;
	private static final int CHECKBOX_WIDTH = 80;
	private static final int COMBO_BOX_HEIGHT = 20;
	private static final int COMBO_BOX_WIDTH = 200;
	private static final int MAX_WIDTH = OBTAIN_MAX_WIDTH();
	private static final int PANEL_WIDTH = MAX_WIDTH + 2 * LEFT_MARGIN;
	private static final int BORDER_THICKNESS = 1;
	private static final int VERTICAL_MARGIN = 5;
	private static final int SMALL_VERTICAL_MARGIN = 1;
	private static final int DEFAULT_TRUNCATION_AMOUNT = 25;
	private static final int DEFAULT_CONTESTANTS_AMOUNT = 3;
	private static final int DEFAULT_CHAMPION_PROBABILITY = 50;
	private static final boolean DEFAULT_BLOATING_CHECK = true;
	private static final int DEFAULT_MAX_DEPTH = 4;
	private static final int DEFAULT_WRAPS = 2;
	
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
	private JTextField populationAmountTF, generationAmountTF, championPercentageTF, contestantsAmountTF, DepthTF,
			seedTF, wrapsTF;
	private JSpinner crossoverRateSpinner, mutationRateSpinner, elitismRateSpinner, truncationSpinner;
	private JComboBox<String> functionCB;
	
	private MyPanel tournamentPanel, probabilisticTournamentPanel;
	
	private MyPanel methodPanel;
	private JComboBox<String> initCB, crossCB, selectionCB, mutationCB, genCB;
	private JCheckBox bloating;
	private JTabbedPane generalPanel;
	
	private double[] gens;
	private Plot2DPanel plot, plotP3;
	private JTextArea textValue;
	private int labelTruncPos;
	
	private PlotData plotData;

	/**
	 * Create the frame.
	 */
	public Window() {
		setTitle("Genetic Algoritm Runner [by Group 13] <Practice 03>");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 1200, 775);
		contentPane = new MyPanel(null, Window.OUTER_LEFT_MARGIN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		initSettings(contentPane);
		initMethodPanel(contentPane);
		initTournamentParameters(contentPane);
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
	
	private JCheckBox createCheckBox(boolean isChecked, MyPanel panel) {
		JCheckBox checkBox = new JCheckBox();
		checkBox.setBounds(panel.innerLeftMargin, panel.getMyHeight(),
				CHECKBOX_WIDTH, CHECKBOX_HIGH_SIZE);
		panel.addHeight(SPINNER_HIGH_SIZE);
		checkBox.setSelected(isChecked);
		panel.add(checkBox);
		return checkBox;
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
		createLabel("Maximun Depth", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		DepthTF = createTextField(String.valueOf(Window.DEFAULT_MAX_DEPTH), superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Number of Wraps", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		wrapsTF = createTextField(String.valueOf(Window.DEFAULT_WRAPS), superPanel);
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
		createLabel("Activate Bloating", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		bloating = createCheckBox(Window.DEFAULT_BLOATING_CHECK, superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Seed: ", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		seedTF = createTextField("", superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Function", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		functionCB = createComboBox(FitnessFunction.class, superPanel);
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Gen Type", superPanel);
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		genCB = createComboBox(GenType.class, superPanel);
		genCB.setSelectedItem(DEFAULT_GEN_TYPE.name());
		genCB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (e.getItem().toString().equals(GenType.PROGRAMACION_EVOLUTIVA.name())) {
						crossCB.setSelectedItem(CrossoverMethod.CROSSOVER_TREE.name());
						mutationCB.setSelectedItem(MutationMethod.PERMUTATION.name());
						initCB.setEnabled(true);
					} else if (e.getItem().toString().equals(GenType.GRAMATICA_EVOLUTIVA.name())) {
						crossCB.setSelectedItem(CrossoverMethod.ONE_POINT.name());
						mutationCB.setSelectedItem(MutationMethod.REAL.name());
						initCB.setEnabled(false);
					}
				}
			}
		});
		superPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Truncation Rate (%)", superPanel);
		labelTruncPos = superPanel.getComponentCount()-1;
		superPanel.addHeight(SMALL_VERTICAL_MARGIN);
		truncationSpinner = createSpinner(Window.DEFAULT_TRUNCATION_AMOUNT, superPanel);
		superPanel.getComponent(labelTruncPos).setVisible(false);
		truncationSpinner.setVisible(false);
	}
	
	private void initGraphicPanel() {
		generalPanel = new JTabbedPane();
		generalPanel.setBackground(new Color(222, 221, 218));
		generalPanel.setBounds(287, 41, 835, 450);
		contentPane.add(generalPanel);
		plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		generalPanel.addTab("Progresión", plot);
		plot.setBounds(228, 12, 430, 315);
		plot.setPreferredSize(new Dimension(835, 450));
		plot.setVisible(false);
		plotP3 = new Plot2DPanel();
		plotP3.addLegend("SOUTH");
		generalPanel.addTab("Resultado Final", plotP3);
		plotP3.setBounds(228, 12, 430, 315);
		plotP3.setPreferredSize(new Dimension(835, 450));
		plotP3.setVisible(false);
		generalPanel.addChangeListener(new ChangeListener() {
			@Override
	        public void stateChanged(ChangeEvent e) {
				if (generalPanel.getSelectedIndex() == 0)
					reprintProgresionPlot();
				else if (generalPanel.getSelectedIndex() == 1)
					reprintResultadoFinalPlot();
	        }
	    });
		plotData = new PlotData();
	}
	
	private void initMethodPanel(MyPanel superPanel) {
		methodPanel = new MyPanel(null, Window.LEFT_MARGIN);
		methodPanel.setBorder(new LineBorder(Color.BLACK, Window.BORDER_THICKNESS, true));
		methodPanel.setLocation(superPanel.getInnerLeftMargin(), superPanel.getMyHeight());
		superPanel.add(methodPanel);
		
		createLabel("Initialization Method", methodPanel);
		initCB = createComboBox(TreeInitializerEnum.class, methodPanel);
		initCB.setSelectedItem(DEFAULT_INITIALIZATION_METHOD.toString());
		methodPanel.addHeight(SMALL_VERTICAL_MARGIN);

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
	
	private void initTournamentParameters(MyPanel superPanel) {
		tournamentPanel = new MyPanel(null, Window.LEFT_MARGIN);
		tournamentPanel.setBorder(new LineBorder(Color.BLACK, Window.BORDER_THICKNESS, true));
		tournamentPanel.setLocation(superPanel.getInnerLeftMargin(), superPanel.getMyHeight());
		tournamentPanel.setVisible(false);
		
		tournamentPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Contestants Amount", tournamentPanel);
		tournamentPanel.addHeight(SMALL_VERTICAL_MARGIN);
		contestantsAmountTF = createTextField(String.valueOf(Window.DEFAULT_CONTESTANTS_AMOUNT), tournamentPanel);
		
		probabilisticTournamentPanel = new MyPanel(null, 0);
		probabilisticTournamentPanel.setLocation(tournamentPanel.getInnerLeftMargin(), tournamentPanel.getMyHeight());
		probabilisticTournamentPanel.setVisible(false);
		
		probabilisticTournamentPanel.addHeight(VERTICAL_MARGIN);
		createLabel("Champion Probability", probabilisticTournamentPanel);
		probabilisticTournamentPanel.addHeight(SMALL_VERTICAL_MARGIN);
		championPercentageTF = createTextField(String.valueOf(Window.DEFAULT_CHAMPION_PROBABILITY),
				probabilisticTournamentPanel);
		
		probabilisticTournamentPanel.setSize(MAX_WIDTH,
				probabilisticTournamentPanel.getMyHeight());
		tournamentPanel.add(probabilisticTournamentPanel);
		tournamentPanel.addHeight(probabilisticTournamentPanel.getMyHeight());
		tournamentPanel.addHeight(VERTICAL_MARGIN);
		
		tournamentPanel.setSize(Window.PANEL_WIDTH, tournamentPanel.getMyHeight());
		superPanel.add(tournamentPanel);
		superPanel.addHeight(tournamentPanel.getMyHeight());
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
		textValue.setBounds(300, 554, 559, 100);
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
			Controller.getInstance().updateView(this, functionCB.getSelectedItem().equals(FitnessFunction.ADAPTATION.name()));
		} catch (InvalidInputException iie) {
			JOptionPane.showMessageDialog(this, iie.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private class PlotData {
		private double[] generationAverage;
		private double[] generationLeaders;
		private double[] bestAbsoluteValue;
		private double[] idealFunction;
		private double[] obtainedFunction;
		private double[] xvalues;
	}
	
	public void paintResult(double[] generationAverage, double[] generationLeaders,
			double[] bestAbsoluteValue, String bestResult) {
		plotData.generationAverage = generationAverage;
		plotData.generationLeaders = generationLeaders;
		plotData.bestAbsoluteValue = bestAbsoluteValue;
		textValue.setText(bestResult);
		reprintProgresionPlot();
	}
	
	public void paintP3Graphics(double[] idealFunction, double[] obtainedFunction, double[] xvalues) {
		plotData.idealFunction = idealFunction;
		plotData.obtainedFunction = obtainedFunction;
		plotData.xvalues = xvalues;
	}
	
	private void reprintProgresionPlot() {
		plotP3.setVisible(false);
		plotP3.removeAllPlots();
		plotP3.removeAllPlotables();
		plotP3.resetMapData();
		plot.removeAllPlots();
		plot.removeAllPlotables();
		plot.resetMapData();
		plot.addLinePlot("Media", Color.green, gens, plotData.generationAverage);
		plot.addLinePlot("Mejor de la generación", Color.red, gens, plotData.generationLeaders);
		plot.addLinePlot("Mejor absoluto", Color.blue, gens, plotData.bestAbsoluteValue);
		plot.setVisible(true);
		generalPanel.setSelectedComponent(plot);
		generalPanel.updateUI();
		generalPanel.repaint();
	}
	
	private void reprintResultadoFinalPlot() {
		plot.setVisible(false);
		plot.removeAllPlots();
		plot.removeAllPlotables();
		plot.resetMapData();
		plotP3.removeAllPlots();
		plotP3.removeAllPlotables();
		plotP3.resetMapData();
		plotP3.addLinePlot("Función Objetivo", Color.red, plotData.xvalues, plotData.idealFunction);
		plotP3.addLinePlot("Función Obtenida", Color.blue, plotData.xvalues, plotData.obtainedFunction);
		plotP3.setVisible(true);
		generalPanel.setSelectedComponent(plotP3);
		generalPanel.updateUI();
		generalPanel.repaint();
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

	@Override
	public String getContestantsAmount() {
		return contestantsAmountTF.getText();
	}

	@Override
	public String getChampionPercentage() {
		return championPercentageTF.getText();
	}

	@Override
	public String getTruncationPercentage() {
		return truncationSpinner.getValue().toString();
	}

	@Override
	public boolean isBloatingActive() {
		return bloating.isSelected();
	}

	@Override
	public String getInitializationMethod() {
		return initCB.getSelectedItem().toString();
	}

	@Override
	public String getMaxDepth() {
		return DepthTF.getText();
	}

	@Override
	public String getSeed() {
		return seedTF.getText();
	}

	@Override
	public String getWraps() {
		return wrapsTF.getText();
	}

	@Override
	public String getGenType() {
		return genCB.getSelectedItem().toString();
	}
}
