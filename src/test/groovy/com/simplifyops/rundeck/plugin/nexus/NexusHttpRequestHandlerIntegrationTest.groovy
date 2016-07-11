package com.simplifyops.rundeck.plugin.nexus

import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options

/**
 * @author Johannes Graf - graf@synyx.de
 */
class NexusHttpRequestHandlerIntegrationTest {

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(
            options()
                    .port(8080)
                    .usingFilesUnderClasspath("src/test/resources/handleRequest")
    );

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;

    @Test
    public void testHandleRequest() {
        def query = NexusHttpRequestHandler.buildQuery('com.simplifyops.rundeck.plugin', 'nexus', '1.0.1', 'releases', 'jar', null)

        def successHandler = { resp ->
            assert resp.contentType == 'application/java-archive'
            assert resp.status == 200
        }

        NexusHttpRequestHandler.handleRequest('http://localhost:8080', 'john.doe', '123456', query, successHandler)
    }
}
