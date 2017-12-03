package br.com.marteleto.kumuluzee.servico;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.kumuluz.ee.logs.cdi.Log;
import com.kumuluz.ee.logs.cdi.LogParams;

import br.com.marteleto.framework.core.util.CoreMessageCode;
import br.com.marteleto.framework.core.util.PropertyUtil;
import br.com.marteleto.framework.core.util.ResourceUtil;
import br.com.marteleto.framework.service.service.abstracts.AService;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Log(LogParams.METRICS)
@Path("/")
public class WorkingService extends AService {
	private static final long serialVersionUID = 1L;
	
	@GET
	@Path("/")
	public Response serviceIsWorking() throws Exception {
		String[] parameters = {PropertyUtil.getApplication(), PropertyUtil.getVersion()};
		return Response.ok(ResourceUtil.getResource(CoreMessageCode.CORE_MESSAGE_0003,parameters).toUpperCase()).build();
	}
}
