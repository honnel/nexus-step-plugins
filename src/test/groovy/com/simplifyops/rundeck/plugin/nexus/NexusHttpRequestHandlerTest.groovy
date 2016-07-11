package com.simplifyops.rundeck.plugin.nexus

import org.junit.Test

/**
 * @author Johannes Graf - graf@synyx.de
 */
class NexusHttpRequestHandlerTest {

    @Test
    public void testBuildQuery() {
        def query = NexusHttpRequestHandler.buildQuery('com.simplifyops.rundeck.plugin', 'nexus', '1.0.1', 'releases', 'jar', null)

        assert query.a == 'nexus'
        assert query.g == 'com.simplifyops.rundeck.plugin'
        assert query.p == 'jar'
        assert query.r == 'releases'
        assert query.v == '1.0.1'
        assert query.c == null
    }

    @Test
    public void testBuildQueryWithOptionalClassifier() {
        def query = NexusHttpRequestHandler.buildQuery('com.simplifyops.rundeck.plugin', 'nexus', '1.0.1', 'releases', 'jar', 'classifier')

        assert query.a == 'nexus'
        assert query.g == 'com.simplifyops.rundeck.plugin'
        assert query.p == 'jar'
        assert query.r == 'releases'
        assert query.v == '1.0.1'
        assert query.c == 'classifier'
    }
}
