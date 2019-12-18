
package com.asset.management.VO;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ResponseVO {
	private String status;
	private String message;
	private String errorcode;
}

