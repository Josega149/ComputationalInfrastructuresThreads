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
	 * Variable que representa la cola de mensajes que esperan a ser atendidos
	 */
	private Mensaje [] Mensajes;

	/**
	 *Variable que representa el pool de servidores
	 */
	ThreadServidor [] pool;





	public boolean atenderMensaje(Mensaje message)
	{
		return false;
	}
	public Mensaje responderMensaje()
	{
		return null;
	}
	public ServidorBuffer(int numServidores, int numMaxClientesPosibles)
	{
		
		Mensajes = new Mensaje [numMaxClientesPosibles];
		// crea el pool de threads servidores que quedan a la espera de mensajes

		pool = new ThreadServidor [numServidores];
		for(int i=0; i< pool.length ;i++)
		{
			pool[i] = new ThreadServidor();
		}


		//inicializa los thread servidor para que comiencen a responder solicitudes
		for(int i=0; i< pool.length ;i++)
		{
			pool[i].run();
		}


	}


}
