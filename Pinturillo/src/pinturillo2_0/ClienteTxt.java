package pinturillo2_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClienteTxt {

	public ClienteTxt(JTextField txtIp, JTextField txtPuerto, JTextField txtPalabra, DefaultListModel<String> dlm) {



		Socket soketCliente=null;
		PrintWriter salida = null;
		BufferedReader entrada = null;
		String respuesta = "";
		String ip;
		int puerto;
		ClienteTxt cTxt = null;

		try {
			ip=txtIp.getText();
			puerto= Integer.parseInt(txtPuerto.getText());

			//establecer conexion cn el server mediante socket
			soketCliente = new Socket(ip, puerto);

			//abrir los canales entrada salida
			salida = new PrintWriter(soketCliente.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(soketCliente.getInputStream()));

			salida.println("*/"+txtPalabra.getText());

			//recibir mensaje por entrada
			respuesta = entrada.readLine();
			dlm.addElement(respuesta);

		} catch (IOException e) {
			System.out.println(e);
		}
	}
}