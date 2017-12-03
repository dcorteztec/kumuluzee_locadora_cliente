package br.com.marteleto.kumuluzee.servico;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;

import br.com.marteleto.framework.service.service.abstracts.AService;
import br.com.marteleto.locadora.business.interfaces.IClienteBusiness;
import br.com.marteleto.locadora.filtro.PadraoFiltro;
import br.com.marteleto.locadora.vo.ClienteVO;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cliente")
@Log(LogParams.METRICS)
public class ClienteService extends AService {
	private static final long serialVersionUID = 1L;
	@Inject private IClienteBusiness clienteBusiness;
	@Context private UriInfo uriInfo;
	
	@POST
    @Path("/")
	public Response save(ClienteVO object) throws Exception {
		try {
			clienteBusiness.save(object);
    		return Response.ok(Boolean.TRUE).build();
    	} catch (Exception ex) {
    		return exceptionProcess(ex);
		}
	}

	@DELETE
    @Path("/{id}")
	public Response deleteClientePorId(@PathParam("id") Long id) throws Exception {
		try {
			clienteBusiness.deleteClientePorId(id);
    		return Response.ok(Boolean.TRUE).build();
    	} catch (Exception ex) {
    		return exceptionProcess(ex);
		}
	}
	
	@GET
	@Path("/{id}")
	public Response recuperarClientePorId(@PathParam("id") Long id) throws Exception {
		try {
			ClienteVO object = clienteBusiness.recuperarClientePorId(id);
    		if (object != null) {
    			return Response.ok(object).build();
    		} else {
    			return Response.status(Response.Status.NOT_FOUND).build();    			
    		}
    	} catch (Exception ex) {
    		return exceptionProcess(ex);
		}
	}
	
	@GET
    @Path("/listar")
	public Response buscarUsuariosPorFiltro(PadraoFiltro filtro) throws Exception {
		try {
	        List<ClienteVO> list = clienteBusiness.buscarClientePorFiltro(filtro);
    		if (list != null && !list.isEmpty()) {
    			return Response.ok(list).build();
    		} else {
    			return Response.status(Response.Status.NOT_FOUND).build();    			
    		}
    	} catch (Exception ex) {
    		return exceptionProcess(ex);
		}
	}

	@GET
    @Path("/contar")
	public Response contarUsuariosPorFiltro(PadraoFiltro filtro) throws Exception {
		try {
			Integer count = clienteBusiness.contarClientePorFiltro(filtro);
    		if (count != null) {
    			return Response.ok(count).build();
    		} else {
    			return Response.status(Response.Status.NOT_FOUND).build();    			
    		}
    	} catch (Exception ex) {
    		return exceptionProcess(ex);
		}
	}
}
