package PackageClientes;

import PackageServidor.ServidorBuffer;

public class ClienteManager
{
	private Cliente [] listaClientes;
	

	public ClienteManager(int numClientes, int [] numMensajesPorCliente, ServidorBuffer bufer)
	{
		listaClientes = new Cliente [numClientes];
		for(int i=0; i< numClientes; i++)
		{
			listaClientes[i] = new Cliente(i, numMensajesPorCliente[i], bufer);
			listaClientes[i].start();
		}
	}

}
