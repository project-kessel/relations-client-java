package org.project_kessel.clients.fake;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Super-fake Idp that supports a hard-coded well-known discovery endpoint and a corresponding fake token endpoint.
 * Does not use TLS.
 */
public class FakeIdp {
    private final int port;
    private final boolean alwaysSucceedOrFailAuthn;
    HttpServer server = null;

    public FakeIdp(int port) {
        this(port, true);
    }

    public FakeIdp(int port, boolean alwaysSucceedOrFailAuthn) {
        this.port = port;
        this.alwaysSucceedOrFailAuthn = alwaysSucceedOrFailAuthn;
    }

    public void start() {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.createContext("/.well-known/openid-configuration", new WellKnownHandler());
        if(alwaysSucceedOrFailAuthn) {
            server.createContext("/token", new TokenHandler());
        } else {
            server.createContext("/token", new UnauthorizedHandler());
        }

        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public void stop() {
        server.stop(0);
    }

    static class TokenHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "{\n" +
                    "      \"iss\": \"http://localhost:8090/\",\n" +
                    "      \"aud\": \"us\",\n" +
                    "      \"sub\": \"usr_123\",\n" +
                    "      \"scope\": \"read write\",\n" +
                    "      \"iat\": 1458785796,\n" +
                    "      \"exp\": 1458872196,\n" +
                    "      \"token_type\": \"Bearer\",\n" +
                    "      \"access_token\": \"blah\"\n" +
                    "}";
            t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class UnauthorizedHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "{\"error_description\":\"Access denied by resource owner or authorization server\",\"error\":\"access_denied\"}";

            // https://openid.net/specs/openid-connect-core-1_0.html#TokenEndpoint (3.1.3.4. Token Error Response)
            t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            t.sendResponseHeaders(400, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class WellKnownHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "{\n" +
                    "\t\"issuer\":\"http://localhost:8090\",\n" +
                    "\t\"authorization_endpoint\":\"http://localhost:8090/protocol/openid-connect/auth\",\n" +
                    "\t\"token_endpoint\":\"http://localhost:8090/token\",\n" +
                    "\t\"introspection_endpoint\":\"http://localhost:8090/token/introspect\",\n" +
                    "\t\"jwks_uri\":\"http://localhost:8090/certs\",\n" +
                    "\t\"response_types_supported\":[\"code\",\"none\",\"id_token\",\"token\",\"id_token token\",\"code id_token\",\"code token\",\"code id_token token\"],\n" +
                    "\t\"token_endpoint_auth_methods_supported\":[\"private_key_jwt\",\"client_secret_basic\",\"client_secret_post\",\"tls_client_auth\",\"client_secret_jwt\"],\n" +
                    "\t\"subject_types_supported\":[\"public\",\"pairwise\"]\n" +
                    "}";
            t.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
