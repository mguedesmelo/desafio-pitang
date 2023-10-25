package br.com.car.rental.exception;

public class RecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4000265776953547670L;

	public RecordNotFoundException(Long id) {
		super("Could not find record " + id);
	}
}
