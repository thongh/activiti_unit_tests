package org.activiti.spring;

import java.io.Serializable;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class BookOrder implements Serializable {
	
//	Logger rootLogger = LogManager.getLogManager().getLogger("");
	
	private int isbn;
	
	private static final long serialVersionUID = 1L;
	
	public boolean validate(int isbn) {
//		rootLogger.info("Validating isbn " + isbn);
		return true;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

}
