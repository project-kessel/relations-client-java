# relations-client-java
Java client library for the relations api.

This library wraps the grpc stubs and channels and provides individual clients for the relations grpc services. A ```RelationsGrpcClientManager``` manages the grpc connection and provides the per-service clients.

## Usage
### CDI / Quarkus

```java
@Inject
CheckClient checkClient;
```
Quarkus configuration: `application.properties` (also containers using smallrye)
```application.properties
relations-api.target-url=localhost:9000
relations-api.is-secure-clients=false
```
For non-quarkus/smallrye CDI, `@Config config` must be provided using custom wiring.

Note: if you want more than one connection configured, you need to use the RelationsGrpcClientsManager
directly (see below).

### Vanilla java

```java
var clientsManager = RelationsGrpcClientsManager.forInsecureClients(url);
var checkClient = clientsManager.getCheckClient();
```
### Making requests
Synchronous:
```java
var checkRequest = CheckRequest.newBuilder()
                .setSubject(SubjectReference.newBuilder()
                                .setObject(ObjectReference.newBuilder()
                                        .setType("user")
                                        .setId("joe").build())
                                .build())
                .setRelation("view")
                .setObject(ObjectReference.newBuilder()
                        .setType("thing")
                        .setId("resource")
                        .build())
                .build();

var checkResponse = checkClient.check(checkRequest);
var permitted = checkResponse.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;
```

Asynchronous reactive (using Mutiny):

```java
Uni<CheckResponse> uni = checkClient.checkUni(checkRequest);

uni.onItem().invoke(() -> {
                if(permitted) {
                    System.out.println("Reactive non-blocking: Permitted");
                } else {
                    System.out.println("Reactive non-blocking: Denied");
                }
            });
```

## Build
This is a maven build, so do the usual things, e.g.
```
./mvnw clean package
```
for a jar.
