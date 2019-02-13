package io.github.christopheleappdirect.cruduiwithvaadindemo;

import javax.servlet.http.HttpServletRequest;

import org.openid4java.consumer.ConsumerException;
import org.springframework.security.openid.AxFetchListFactory;
import org.springframework.security.openid.OpenID4JavaConsumer;
import org.springframework.security.openid.OpenIDConsumerException;

public class CrudWithVaadinOpenIDConsumer extends OpenID4JavaConsumer {

	private final String openIdProviderUrl;

	public CrudWithVaadinOpenIDConsumer(String openIdProviderUrl, AxFetchListFactory axFetchListFactory) throws ConsumerException {
		super(axFetchListFactory);
		this.openIdProviderUrl = openIdProviderUrl;
	}

	@Override
	public String beginConsumption(HttpServletRequest httpServletRequest, String claimedIdentity, String returnToUrl, String realm) throws OpenIDConsumerException {
		return super.beginConsumption(httpServletRequest, openIdProviderUrl, returnToUrl, realm);
	}
}
