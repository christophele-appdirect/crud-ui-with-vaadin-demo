package io.github.christopheleappdirect.cruduiwithvaadindemo;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

public class CrudWithVaadinUserDetailsService implements AuthenticationUserDetailsService<OpenIDAuthenticationToken> {

	private static final String ROLES = "roles";
	private static final String FULLNAME = "fullname";

	public UserDetails loadUserDetails(OpenIDAuthenticationToken openIDAuthenticationToken) throws UsernameNotFoundException {
		List<OpenIDAttribute> attributes = openIDAuthenticationToken.getAttributes();

		OpenIDUserDetails user = new OpenIDUserDetails();
		user.setFullname(getFullName(attributes));
		user.setRoles(new HashSet<>(getAttributeValues(attributes, ROLES)));

		return user;
	}

	private String getFullName(List<OpenIDAttribute> attributes) {
		return getAttributeValues(attributes, FULLNAME).get(0);
	}

	private List<String> getAttributeValues(List<OpenIDAttribute> attributes, String attributeName) {
		return attributes.stream().filter(a -> attributeName.equals(a.getName())).findFirst()
				.orElseThrow(() -> new InsufficientAuthenticationException(String.format("No attribute %s defined for user.", attributeName)))
				.getValues();
	}
}
