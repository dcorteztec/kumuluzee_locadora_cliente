package br.com.marteleto.kumuluzee.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;

import br.com.marteleto.framework.web.listener.abstracts.AServletContextListener;

@WebListener
public class ServicoListener extends AServletContextListener {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected List<String> getProperties() {
		List<String> properties = new ArrayList<String>();
		properties.add("remote.properties");
		return properties;
	}
	
	@Override
	protected List<String> getResources() {
		List<String> resources = new ArrayList<String>();
		resources.add("locale.message");
		return resources;
	}
}
