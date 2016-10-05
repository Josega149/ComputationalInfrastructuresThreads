package PackageServidor;

public class ThreadServidor extends Thread
{
	/**
	 * Clase que atiende los mensajes del buffer
	 * Author jg.tamura10
	 */
	private int id;
	private ServidorBuffer servidor;
	
	public ThreadServidor(ServidorBuffer server, int idP)
	{
		servidor = server;
		id = idP;
	}
	
	public void run()
	{
		while(!servidor.termino())
		{
			System.out.println("va el hilo: "+id);
			servidor.procesarMensaje();
			
		}
	}


}
