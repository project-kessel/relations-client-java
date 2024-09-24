package org.project_kessel.relations.client;


import static org.junit.jupiter.api.Assertions.assertEquals;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Inject;
import java.io.IOException;
import java.util.Optional;
import org.jboss.weld.bootstrap.spi.BeanDiscoveryMode;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.project_kessel.api.relations.v1beta1.CheckRequest;
import org.project_kessel.api.relations.v1beta1.CheckResponse;
import org.project_kessel.api.relations.v1beta1.KesselCheckServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselLookupServiceGrpc;
import org.project_kessel.api.relations.v1beta1.KesselTupleServiceGrpc;
import org.project_kessel.api.relations.v1beta1.LookupSubjectsRequest;
import org.project_kessel.api.relations.v1beta1.LookupSubjectsResponse;
import org.project_kessel.api.relations.v1beta1.ObjectReference;
import org.project_kessel.api.relations.v1beta1.ObjectType;
import org.project_kessel.api.relations.v1beta1.ReadTuplesRequest;
import org.project_kessel.api.relations.v1beta1.ReadTuplesResponse;
import org.project_kessel.api.relations.v1beta1.Relationship;
import org.project_kessel.api.relations.v1beta1.SubjectReference;


/**
 * Use Weld as a test container to check CDI functionality.
 */
@EnableWeld
class CDIManagedRelationsClientsContainerTests {
    private static final int testServerPort = 7000;
    private static Server grpcServer;
    @WeldSetup
    public WeldInitiator weld = WeldInitiator.from(new Weld().setBeanDiscoveryMode(BeanDiscoveryMode.ALL)
            .addBeanClass(TestRelationsConfig.class)).build();
    @Inject
    CheckClient checkClient;
    @Inject
    RelationTuplesClient relationTuplesClient;
    @Inject
    LookupClient lookupClient;

    /*
     Start a grpcServer with the following services added and some custom responses that we can check for in the tests.
     */
    @BeforeAll
    static void setup() throws IOException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(testServerPort);

        serverBuilder.addService(new KesselCheckServiceGrpc.KesselCheckServiceImplBase() {
            @Override
            public void check(CheckRequest request, StreamObserver<CheckResponse> responseObserver) {
                responseObserver.onNext(CheckResponse.newBuilder().setAllowed(CheckResponse.Allowed.ALLOWED_TRUE)
                        .build());
                responseObserver.onCompleted();
            }
        });
        serverBuilder.addService(new KesselTupleServiceGrpc.KesselTupleServiceImplBase() {
            @Override
            public void readTuples(ReadTuplesRequest request, StreamObserver<ReadTuplesResponse> responseObserver) {
                responseObserver.onNext(ReadTuplesResponse.newBuilder().setTuple(
                                Relationship.newBuilder().setResource(
                                                ObjectReference.newBuilder().setType(
                                                                ObjectType.newBuilder().setName("TestType"))
                                                        .build())
                                        .build())
                        .build());
                responseObserver.onCompleted();
            }
        });
        serverBuilder.addService(new KesselLookupServiceGrpc.KesselLookupServiceImplBase() {
            @Override
            public void lookupSubjects(LookupSubjectsRequest request,
                                       StreamObserver<LookupSubjectsResponse> responseObserver) {
                responseObserver.onNext(LookupSubjectsResponse.newBuilder().setSubject(
                                SubjectReference.newBuilder().setSubject(
                                                ObjectReference.newBuilder().setId("TestSubjectId")
                                                        .build())
                                        .build())
                        .build());
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
    void basicCDIWiringTest() {
        /* Make some calls to dummy services in test grpc server to test injected clients */
        var checkResponse = checkClient.check(CheckRequest.getDefaultInstance());
        var relationTuplesResponse = relationTuplesClient.readTuples(ReadTuplesRequest.getDefaultInstance());
        var lookupResponse = lookupClient.lookupSubjects(LookupSubjectsRequest.getDefaultInstance());

        assertEquals(CheckResponse.Allowed.ALLOWED_TRUE, checkResponse.getAllowed());
        assertEquals("TestType", relationTuplesResponse.next().getTuple().getResource().getType().getName());
        assertEquals("TestSubjectId", lookupResponse.next().getSubject().getSubject().getId());
    }

    /*
     Implementation of RelationsConfig to inject manually with hardcoded settings.
     */
    static class TestRelationsConfig implements RelationsConfig {
        @Override
        public boolean isSecureClients() {
            return false;
        }

        @Override
        public String targetUrl() {
            return "0.0.0.0:" + String.valueOf(testServerPort);
        }

        @Override
        public Optional<AuthenticationConfig> authenticationConfig() {
            return Optional.empty();
        }
    }
}
