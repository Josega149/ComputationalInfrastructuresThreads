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
	 * Author jg.tamura10
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
	private int termino;

	private synchronized Mensaje agregarRetirarMensajeALaCola(Mensaje message)
	{
		// el acceso a la cola de mensajes debe ser atomico
		// retirar y agregar mensajes debe hacerse sincronizadamente
		if(message != null)
		{
			//va a agregar mensaje
			ultimoMensajeEnPos +=1;
			mensajes[ultimoMensajeEnPos] = message;
		}
		else
		{
			//va a retirar mensaje
			if(ultimoMensajeEnPos ==-1){return null;}
			Mensaje recuperado = mensajes[ultimoMensajeEnPos];
			mensajes[ultimoMensajeEnPos] = null;
			ultimoMensajeEnPos -= 1;
			return recuperado;
		}
		return null;
		
	}
	
	public synchronized boolean enviarMensaje(Mensaje message)
	{
		System.out.println("intenta entrar un mensaje a la cola de mensajes");
		if(ultimoMensajeEnPos < mensajes.length-1)
		{
			//agrega mensaje a la cola
			agregarRetirarMensajeALaCola(message);
			//despierta un thread si hay dormido
			notifyAll();
			
			return true;
		}
		else
		{
			System.out.println(" no logra entrar en la cola de mensajes");
			return false;
		}
		
	}

	public synchronized void procesarMensaje()
	{
		System.out.println("entra a procesar mensaje");
		Mensaje actual = agregarRetirarMensajeALaCola(null); // recupera el ultimo mensaje de la lista
		while( actual == null)
		{	if(termino()){System.out.println("ya no vendran mas hilos, server se retira");return;}
			System.out.println("No hay mensajes que atender, thread server se queda dormido");
			try {wait();} catch (InterruptedException e) {e.printStackTrace();}
			actual = agregarRetirarMensajeALaCola(null); // recupera el ultimo mensaje de la lista
		}
		System.out.println("thread server se despierta con un mensaje para atender");
		actual.modificarMensaje();
		actual.despertar();
	}
	
	public boolean termino(){ return termino==0;}
	public synchronized void terminoElCliente(){termino-=1;if(termino==0){System.out.println("Los clientes terminaron"); notifyAll();}}
	
	public ServidorBuffer(int numServidores, int numMaxClientesPosibles, int numClientes)
	{
		
		mensajes = new Mensaje [numMaxClientesPosibles];
		// crea el pool de threads servidores que quedan a la espera de mensajes

		pool = new ThreadServidor [numServidores];
		for(int i=0; i< pool.length ;i++)
		{
			pool[i] = new ThreadServidor(this, i);
			pool[i].start();
		}
		ultimoMensajeEnPos = -1;
		termino = numClientes;

	}


}
