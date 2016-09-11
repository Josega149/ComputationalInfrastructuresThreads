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





	public synchronized boolean enviarMensaje(Mensaje message)
	{
		if(ultimoMensajeEnPos < mensajes.length)
		{
			notify();
			ultimoMensajeEnPos +=1;
			mensajes[ultimoMensajeEnPos] = message;
			return true;
		}
		else
		{
			return false;
		}
		
	}

	public void procesarMensaje()
	{
		Mensaje actual = recuperarMensaje(); // recupera el ultimo mensaje de la lista
		while( actual == null)
		{
			try {wait();} catch (InterruptedException e) {e.printStackTrace();}
			actual = recuperarMensaje(); // recupera el ultimo mensaje de la lista
		}
		
		actual.modificarMensaje();
		actual.notify();
	}
	public synchronized Mensaje recuperarMensaje()
	{
		if(ultimoMensajeEnPos <0){return null;}
		Mensaje recuperado = mensajes[ultimoMensajeEnPos];
		mensajes[ultimoMensajeEnPos] = null;
		ultimoMensajeEnPos -= 1;
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
