package com.twitter.hbc.httpclient;

import com.google.common.base.Optional;
import com.twitter.hbc.httpclient.auth.Authentication;
import java.net.UnknownHostException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.params.HttpParams;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestartableHttpClientTest {
  private Authentication mockAuth;
  private SchemeRegistry defaultSchemeRegistry;
  private HttpParams mockParams;
  private HttpUriRequest request;

  @Before
  public void setup() throws Exception {
    mockAuth = mock(Authentication.class);
    when(mockAuth.getUsernameAndPassword()).thenReturn(Optional.<UsernamePasswordCredentials>absent());
    mockParams = mock(HttpParams.class);
    defaultSchemeRegistry = SchemeRegistryFactory.createDefault();
    request = new HttpGet("http://hi");
  }

  @Test
  public void testRestart() throws Exception {
    RestartableHttpClient client = new RestartableHttpClient(mockAuth, true, mockParams, defaultSchemeRegistry);
    client.setup();
    client.restart();
    try {
      client.execute(request); // used to crash, https://github.com/twitter/hbc/issues/113
      fail("should not reach here");
    } catch (UnknownHostException e) {
      // expected
    }
  }
}
