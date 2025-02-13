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

Let's say we want to check whether a subject of type "principal" (in the "rbac" type namespace) with id "bob" has "view" permission to a resource of type "widget" (in the "rbac" type namespace) and id "my_thing" with a consistency of minimize latency. We can make the following synchronous check request:

```java
var checkRequest = CheckRequest.newBuilder()
        .setSubject(SubjectReference.newBuilder()
                .setSubject(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("rbac")
                                .setName("principal").build())
                        .setId("bob").build())
                .build())
        .setRelation("view")
        .setResource(ObjectReference.newBuilder()
                .setType(ObjectType.newBuilder()
                        .setNamespace("rbac")
                        .setName("widget").build())
                .setId("my_thing")
                .build())
        .setConsistency(Consistency.newBuilder()
                .setMinimizeLatency(true)
                .build())
        .build();

var checkResponse = checkClient.check(checkRequest);
var permitted = checkResponse.getAllowed() == CheckResponse.Allowed.ALLOWED_TRUE;
```

#### Asynchronous reactive example (using Mutiny)

Let's say we want to look up all of the resources of type "widget" (in the "rbac" type namespace) that a subject of type "principal" (in the "rbac" type namespace) and id "bob" has "view" permission on with a consistency of minimize latency. Since there may be many responses, we might want to operate on them asynchronously as they come in, but we may also want to collect them all afterwards and perform a synchronous operation on the list. We can make the following request using the mutiny reactive programming API to achieve both:

```java
var lookupResourcesRequest = LookupResourcesRequest.newBuilder()
        .setResourceType(ObjectType.newBuilder()
                .setNamespace("rbac").setName("widget"))
        .setRelation("view").setSubject(SubjectReference.newBuilder()
                .setSubject(ObjectReference.newBuilder()
                        .setType(ObjectType.newBuilder()
                                .setNamespace("rbac")
                                .setName("principal").build())
                        .setId("bob").build())
                .build())
                .setConsistency(Consistency.newBuilder()
                        .setMinimizeLatency(true)
                        .build())
                .build();

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

## Code Style

We are using a customized code style that builds off of Checkstyle's [official implementation of the Google Java style guide](https://checkstyle.sourceforge.io/google_style.html) at [`google_checks.xml`](https://github.com/project-kessel/relations-client-java/blob/main/google_checks.xml). The Checkstyle check will automatically run apart of PR pipelines and when using `./mvnw clean verify -Dgpg.skip`.

### IDE Integration

#### Intellij

It is recommended to use the [Checkstyle plugin](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) to automatically highlight checkstyle violations and reformat code.

#### VSCode

It is recommended to use the [Checkstyle plugin](https://marketplace.visualstudio.com/items?itemName=shengchen.vscode-checkstyle) to automatically highlight checkstyle violations while coding.

