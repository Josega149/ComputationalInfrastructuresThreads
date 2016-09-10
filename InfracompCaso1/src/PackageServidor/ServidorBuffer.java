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
	private Mensaje [] mensajes;

	/**
	 *Variable que representa el pool de servidores
	 */
	private ThreadServidor [] pool;
	
	private int ultimoMensajeEnPos;





	public boolean enviarMensaje(Mensaje message)
	{
		notify();
		ultimoMensajeEnPos +=1;
		return false;
	}
	public void procesarMensaje()
	{
		Mensaje actual = recuperarMensaje(); // recupera el ultimo mensaje de la lista
		if( ultimoMensajeEnPos == -1)
		{
			try {wait();} catch (InterruptedException e) {e.printStackTrace();}
		}
	}
	public synchronized Mensaje recuperarMensaje()
	{
		Mensaje recuperado = mensajes[ultimoMensajeEnPos];
		return recuperado;
	}
	
	
	public ServidorBuffer(int numServidores, int numMaxClientesPosibles)
	{
		
		mensajes = new Mensaje [numMaxClientesPosibles];
		// crea el pool de threads servidores que quedan a la espera de mensajes

		pool = new ThreadServidor [numServidores];
		for(int i=0; i< pool.length ;i++)
		{
			pool[i] = new ThreadServidor(this);
			pool[i].start();
		}
		ultimoMensajeEnPos = -1;

	}


}
