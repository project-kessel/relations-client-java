import api.check.v1.CheckGrpc;
import api.check.v1.CheckRequest;
import api.check.v1.CheckResponse;
import api.relations.v1.*;
import client.CheckClient;
import client.Config;
import client.RelationTuplesClient;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import org.jboss.weld.bootstrap.spi.BeanDiscoveryMode;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Use Weld as a test container to check CDI functionality.
 */
@EnableWeld
public class CDIManagedClientsTest {
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(new Weld().setBeanDiscoveryMode(BeanDiscoveryMode.ALL).addBeanClass(TestConfig.class)).build();

    private static final int testServerPort = 7000;

    @Inject
    CheckClient checkClient;

    @Inject
    RelationTuplesClient relationTuplesClient;

    private static Server grpcServer;

    /*
     Start a grpcServer with the following services added and some custom responses that we can check for in the tests.
     */
    @BeforeAll
    static void setup() throws IOException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(testServerPort);
        serverBuilder.addService(new CheckGrpc.CheckImplBase() {
            @Override
            public void check(CheckRequest request, StreamObserver<CheckResponse> responseObserver) {
                responseObserver.onNext(CheckResponse.newBuilder().setAllowed(CheckResponse.Allowed.ALLOWED_TRUE).build());
                responseObserver.onCompleted();
            }
        });
        serverBuilder.addService(new RelationshipsGrpc.RelationshipsImplBase() {
            @Override
            public void readRelationships(ReadRelationshipsRequest request, StreamObserver<ReadRelationshipsResponse> responseObserver) {
                responseObserver.onNext(ReadRelationshipsResponse.newBuilder().addRelationships(
                        Relationship.newBuilder().setObject(ObjectReference.newBuilder().setType("TestType").build()).build()
                ).build());
                responseObserver.onCompleted();
            }
        });
        grpcServer = serverBuilder.build();
        grpcServer.start();
    }

    @AfterAll
    static void tearDown() {
        if (grpcServer != null) {
            grpcServer.shutdownNow();
        }
    }

    @Test
    public void basicCDIWiringTest() {
        var checkResponse = checkClient.check(CheckRequest.getDefaultInstance());
        var relationTuplesResponse = relationTuplesClient.readRelationships(ReadRelationshipsRequest.getDefaultInstance());

        assertEquals(CheckResponse.Allowed.ALLOWED_TRUE, checkResponse.getAllowed());
        assertEquals("TestType", relationTuplesResponse.getRelationships(0).getObject().getType());
    }

    /*
     Implementation of Config to inject manually with hardcoded settings.
     */
    static class TestConfig implements Config {
        @Override
        public boolean isSecureClients() {
            return false;
        }

        @Override
        public String targetUrl() {
            return "0.0.0.0:" + String.valueOf(testServerPort);
        }
    }
}
