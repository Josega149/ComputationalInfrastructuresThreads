package PackageClientes;

public class Mensaje {
	
	private String mensaje;
	
	public Mensaje (String msj)
	{
		mensaje = msj;
	}
	public synchronized void dormir()
	{
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void despertar()
	{
		System.out.println(" CLIENTE SE DESPIERTAAAA ");
		notify();
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
