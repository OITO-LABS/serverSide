
package com.asset.management.VO;

import java.util.Date;


import com.asset.management.dao.entity.Employee;
import com.asset.management.dao.entity.Role;
import com.asset.management.dao.entity.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginVo {
	private String username;
	private String password;
	private Status status;
	private String token;
	private Date loginTime;
	private Date logoutTime;
	private Role role;
	private Long employeeId;
	private ResponseVO response;
}

