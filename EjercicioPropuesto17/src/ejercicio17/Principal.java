package ejercicio17;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal {

	private JFrame frame;
	private JLabel lblRes;
	private JLabel lblRes_1;
	private JLabel lblRes_2;
	private JLabel lblPregunta;
	private JLabel lblFin;
	Preguntas pr = new Preguntas();
	int op =1, bien, mal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		lblPregunta = new JLabel("Dale a");
		GridBagConstraints gbc_lblPregunta = new GridBagConstraints();
		gbc_lblPregunta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPregunta.gridx = 1;
		gbc_lblPregunta.gridy = 1;
		frame.getContentPane().add(lblPregunta, gbc_lblPregunta);

		lblRes = new JLabel("a");
		GridBagConstraints gbc_lblRes = new GridBagConstraints();
		gbc_lblRes.insets = new Insets(0, 0, 5, 5);
		gbc_lblRes.gridx = 1;
		gbc_lblRes.gridy = 3;
		frame.getContentPane().add(lblRes, gbc_lblRes);

		lblRes_1 = new JLabel("b");
		GridBagConstraints gbc_lblRes_1 = new GridBagConstraints();
		gbc_lblRes_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblRes_1.gridx = 1;
		gbc_lblRes_1.gridy = 4;
		frame.getContentPane().add(lblRes_1, gbc_lblRes_1);

		lblRes_2 = new JLabel("c");
		GridBagConstraints gbc_lblRes_2 = new GridBagConstraints();
		gbc_lblRes_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblRes_2.gridx = 1;
		gbc_lblRes_2.gridy = 5;
		frame.getContentPane().add(lblRes_2, gbc_lblRes_2);

		lblFin = new JLabel("  ");
		GridBagConstraints gbc_lblFin = new GridBagConstraints();
		gbc_lblFin.insets = new Insets(0, 0, 0, 5);
		gbc_lblFin.gridx = 1;
		gbc_lblFin.gridy = 7;
		frame.getContentPane().add(lblFin, gbc_lblFin);

		JButton btnReiniciar = new JButton("Reiniciar");
		GridBagConstraints gbc_btnReiniciar = new GridBagConstraints();
		gbc_btnReiniciar.gridx = 6;
		gbc_btnReiniciar.gridy = 7;
		frame.getContentPane().add(btnReiniciar, gbc_btnReiniciar);
		//////////////////////////////////////////////////////////////////

		//1º lbl
		lblRes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switch(op){
				case 1:
					bien++;
					op++;
					pr.Preg2(lblPregunta, lblRes, lblRes_1, lblRes_2);
					break;
				case 2:
					mal++;
					op++;
					pr.Preg3(lblPregunta, lblRes, lblRes_1, lblRes_2);
					break;
				case 3:
					mal++;
					lblFin.setText("Acertaste "+bien+" y fallaste "+mal);
					break;
				}
			}
		});

		//2º lbl
		lblRes_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(op){
				case 1:
					mal++;
					op++;
					pr.Preg2(lblPregunta, lblRes, lblRes_1, lblRes_2);
					break;
				case 2:
					bien++;
					op++;
					pr.Preg3(lblPregunta, lblRes, lblRes_1, lblRes_2);
					break;
				case 3:
					mal++;
					lblFin.setText("Acertaste "+bien+" y fallaste "+mal);
					break;
				}
			}
		});

		//3º lbl
		lblRes_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(op){
				case 1:
					mal++;
					op++;
					pr.Preg2(lblPregunta, lblRes, lblRes_1, lblRes_2);
					break;
				case 2:
					mal++;
					op++;
					pr.Preg3(lblPregunta, lblRes, lblRes_1, lblRes_2);
					break;
				case 3:
					bien++;
					lblFin.setText("Acertaste "+bien+" y fallaste "+mal);
					break;
				}
			}
		});

		//reset
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pr.Preg1(lblPregunta, lblRes, lblRes_1, lblRes_2);
				lblFin.setText(" ");
				bien=0;
				mal=0;
				op=1;
			}
		});
	}
}
