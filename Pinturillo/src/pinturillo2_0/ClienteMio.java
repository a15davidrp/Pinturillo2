package pinturillo2_0;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class ClienteMio extends JFrame {


	private static final long serialVersionUID = 1303601066005116696L;
	private JPanel contentPane;
	private JTextField txtIp;
	private JTextField txtPuerto;
	private JList<String> list;
	private JTextField txtPalabra;


	Socket soketCliente=null;
	PrintWriter salida = null;
	BufferedReader entrada = null;
	String ip;
	int puerto;
	ClienteTxt cTxt;
	String activo="no";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteMio frame = new ClienteMio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClienteMio() {
		setMinimumSize(new Dimension(800, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{124, 94, 221, 46, 0, 0};
		gbl_panel.rowHeights = new int[]{14, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		JLabel lblPalabra = new JLabel("Palabra:");
		GridBagConstraints gbc_lblPalabra = new GridBagConstraints();
		gbc_lblPalabra.insets = new Insets(0, 0, 5, 5);
		gbc_lblPalabra.gridx = 1;
		gbc_lblPalabra.gridy = 0;
		panel.add(lblPalabra, gbc_lblPalabra);

		JLabel lblIp_1 = new JLabel("IP");
		GridBagConstraints gbc_lblIp_1 = new GridBagConstraints();
		gbc_lblIp_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblIp_1.anchor = GridBagConstraints.EAST;
		gbc_lblIp_1.gridx = 2;
		gbc_lblIp_1.gridy = 0;
		panel.add(lblIp_1, gbc_lblIp_1);

		txtIp = new JTextField();
		txtIp.setText("localhost");
		GridBagConstraints gbc_txtIp = new GridBagConstraints();
		gbc_txtIp.anchor = GridBagConstraints.WEST;
		gbc_txtIp.insets = new Insets(0, 0, 5, 5);
		gbc_txtIp.gridx = 3;
		gbc_txtIp.gridy = 0;
		panel.add(txtIp, gbc_txtIp);
		txtIp.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 5, 0);
		gbc_btnConectar.gridx = 4;
		gbc_btnConectar.gridy = 0;
		panel.add(btnConectar, gbc_btnConectar);
		GridBagConstraints gbc_lblRng = new GridBagConstraints();
		gbc_lblRng.insets = new Insets(0, 0, 5, 5);
		gbc_lblRng.gridx = 0;
		gbc_lblRng.gridy = 1;

		JLabel lblCoso = new JLabel("...");
		GridBagConstraints gbc_lblCoso = new GridBagConstraints();
		gbc_lblCoso.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoso.gridx = 1;
		gbc_lblCoso.gridy = 1;
		panel.add(lblCoso, gbc_lblCoso);

		JLabel lblPuerto = new JLabel("Puerto");
		GridBagConstraints gbc_lblPuerto = new GridBagConstraints();
		gbc_lblPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_lblPuerto.anchor = GridBagConstraints.EAST;
		gbc_lblPuerto.gridx = 2;
		gbc_lblPuerto.gridy = 1;
		panel.add(lblPuerto, gbc_lblPuerto);

		txtPuerto = new JTextField();
		txtPuerto.setText("3333");
		GridBagConstraints gbc_txtPuerto = new GridBagConstraints();
		gbc_txtPuerto.anchor = GridBagConstraints.WEST;
		gbc_txtPuerto.insets = new Insets(0, 0, 5, 5);
		gbc_txtPuerto.gridx = 3;
		gbc_txtPuerto.gridy = 1;
		panel.add(txtPuerto, gbc_txtPuerto);
		txtPuerto.setColumns(10);

		JButton btnDesconectar = new JButton("Desconectar");
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.insets = new Insets(0, 0, 5, 0);
		gbc_btnDesconectar.gridx = 4;
		gbc_btnDesconectar.gridy = 1;
		panel.add(btnDesconectar, gbc_btnDesconectar);

		JLabel label = new JLabel("   ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		panel.add(label, gbc_label);

		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);

		JPanel panelDibujo = new JPanel();
		splitPane.setRightComponent(panelDibujo);

		JPanel panel_3 = new JPanel();
		splitPane.setLeftComponent(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_3.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setMinimumSize(new Dimension(200, 100));
		scrollPane.setPreferredSize(new Dimension(1, 2));

		list = new JList<String>();
		list.setModel(new DefaultListModel<String>());
		DefaultListModel<String> dlm = (DefaultListModel<String>) list.getModel();
		scrollPane.setViewportView(list);

		JLabel lblChat = new JLabel("Chat del juego");
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblChat);

		JPanel panel_1 = new JPanel();
		panel_3.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtPalabra = new JTextField();
		panel_1.add(txtPalabra);
		txtPalabra.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		panel_1.add(btnEnviar);


		////////////////////////////////eventos

		//conectar
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AbrirConexion();
			}
		});



		//desconectar
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CerrarConexion();
			}
		});



		//EnviarTexto
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(soketCliente !=null) {
					CerrarConexion();
					cTxt = new ClienteTxt(txtIp, txtPuerto, txtPalabra, dlm);
					
				}else {
					cTxt = new ClienteTxt(txtIp, txtPuerto, txtPalabra, dlm);
					
				}
			}
		});



		//imagen
		panelDibujo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent a) {
				//enviar mensaje por click

				if(activo.equals("si")) {
					salida.println(a.getX()+":"+a.getY());
				}
			}
		});
	}

	public void AbrirConexion() {
		try {
			ip=txtIp.getText();
			puerto= Integer.parseInt(txtPuerto.getText());

			System.out.println("abierto");

			//establecer conexion cn el server mediante socket
			soketCliente = new Socket(ip, puerto);

			//abrir los canales entrada salida
			salida = new PrintWriter(soketCliente.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(soketCliente.getInputStream()));

			activo="si";
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void CerrarConexion() {
		try {
			soketCliente.close();
			salida.close();
			entrada.close();

		} catch (Exception ess) {
			ess.printStackTrace();
		}
	}
}
