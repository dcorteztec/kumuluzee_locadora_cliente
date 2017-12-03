package br.com.marteleto.framework.persistence.exception;

import br.com.marteleto.framework.persistence.util.PesistenceMessageCode;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message = PesistenceMessageCode.BUSINESS_MESSAGE_0001;
	
	public BusinessException() {
        super();
        processException(null,null);
    }
	
	public BusinessException(String message) {
        super(message);
        processException(message,null);
    }
    
	public BusinessException(String message, Throwable cause) {
        super(message, cause);
        processException(message,cause);
    }

	public BusinessException(Throwable cause) {
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
		} else {
			if (cause instanceof DaoException) {
				if (cause.getMessage() != null && !"".equals(cause.getMessage().trim())) {
					this.message = cause.getMessage();
				}
			}
		}
	}
}