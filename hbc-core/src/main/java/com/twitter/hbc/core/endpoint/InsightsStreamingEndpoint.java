package com.twitter.hbc.core.endpoint;

import com.twitter.hbc.core.HttpConstants;

/**
 * See http://support.gnip.com/apis/p/insightstrack/api_reference.html#GET
 */
public abstract class InsightsStreamingEndpoint implements StreamingEndpoint {

	// /stream/insightstrack/accounts/{account-name}/publishers/twitter/{stream-label}.json
	private static final String BASE_PATH = "/stream/insightstrack/accounts/%s/publishers/twitter/%s.json";

	protected final String account;
	protected final String label;

	public InsightsStreamingEndpoint(final String account, final String label) {
		this.account = account;
		this.label = label;
	}

	@Override
	public String getURI() {
		return String.format(BASE_PATH, account.trim(), label.trim());
	}

	@Override
	public String getHttpMethod() {
		return HttpConstants.HTTP_GET;
	}

	@Override
	public String getPostParamString() {
		return null;
	}

	@Override
	public String getQueryParamString() {
		return null;
	}

	// The following methods do nothing.
	@Override
	public void setBackfillCount(final int count) { }

	@Override
	public void setApiVersion(final String apiVersion) { }

	@Override
	public void addPostParameter(final String param, final String value) { }

	@Override
	public void removePostParameter(final String param) { }

	@Override
	public void addQueryParameter(final String param, final String value) { }

	@Override
	public void removeQueryParameter(final String param) { }
}
