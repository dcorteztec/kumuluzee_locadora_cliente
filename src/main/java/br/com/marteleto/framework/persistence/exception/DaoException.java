package br.com.marteleto.framework.persistence.exception;

import br.com.marteleto.framework.persistence.util.PesistenceMessageCode;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = PesistenceMessageCode.PERSISTENCE_MESSAGE_0001;
	
	public DaoException() {
        super();
        processException(null,null);
    }
	
	public DaoException(String message) {
        super(message);
        processException(message,null);
    }
    
	public DaoException(String message, Throwable cause) {
		processException(message,cause);
    }

	public DaoException(Throwable cause) {
        super(cause);
        processException(null,cause);
    }
	
	public String getError() {
	    return message;
	}
	
	public String getMessage() {
	    return message;
	}
	
	private void processException(String message, Throwable cause) {
		if (message != null && !"".equals(message.trim())) {
			this.message = message;
		}
	}
}