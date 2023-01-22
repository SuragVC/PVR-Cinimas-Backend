package com.pvr.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pvr.entity.Admin;
import com.pvr.entity.Customer;
import com.pvr.entity.UserInterface;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private UserInterface user;

	public SecurityUser(UserInterface user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> grantedAuthos = new ArrayList<>();
		
		if(user instanceof Admin) {
			Admin admin = (Admin)user;
			SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(admin.getRole());
			grantedAuthos.add(grantAuth);
			return grantedAuthos;
		}else {
			Customer customer = (Customer)user;
			SimpleGrantedAuthority grantAuth = new SimpleGrantedAuthority(customer.getRole());
			grantedAuthos.add(grantAuth);
			return grantedAuthos;
			
		}
	}

	@Override
	public String getPassword() {
		
		if(user instanceof Admin) {
			Admin admin = (Admin)user;
			return admin.getPassword();
		}else{
			Customer customer = (Customer)user;
			return customer.getPassword();
		}
	}

	@Override
	public String getUsername() {
		
		if(user instanceof Admin) {
			Admin admin = (Admin)user;
			return admin.getEmail();
		}else{
			Customer customer = (Customer)user;
			return customer.getEmail();
		}
			
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
