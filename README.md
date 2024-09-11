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
#### Synchronous example

Let's say we want to check whether a subject of type "user" (in the "rbac" type namespace) with id "bob" has "view" permission to a resource of type "thing" (in the "rbac" type namespace) and id "my_thing". We can make the following synchronous check request:

```java
var checkRequest = CheckRequest.newBuilder()
        .setSubject(SubjectReference.newBuilder()
                .setSubject(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("rbac")
                                .setName("user").build())
                        .setId("bob").build())
                .build())
        .setRelation("view")
        .setResource(ObjectReference.newBuilder()
                .setType(ObjectType.newBuilder()
                        .setNamespace("rbac")
                        .setName("thing").build())
                .setId("my_thing")
                .build())
        .build();

var checkResponse = checkClient.check(checkRequest);
var permitted = checkResponse.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;
```

#### Asynchronous reactive example (using Mutiny)

Let's say we want to look up all of the resources of type "thing" (in the "rbac" type namespace) that a subject of type "user" (in the "rbac" type namespace) and id "bob" has "view" permission on. Since there may be many responses, we might want to operate on them asynchronously as they come in, but we may also want to collect them all afterwards and perform a synchronous operation on the list. We can make the following request using the mutiny reactive programming API to achieve both:

```java
var lookupResourcesRequest = LookupResourcesRequest.newBuilder()
        .setResourceType(ObjectType.newBuilder()
                .setNamespace("rbac").setName("thing"))
        .setRelation("view").setSubject(SubjectReference.newBuilder()
                .setSubject(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("rbac")
                                .setName("user").build())
                        .setId("bob").build())
                .build()).build();

Multi<LookupResourcesResponse> multi = lookupClient.lookupResourcesMulti(lookupResourcesRequest);

/* Pattern where we may want collect all the responses, but still operate on each as it comes in. */
List<LookupResourcesResponse> responses = multi.onItem()
        .invoke(response -> {
            var tuple = response.getResource();
            System.out.println("We can do something async here for each tuple: " + tuple);
        })
        .collect().asList().await().indefinitely();

System.out.println("We can then wait here synchronously for the whole list if we want to: " + responses);
```

## Build
This is a maven build, so do the usual things, e.g.
```
./mvnw clean package
```
for a jar.
