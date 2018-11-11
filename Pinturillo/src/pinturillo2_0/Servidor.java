package pinturillo2_0;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Dimension;

public class Servidor implements Runnable {

	private JFrame frame;
	boolean arrancado;
	private JSplitPane splitPane;
	private JPanel panelDraw;
	private JScrollPane scrollPane;
	private JList<String> list;
	private JLabel lblChatDelJuego;
	DefaultListModel<String> dlm;
	Thread hilo=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor window = new Servidor();
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
	public Servidor() {
		initialize();
		boolean arrancado = true;

		Thread hilo = new Thread(this);
		hilo.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(800, 500));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		panelDraw = new JPanel();
		splitPane.setRightComponent(panelDraw);
		
		scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(1, 1));
		scrollPane.setMinimumSize(new Dimension(200, 100));
		splitPane.setLeftComponent(scrollPane);
		
		list = new JList();
		list.setModel(new DefaultListModel<String>());
		dlm = (DefaultListModel<String>) list.getModel();
		scrollPane.setViewportView(list);
		
		lblChatDelJuego = new JLabel("Chat del Juego");
		lblChatDelJuego.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblChatDelJuego);
	}

	@Override
	public void run() {

		ServerSocket socketServer = null;
		Socket soketCliente=null;
		PrintWriter salida = null;
		BufferedReader entrada = null;
		String mensaje;
		String m2, m3;

		try {
			//crear un socket para admitir conexiones o peticiones
			socketServer = new ServerSocket(3333);
			System.out.println("Server arrancado");

			while(true) {
				//esperar a un cliente se conecte
				soketCliente = socketServer.accept();
				System.out.println("llega un nuevo cliente");

				//abrimos canales de entrada/salida para enviar y recibir info
				salida = new PrintWriter(soketCliente.getOutputStream(), true);
				entrada = new BufferedReader(new InputStreamReader(soketCliente.getInputStream()));
				System.out.println("Canales abiertos");

				
				
				
				
				
				//leemos mensaje cliente
				mensaje = entrada.readLine();

				System.out.println("! "+mensaje);
				
				
				
				if(mensaje.contains("*")) {
					//leer texto
					String[] mA=mensaje.split("/");
					m2= mA[1];

					dlm.addElement(soketCliente.getInetAddress() + " " + mensaje);

					//contestar al cliente
					mensaje = soketCliente.getInetAddress() + " " + mensaje;
					salida.println(mensaje);
					System.out.println("contestado con :"+mensaje);
					
					
					
				}else {
					//leer dibujos
					m3=mensaje;
					System.out.println("socke cliente aceptado:"+soketCliente.getInetAddress().toString());

					Respondedor resp = new Respondedor(soketCliente, panelDraw);
					Thread hilo = new Thread(resp);
					hilo.start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				socketServer.close();
				soketCliente.close();
				salida.close();
				entrada.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public JPanel getPanelDraw() {
		return panelDraw;
	}
}
