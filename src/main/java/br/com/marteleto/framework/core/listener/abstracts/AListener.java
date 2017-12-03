package br.com.marteleto.framework.core.listener.abstracts;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import br.com.marteleto.framework.core.util.CoreMessageCode;
import br.com.marteleto.framework.core.util.PropertyUtil;
import br.com.marteleto.framework.core.util.ResourceUtil;

public abstract class AListener implements Serializable {
	private static final long serialVersionUID = 1L;
	protected transient Logger LOG = Logger.getLogger(getClass().getName());
	
	protected List<String> getProperties() {
		return null;
	}
	
	protected List<String> getResources() {
		return null;
	}
	
	protected void	initListener() {
		initProperties();
		initResources();
		String[] parameters = {PropertyUtil.getApplication(), PropertyUtil.getVersion()};
		LOG.info("" +
				"\n*************************************************" +
				"\n " + ResourceUtil.getResource(CoreMessageCode.CORE_MESSAGE_0001,parameters).toUpperCase() +
				"\n*************************************************" +
		"");
	}

	protected void	finishListener() {
		String[] parameters = {PropertyUtil.getApplication(), PropertyUtil.getVersion()};
		LOG.info("" +
				"\n*************************************************" +
				"\n " + ResourceUtil.getResource(CoreMessageCode.CORE_MESSAGE_0002,parameters).toUpperCase() +
				"\n*************************************************" +
		"");
	}
	
	protected void initProperties() {
		try {
			if (this.getProperties() != null && this.getProperties().size() > 0) {
				for (String property : this.getProperties()) {
					PropertyUtil.addProperty(property);
				}
			}
		} catch (Exception ex) {
			LOG.severe("Failed to instantiate properties.");
		}
	}
	
	protected void initResources() {
		try {
			if (this.getResources() != null && this.getResources().size() > 0) {
				for (String resource : this.getResources()) {
					ResourceUtil.addResource(resource);
				}
			}
		} catch (Exception ex) {
			LOG.severe("Failed to instantiate resources.");
		}
	}
}