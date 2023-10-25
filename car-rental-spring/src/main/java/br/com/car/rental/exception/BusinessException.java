package br.com.car.rental.exception;

public class BusinessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7006992541930435245L;

	public BusinessException(String message) {
		super(message);
	}
}
