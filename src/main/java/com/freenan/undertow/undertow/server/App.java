package com.freenan.undertow.undertow.server;

import io.undertow.Undertow;
 
/**
 * Hello world!
 * 
 */
public class App {
    public static void main(final String[] args) {
        Undertow server = Undertow.builder().addHttpListener(8080, "localhost")
                .setHandler(new AppHttpHandler()).build();
 
        server.start();
    }
    
}