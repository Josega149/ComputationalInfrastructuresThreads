package PackageClientes;

public class ClienteManager
{
	private Cliente [] listaClientes;
	

	public ClienteManager(int numClientes, int [] numMensajesPorCliente)
	{
		for(int i=0; i< numClientes; i++)
		{
			listaClientes[i] = new Cliente(numMensajesPorCliente[i]);
			listaClientes[i].start();
		}
	}

}
