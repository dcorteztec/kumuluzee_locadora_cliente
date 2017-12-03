package br.com.marteleto.framework.persistence.util;

import java.io.Serializable;

public class PesistenceMessageCode implements Serializable {
	private static final long serialVersionUID = 1L;
	private PesistenceMessageCode(){
	}
	
	public static final String PERSISTENCE_MESSAGE_0001 = "PERSISTENCE_MESSAGE_0001";
	public static final String BUSINESS_MESSAGE_0001 = "BUSINESS_MESSAGE_0001";
}