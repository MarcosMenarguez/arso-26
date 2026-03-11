package usuarios.servicio.test;

import servicio.FactoriaServicios;
import usuarios.servicio.IServicioUsuarios;


public class Programa {

	public static void main(String[] args) throws Exception {
		
				
		IServicioUsuarios servicio = FactoriaServicios.getServicio(IServicioUsuarios.class);
		
		servicio.crear("juan@um.es", "Juan"); 
		
		System.out.println(servicio.obtenerUsuario("juan@um.es"));
		
		servicio.modificarNombre("juan@um.es", "Juan González");
		
		System.out.println(servicio.obtenerUsuario("juan@um.es"));
		
		System.out.println("Fin.");
	}
}
