package usuarios.rest;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import servicio.FactoriaServicios;
import usuarios.modelo.Usuario;
import usuarios.rest.dto.CreateUsuarioDto;
import usuarios.servicio.IServicioUsuarios;

@Path("usuarios")
public class ControladorUsuarios {

	private IServicioUsuarios servicio = FactoriaServicios.getServicio(IServicioUsuarios.class);

	@Context
	private UriInfo uriInfo;

	// curl http://localhost:8080/api/usuarios/juan@um.es

	@GET
	@Path("{email}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("email") String email) throws Exception {

		Usuario usuario = this.servicio.obtenerUsuario(email);
		
		// TODO: definir DTO
		
		return Response.status(Response.Status.OK).entity(usuario).build();
	}

	// curl -i -X PATCH --data "nombre=Juan González"   http://localhost:8080/api/usuarios/juan@um.es
	
	@PATCH
	@Path("/{email}")	
	public Response updateNombre(@PathParam("email") String email, @FormParam("nombre") String nombre) throws Exception {
		
		servicio.modificarNombre(email, nombre);
		
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	// curl -i -X POST -H "Content-type: application/json" -d "{'email': 'juan@um.es', 'nombre': 'Juan'}"  http://localhost:8080/api/usuarios/

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUsuario(CreateUsuarioDto dto) throws Exception {
		
		servicio.crear(dto.getEmail(), dto.getNombre());
		
		URI nuevaURL = this.uriInfo.getAbsolutePathBuilder().path(dto.getEmail()).build();
		return Response.created(nuevaURL).build();
	}


}