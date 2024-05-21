import api.check.v1.CheckRequest;
import api.relations.v1.ReadRelationshipsRequest;
import client.CheckClient;
import client.Config;
import client.RelationTuplesClient;
import jakarta.inject.Inject;
import org.jboss.weld.bootstrap.spi.BeanDiscoveryMode;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Use Weld as a test container to check CDI functionality.
 */
@EnableWeld
public class CDIManagedClientsTest {
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(new Weld().setBeanDiscoveryMode(BeanDiscoveryMode.ALL).addBeanClass(TestConfig.class)).build();

    @Inject
    CheckClient checkClient;

    @Inject
    RelationTuplesClient relationTuplesClient;

    @Test
    public void basicCDIWiringTestCheckClient() {
        Assertions.assertThrows(io.grpc.StatusRuntimeException.class, () -> {
            checkClient.check(CheckRequest.getDefaultInstance());
        });
    }

    @Test
    public void basicCDIWiringTestReadRelationships() {
        Assertions.assertThrows(io.grpc.StatusRuntimeException.class, () -> {
            relationTuplesClient.readRelationships(ReadRelationshipsRequest.getDefaultInstance());
        });
    }

    static class TestConfig implements Config {
        @Override
        public boolean isSecureClients() {
            return false;
        }

        @Override
        public String targetUrl() {
            return "240.0.0.0:8080"; // does not exist
        }
    }
}
