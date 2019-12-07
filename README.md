# MicroProfile ReST Helpers

## Usage

### BasicAuthHeader

in your eclipse microprofile rest client definition

```java
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.BeanParam;

// import BasicAuthHeader
import es.eisig.mp_rest_helper.BasicAuthHeader;

@Path("/")
public interface ApiDefinition {
    //Use `@BeanParam` when Basic Authentication is needed in your API definition interface:
    @GET
    @Path("/my/authenticated/endpoint")
    String getAuthenticatedResponse(@BeanParam BasicAuthHeader authHeader);
}
```

Now, simply use the included builder to generate your authHeader parameter:
```java
ApiDefinition api = //invoke RestClientBuilder...

String result = api.getAuthenticatedResponse(BasicAuthHeader.builder()
    .username("username")
    .password("password")
    .build());
```
