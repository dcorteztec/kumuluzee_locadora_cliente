package br.com.marteleto.framework.web.listener.abstracts;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.marteleto.framework.core.listener.abstracts.AListener;

public abstract class AServletContextListener extends AListener implements ServletContextListener {
	private static final long serialVersionUID = 1L;
	
	protected void init(ServletContextEvent event) {
	}
	
	public void	contextInitialized(ServletContextEvent event) {
		this.initListener();
		init(event);
	}

	public void	contextDestroyed(ServletContextEvent event) {
		this.finishListener();
	}
}
