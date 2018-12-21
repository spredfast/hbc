package com.twitter.hbc.httpclient.auth;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

public class OAuth2 implements Authentication {

	private final String accessToken;

	public OAuth2(final String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
	public void setupConnection(final AbstractHttpClient client) {
		client.addRequestInterceptor(new HttpRequestInterceptor() {
			@Override
			public void process(final HttpRequest httpRequest, final HttpContext httpContext) throws HttpException, IOException {
				httpRequest.addHeader("Authorization", String.format("Bearer %s", accessToken.trim()));
			}
		});
	}

	@Override
	public void signRequest(final HttpUriRequest request, final String postContent) { }

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}
}
