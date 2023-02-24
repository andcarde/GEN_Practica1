package graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

import org.math.plot.Plot2DPanel;

import model.selection.SelectionMethod;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;

public class Window extends JFrame implements RequestMaker {

	private static final long serialVersionUID = 8815627840243675666L;
	
	private JPanel contentPane, panel;
	private JLabel lblNmeroDeGeneraciones, lblNewLabel, lblNewLabel_1, lblCruces;
	private JTextField txtGeneraciones, txtPrecision, txtTamPobl;
	private JComboBox comboMutacion, comboSeleccion, comboCruce, comboFuncion;
	private JSpinner spinnerCruces, spinnerMutaciones, spinnerElitismo;
	private Controller _ctrl;
	private double[] gens;
	private Plot2DPanel plot;
	private JTextArea textValue;
	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Window() {
		setTitle("G13P01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTamaoDePoblacin = new JLabel("Tamaño de Población");
		lblTamaoDePoblacin.setBounds(12, 12, 151, 15);
		contentPane.add(lblTamaoDePoblacin);
		
		txtTamPobl = new JTextField("100");
		txtTamPobl.setBounds(22, 32, 114, 19);
		contentPane.add(txtTamPobl);
		txtTamPobl.setColumns(10);
		
		lblNmeroDeGeneraciones = new JLabel("Num Generaciones");
		lblNmeroDeGeneraciones.setBounds(12, 67, 191, 15);
		contentPane.add(lblNmeroDeGeneraciones);
		
		txtGeneraciones = new JTextField("100");
		txtGeneraciones.setBounds(22, 90, 114, 19);
		contentPane.add(txtGeneraciones);
		txtGeneraciones.setColumns(10);
		
		lblCruces = new JLabel("% Cruces");
		lblCruces.setBounds(22, 121, 114, 15);
		contentPane.add(lblCruces);
		
		spinnerCruces = new JSpinner();
		spinnerCruces.setBounds(22, 135, 72, 20);
		contentPane.add(spinnerCruces);
		
		JLabel lblMutaciones = new JLabel("% Mutaciones");
		lblMutaciones.setBounds(12, 170, 114, 15);
		contentPane.add(lblMutaciones);
		
		spinnerMutaciones = new JSpinner();
		spinnerMutaciones.setBounds(22, 193, 72, 20);
		contentPane.add(spinnerMutaciones);
		
		JLabel lblPrecision = new JLabel("Precisión");
		lblPrecision.setBounds(22, 284, 114, 15);
		contentPane.add(lblPrecision);
		
		txtPrecision = new JTextField("0.001");
		txtPrecision.setBounds(22, 304, 72, 20);
		contentPane.add(txtPrecision);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(22, 441, 171, 163);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Selección");
		lblNewLabel.setBounds(12, 8, 70, 15);
		panel.add(lblNewLabel);
		
		comboSeleccion = new JComboBox();
		comboSeleccion.addItem(SelectionMethod.ROULETTE);
		comboSeleccion.addItem(SelectionMethod.UNIVERSAL_STOCHASTIC);
		comboSeleccion.addItem(SelectionMethod.DETERMINISTIC_TOURNAMENT.name());
		comboSeleccion.addItem(SelectionMethod.TRUNCATION.name());
		comboSeleccion.addItem(SelectionMethod.PROBABILISTIC_TOURNAMENT.name());
		comboSeleccion.addItem(SelectionMethod.REMAINS.name());

		comboSeleccion.setBounds(12, 26, 119, 18);
		panel.add(comboSeleccion);
		
		JLabel lblCruce = new JLabel("Cruce");
		lblCruce.setBounds(12, 58, 70, 15);
		panel.add(lblCruce);
		
		comboCruce = new JComboBox();
		comboCruce.addItem("ONE_POINT");
		comboCruce.addItem("UNIFORM");
		comboCruce.setBounds(12, 74, 119, 18);
		panel.add(comboCruce);
		
		lblNewLabel_1 = new JLabel("Mutación");
		lblNewLabel_1.setBounds(12, 106, 70, 15);
		panel.add(lblNewLabel_1);
		
		comboMutacion = new JComboBox();
		comboMutacion.addItem("BASIC");
		comboMutacion.setBounds(12, 123, 119, 18);
		panel.add(comboMutacion);
		
		JPanel graphicPanel = new JPanel();

		graphicPanel.setBackground(new Color(222, 221, 218));

		graphicPanel.setBounds(287, 41, 835, 450);

		contentPane.add(graphicPanel);

		plot = new Plot2DPanel();

		plot.addLegend("SOUTH");



		graphicPanel.add(plot);
		plot.setBounds(228, 12, 430, 315);
		plot.setPreferredSize(new Dimension(835, 450));
		
		plot.setVisible(false);

		textValue = new JTextArea();

		textValue.setBounds(300, 554, 559, 34);

		contentPane.add(textValue);

		JButton btnStart = new JButton("Empezar");

		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		btnStart.setBounds(968, 563, 117, 25);

		contentPane.add(btnStart);
		
		JLabel lblElitismo = new JLabel("% Elitismo");
		lblElitismo.setBounds(22, 236, 104, 15);
		contentPane.add(lblElitismo);
		
		spinnerElitismo = new JSpinner();
		spinnerElitismo.setBounds(22, 252, 72, 20);
		contentPane.add(spinnerElitismo);
		
		JLabel lblFuncion = new JLabel("Función");
		lblFuncion.setBounds(22, 373, 70, 15);
		contentPane.add(lblFuncion);
		
		comboFuncion = new JComboBox();
		comboFuncion.addItem("FUNCTION1");
		comboFuncion.addItem("FUNCTION2");
		comboFuncion.addItem("FUNCTION3");
		comboFuncion.addItem("FUNCTION4");
		
		comboFuncion.setBounds(22, 392, 119, 18);
		contentPane.add(comboFuncion);
		
		
	}

	private void start() {
		try {
			gens = new double[getPopulationAmount()];
			for (int i = 0; i < getPopulationAmount(); i++) {
				gens[i] = i;
			}
			Controller.getInstance().execute(new Request(this));
		} catch (InvalidInputException e) {
			JOptionPane.showMessageDialog(this,e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void paintResult(double[] generationAverage, double[] generationLeaders, double[] bestAbsoluteValue,String bestResult) {
		plot.resetMapData();
		plot.setVisible(true);
		plot.removeAllPlots();
		for (int i = 0; i < generationLeaders.length; i++) {
			System.out.println(generationLeaders[i]);
		}
		plot.addLinePlot("media", Color.green, gens, generationAverage);
		plot.addLinePlot("mejor valor", Color.blue, gens, generationLeaders);
		plot.addLinePlot("mejor valor absoluto", Color.red, gens, bestAbsoluteValue);
		plot.repaint();
		textValue.setText(bestResult);
	}
	@Override
	public Integer getPopulationAmount() {
		return Integer.parseInt(txtTamPobl.getText());
	}

	@Override
	public Integer getGenerationAmount() {
		return Integer.parseInt(txtGeneraciones.getText());

	}

	@Override
	public Integer getCrossoverPercentage() {
		return (Integer) spinnerCruces.getValue();

	}

	@Override
	public Integer getMutationPercentage() {
		return (Integer) spinnerMutaciones.getValue();

	}

	@Override
	public Double getPrecision() {
		return Double.parseDouble(txtPrecision.getText());
	}

	@Override
	public String getSelectionMethod() {
		return (String) comboSeleccion.getSelectedItem();
	}

	@Override
	public String getTournamentMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMutationMethod() {
		return (String) comboMutacion.getSelectedItem();

	}

	@Override
	public Integer getElitismPercentage() {
		return (Integer) spinnerElitismo.getValue();
	}

	@Override
	public String getFitnessFunction() {
		return (String) comboFuncion.getSelectedItem();
	}

	@Override
	public Integer getContestantsAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getChampionProbability() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getFuction4Dimension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCrossoverMethod() {
		return (String) comboCruce.getSelectedItem();

	}
}
