package com.projeto.course.entities.enums;

import org.hibernate.boot.model.naming.IllegalIdentifierException;

public enum OrderStatus {

	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERD(4),
	CANCELED(5);
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	//retornando o nome do enum atraves do codigo
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus Code");
	}
}
