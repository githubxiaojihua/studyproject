package com.xiaojihua.enterpriseJ2EE.network.nio.bealdung;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static junit.framework.TestCase.assertEquals;

/**
 * 测试用例
 */
public class EchoTest {
    Process server;
    EchoClient client;

    @Before
    public void setup() throws IOException, InterruptedException {
        //这里没有使用server的start方法，而是直接运行其main
        //server = EchoServer.start();
        client = EchoClient.start();
    }

    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrect() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        assertEquals("hello", resp1);
        assertEquals("world", resp2);
    }

    @After
    public void teardown() throws IOException {
        server.destroy();
        EchoClient.stop();
    }
}
