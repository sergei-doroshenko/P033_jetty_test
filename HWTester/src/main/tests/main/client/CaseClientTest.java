package main.client;

import base.CaseClient;
import base.CaseConfig;
import base.CaseServer;
import main.CaseProcessor;
import main.server.CaseServerImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author v.chibrikov
 */
public class CaseClientTest {
    @Test
    public void mirrorWebServerTest() {
        CaseConfig cfg = mock(CaseConfig.class);
        when(cfg.getStartWaitPeriod()).thenReturn(10000);
        when(cfg.getHost()).thenReturn("localhost");
        when(cfg.getPort()).thenReturn("8080");
        when(cfg.getServerStartCommand()).thenReturn("java -jar src/main/tests/samples/mirror.jar");
        when(cfg.getStartedMessage()).thenReturn("Server started");
        when(cfg.getCaseClass()).thenReturn("testCases.testExamples.MirrorHomeWork");
        when(cfg.getArgs()).thenReturn(new String[]{"hello"});

        CaseClient caseClient = new CaseClientImpl(cfg);
        CaseServer caseServer = new CaseServerImpl(cfg);

        CaseProcessor caseProcessor = new CaseProcessor(caseServer, caseClient);
        boolean result = caseProcessor.process();

        Assert.assertTrue(result);
    }
}
