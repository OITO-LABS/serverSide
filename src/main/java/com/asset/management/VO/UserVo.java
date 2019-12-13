package com.asset.management.VO;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.asset.management.dao.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserVo extends User{
	private Long employeeId;
	private Role role;
	public UserVo(String username, String password, Long employeeId,Role role,Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.employeeId=employeeId;
		this.role=role;
	}

	
}
