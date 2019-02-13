package io.github.christopheleappdirect.cruduiwithvaadindemo;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
public class OpenIDUserDetails implements UserDetails {

	private String fullname;
	private Set<String> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> "ROLE_" + role)
				.map(SimpleGrantedAuthority::new)
				.collect(toSet());
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return getFullname();
	}
}
