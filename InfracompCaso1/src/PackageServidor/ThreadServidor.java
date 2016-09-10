package PackageServidor;

public class ThreadServidor extends Thread
{
	/**
	 * Clase que atiende los mensajes del buffer
	 * 
	 */
	
	private ServidorBuffer servidor;
	
	public ThreadServidor(ServidorBuffer server)
	{
		servidor = server;
	}
	
	public void run()
	{
		while(true)
		{
			servidor.procesarMensaje();
			
		}
	}


}
