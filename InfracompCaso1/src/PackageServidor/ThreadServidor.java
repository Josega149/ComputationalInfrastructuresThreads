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
			boolean hayMensajes = servidor.procesarMensaje();
			if(!hayMensajes)
			{
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}


}
