package org.project_kessel.relations.client;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;

/**
 * Interface for injecting config into container managed beans.
 * It has the current limitation that only one underlying grpc connection can be configured.
 * Does nothing if this client is not being managed by a container.
 * Works directly for Quarkus. Might need an implementation class for future Spring Boot config.
 */
@ConfigMapping(prefix = "relations-api")
public interface Config {
    @WithDefault("false")
    boolean isSecureClients();
    String targetUrl();
}
