package PackageClientes;

public class Mensaje {
	
	private int mensaje;
	
	public Mensaje (int msj)
	{
		mensaje = msj;
	}
	
	public void modificarMensaje ()
	{
		mensaje++;
	}
	
	public int darMensaje()
	{
		return mensaje;
	}
	
	public void dormir()
	{
		try 
		{
			wait();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
