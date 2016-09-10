package PackageClientes;

public class Mensaje {
	
	private String mensaje;
	
	public Mensaje (String msj)
	{
		mensaje = msj;
	}
	
	public void modificarMensaje ()
	{
		mensaje+=" MODIFICADO";
	}
	
	public String darMensaje()
	{
		return mensaje;
	}
	
	public void momir()
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
