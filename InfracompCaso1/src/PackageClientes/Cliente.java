package PackageClientes;

import PackageServidor.ServidorBuffer;

public class Cliente extends Thread
{
	private int id;

	private int numMensajesAMandar;

	private Mensaje [] mensajes;

	private ServidorBuffer buffer;

	public Cliente(int pid, int numDeMensajesAMandarP, ServidorBuffer buf)
	{
		buffer = buf;
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
			mensajito.dormir();
			numMensajesAMandar--;
			System.out.println(mensajito.darMensaje());
		}
		System.out.println("EL CLIENTE SE SALE!");
		buffer.terminoElCliente();
	}

}
