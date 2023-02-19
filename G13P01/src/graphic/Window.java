package graphic;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;

import org.math.plot.Plot2DPanel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class Window extends JFrame implements RequestMaker {

	private static final long serialVersionUID = 8815627840243675666L;
	
	private JPanel contentPane;
	private JTextField txtTamPobl;
	private JLabel lblNmeroDeGeneraciones;
	private JTextField txtGeneraciones;
	private JLabel lblCruces;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JComboBox comboBox_1;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox_2;

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
		setBounds(100, 100, 700, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTamaoDePoblacin = new JLabel("Tamaño de Población");
		lblTamaoDePoblacin.setBounds(12, 12, 151, 15);
		contentPane.add(lblTamaoDePoblacin);
		
		txtTamPobl = new JTextField();
		txtTamPobl.setBounds(22, 32, 114, 19);
		contentPane.add(txtTamPobl);
		txtTamPobl.setColumns(10);
		
		lblNmeroDeGeneraciones = new JLabel("Num Generaciones");
		lblNmeroDeGeneraciones.setBounds(12, 67, 191, 15);
		contentPane.add(lblNmeroDeGeneraciones);
		
		txtGeneraciones = new JTextField();
		txtGeneraciones.setBounds(22, 90, 114, 19);
		contentPane.add(txtGeneraciones);
		txtGeneraciones.setColumns(10);
		
		lblCruces = new JLabel("% Cruces");
		lblCruces.setBounds(22, 121, 114, 15);
		contentPane.add(lblCruces);
		
		JSpinner spinnerCruces = new JSpinner();
		spinnerCruces.setBounds(22, 135, 72, 20);
		contentPane.add(spinnerCruces);
		
		JLabel lblMutaciones = new JLabel("% Mutaciones");
		lblMutaciones.setBounds(12, 170, 114, 15);
		contentPane.add(lblMutaciones);
		
		JSpinner spinnerMutaciones = new JSpinner();
		spinnerMutaciones.setBounds(22, 193, 72, 20);
		contentPane.add(spinnerMutaciones);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(22, 226, 171, 163);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Selección");
		lblNewLabel.setBounds(12, 8, 70, 15);
		panel.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 26, 90, 18);
		panel.add(comboBox);
		
		JLabel lblCruce = new JLabel("Cruce");
		lblCruce.setBounds(12, 58, 70, 15);
		panel.add(lblCruce);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(12, 74, 90, 18);
		panel.add(comboBox_1);
		
		lblNewLabel_1 = new JLabel("Mutación");
		lblNewLabel_1.setBounds(12, 106, 70, 15);
		panel.add(lblNewLabel_1);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(12, 123, 90, 18);
		panel.add(comboBox_2);
		
		JPanel graphicPanel = new JPanel();
		graphicPanel.setBackground(new Color(222, 221, 218));
		graphicPanel.setBounds(228, 12, 430, 315);
		contentPane.add(graphicPanel);
		
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLegend("SOUTH");
		plot.setBounds(228, 12, 430, 315);
		plot.setVisible(false);
		graphicPanel.add(plot);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(228, 350, 345, 15);
		contentPane.add(textArea);
		
		}

	@Override
	public Integer getPopulationAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getGenerationAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCrossoverPercentage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getMutationPercentage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPrecision() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectionMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTournamentMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMutationMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getElitismPercentage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFitnessFunction() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
}
