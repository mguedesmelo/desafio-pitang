package br.com.car.rental.exception;

import lombok.Getter;

@Getter
public class ResponseMessage {
	private int errorCode;
    private String message;

	public ResponseMessage(int errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("{\"errorCode\":%s,\"message\":\"%s\"}", errorCode, message);
	}
}
