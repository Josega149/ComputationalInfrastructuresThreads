package PackageServidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFileChooser;

import PackageClientes.Mensaje;

public class ServidorBuffer 
{
	/**
	 * Esta clase es la encargada de crear y manejar los thread servidores
	 * 
	 * Adicionalmente, debe recibir los mensajes de los clientes en una cola de mensajes
	 */

	
	/**
	 * Variable para tener un control sobre los clientes que llegan
	 */
	private int numMaxClientesPosibles;
	/**
	 * Variable que representa la cola de mensajes que esperan a ser atendidos
	 */
	private Mensaje [] Mensajes;
	/**
	 * Variable que representa el numero de servidores que van a existir
	 */
	private int numServidores;
	/**
	 *Variable que representa el pool de servidores
	 */
	ThreadServidor [] pool;
	
	private int numClientes;
	private int [] mensajesPorCliente;




	public boolean atenderMensaje(Mensaje message)
	{
		return false;
	}
	public Mensaje responderMensaje()
	{
		return null;
	}
	public ServidorBuffer()
	{
		
		Mensajes = new Mensaje [numMaxClientesPosibles];
		// crea el pool de threads servidores que quedan a la espera de mensajes

		pool = new ThreadServidor [numServidores];
		for(int i=0; i< pool.length ;i++)
		{
			pool[i] = new ThreadServidor();
		}

		// inicializa los clientes

		//inicializa los thread servidor para que comiencen a responder solicitudes
		for(int i=0; i< pool.length ;i++)
		{
			pool[i].run();
		}


	}
	public void leerDatos()
	{
		JFileChooser chooser = new JFileChooser("./data");
		chooser.setDialogTitle("Abrir archivo con los datos");

		File datos = null;
		int resultado = chooser.showOpenDialog(null);

		if(resultado == JFileChooser.APPROVE_OPTION)
		{
			datos = chooser.getSelectedFile();
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(datos));
				String numServidoresS = br.readLine().split(":")[1];
				numServidores = Integer.parseInt(numServidoresS);
				
				String tamanoBufferS = br.readLine().split(":")[1];
				numMaxClientesPosibles = Integer.parseInt(tamanoBufferS);
				
				String tamanoClientes = br.readLine().split(":")[1];
				numClientes = Integer.parseInt(tamanoClientes);
				
				mensajesPorCliente = new int [numClientes];
				for(int i=0; i< mensajesPorCliente.length;i++)
				{
					mensajesPorCliente[i] = Integer.parseInt(br.readLine().split(":")[1]);
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) 
	{
		ServidorBuffer server = new ServidorBuffer();
	}
}
