package PackageClientes;

import PackageServidor.ServidorBuffer;

public class Cliente extends Thread
{
	private int id;
	
	private int numMensajesAMandar;
	
	private Mensaje [] mensajes;
	
	private ServidorBuffer buffer;
	
	public Cliente(int pid, int numDeMensajesAMandarP)
	{
		numMensajesAMandar = numDeMensajesAMandarP;
		mensajes = new Mensaje [numMensajesAMandar];
		id = pid;
	}
	
	public void run()
	{
		int num = -1;
		while (numMensajesAMandar>0)
		{
			Mensaje mensajito = new Mensaje(id+" "+ num++);
			mensajes[num] = mensajito;
			boolean sePudoMandar = buffer.enviarMensaje(mensajito);
			while (!sePudoMandar)
			{
				yield();
				
				sePudoMandar = buffer.enviarMensaje(mensajito);
			}
			try {
				mensajito.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			numMensajesAMandar--;
			System.out.println(mensajito.darMensaje());
		}
	}

}
