package io.github.christopheleappdirect.cruduiwithvaadindemo;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.List;

import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenIDAttribute;

public class CrudWithVaadinAxFetchListFactory implements AxFetchListFactory {

	private final List<OpenIDAttribute> openIDAttributes;

	public CrudWithVaadinAxFetchListFactory() {
		OpenIDAttribute rolesAttribute = new OpenIDAttribute("roles", "https://www.appdirect.com/schema/user/roles");
		rolesAttribute.setRequired(true);
		rolesAttribute.setCount(100);
		OpenIDAttribute fullnameAttribute = new OpenIDAttribute("fullname", "http://axschema.org/namePerson");
		fullnameAttribute.setRequired(true);
		openIDAttributes = unmodifiableList(asList(rolesAttribute, fullnameAttribute));
	}

	@Override
	public List<OpenIDAttribute> createAttributeList(String identifier) {
		return openIDAttributes;
	}
}
