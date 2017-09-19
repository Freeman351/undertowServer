package com.freenan.undertow.undertow.server;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class AppHttpHandler implements HttpHandler{

    public void handleRequest(final HttpServerExchange exchange)
            throws Exception {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,
                "text/plain");
        exchange.getResponseSender().send("Hello World");
    }

}
