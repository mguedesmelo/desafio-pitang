package br.com.car.rental.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMessage {
	private int errorCode;
    private String message;
}
