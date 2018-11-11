package pinturillo2_0;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.JList;
import javax.swing.JPanel;

public class Respondedor implements Runnable {
	static HashMap<String, Color> pixeles = new HashMap<String, Color>();
	Color color = Color.BLACK;
	Socket socketCliente;
	ArrayList lista;
	JPanel panel;

	public Respondedor(Socket socketCliente, JPanel panel) {
		this.socketCliente = socketCliente;
		this.panel = panel;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
			String inputLine="", outputLine="";
			if (in == null) return;
			while (!(inputLine = in.readLine()).equals("salir")) {
				if (inputLine.startsWith("color:")) {
					String valor[] = inputLine.split(":");

					String textoColor = valor[1];

					switch (textoColor.toLowerCase()) {

					case "red":
					case "rojo":
						color = Color.RED;
						break;

					case "green":
					case "verde":
						color = Color.GREEN;
						break;

					case "blue":
					case "azul":
						color = Color.BLUE;
						break;

					case "black":
					case "negro":
						color = Color.BLACK;
						break;

					case "orange":
					case "naranja":
						color = Color.ORANGE;
						break;

					case "pink":
					case "rosa":
						color = Color.PINK;
						break;

					case "gray":
					case "gris":
						color = Color.GRAY;
						break;

					case "drakgray":
					case "grisoscuro":
						color = Color.DARK_GRAY;
						break;

					case "magenta":
						color = Color.MAGENTA;
						break;

					case "yellow":
					case "amarillo":
						color = Color.YELLOW;
						break;

					}
				} else if (inputLine.startsWith("salir")) {

				}
				else if (inputLine.indexOf(':') != -1) {
					String valor[] = inputLine.split(":");
					int x = Integer.parseInt(valor[0]);
					int y = Integer.parseInt(valor[1]);

					Graphics g = panel.getGraphics();
					g.setColor(color);
					g.drawOval(x, y, 2, 2);

					pixeles.put(inputLine, color);

				}else if(inputLine.equals("dame")) {
					ObjectOutputStream oos = new ObjectOutputStream(socketCliente.getOutputStream());
					oos.writeObject(pixeles);
				}
				else {

				}
			}
			out.close();
			in.close();
			socketCliente.close();
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}
}
