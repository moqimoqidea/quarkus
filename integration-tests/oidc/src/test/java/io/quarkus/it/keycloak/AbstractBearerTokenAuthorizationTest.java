package io.quarkus.it.keycloak;

import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import io.quarkus.test.keycloak.client.KeycloakTestClient;
import io.quarkus.test.keycloak.client.KeycloakTestClient.Tls;
import io.restassured.RestAssured;

public abstract class AbstractBearerTokenAuthorizationTest {

    KeycloakTestClient client = new KeycloakTestClient(
            new Tls("target/certificates/oidc-client-keystore.p12",
                    "target/certificates/oidc-client-truststore.p12"));

    @Test
    public void testSecureAccessSuccessWithCors() {
        String origin = "http://custom.origin.quarkus";
        String methods = "GET";
        String headers = "X-Custom";
        RestAssured.given().header("Origin", origin)
                .header("Access-Control-Request-Method", methods)
                .header("Access-Control-Request-Headers", headers)
                .when()
                .options("/api").then()
                .statusCode(200)
                .header("Access-Control-Allow-Origin", origin)
                .header("Access-Control-Allow-Methods", methods)
                .header("Access-Control-Allow-Headers", headers);

        for (String username : Arrays.asList("alice", "jdoe", "admin")) {
            RestAssured.given().auth().oauth2(getAccessToken(username))
                    .when().get("/api/users/preferredUserName")
                    .then()
                    .statusCode(200)
                    .body("userName", equalTo(username));
        }
    }

    @Test
    public void testSecureAccessSuccessCustomPrincipal() {
        for (String username : Arrays.asList("alice", "jdoe", "admin")) {
            RestAssured.given().auth().oauth2(getAccessToken(username))
                    .when().get("/api/users/me")
                    .then()
                    .statusCode(200)
                    .body("userName", equalTo(username + "@gmail.com"));
        }
    }

    @Test
    public void testBasicAuth() {
        RestAssured.given().auth()
                .preemptive().basic("alice", "password")
                .when().get("/api/users/me")
                .then()
                .statusCode(200)
                .body("userName", equalTo("alice"));
    }

    @Test
    public void testBasicAuthWrongPassword() {
        RestAssured.given().auth()
                .preemptive().basic("alice", "wrongpassword")
                .when().get("/api/users/me")
                .then()
                .statusCode(401)
                .header("WWW-Authenticate", equalTo("basic"));
    }

    @Test
    public void testSecureAccessSuccessPreferredUsername() {
        for (String username : Arrays.asList("alice", "jdoe", "admin")) {
            RestAssured.given().auth().oauth2(getAccessToken(username))
                    .when().get("/api/users/preferredUserName")
                    .then()
                    .statusCode(200)
                    .body("userName", equalTo(username));
        }
    }

    @Test
    public void testAccessAdminResource() {
        RestAssured.given().auth().oauth2(getAccessToken("admin"))
                .when().get("/api/admin")
                .then()
                .statusCode(200)
                .body(Matchers.containsString("granted:admin"));
    }

    @Test
    public void testAccessAdminResourceCustomHeaderNoBearerScheme() {
        RestAssured.given().header("X-Forwarded-Authorization", getAccessToken("admin"))
                .when().get("/api/admin")
                .then()
                .statusCode(401);
    }

    @Test
    public void testAccessAdminResourceCustomHeaderBearerScheme() {
        RestAssured.given().header("X-Forwarded-Authorization", getAccessToken("admin"))
                .when().get("/api/admin")
                .then()
                .statusCode(401);
    }

    @Test
    public void testAccessAdminResourceWithRefreshToken() {
        RestAssured.given().auth().oauth2(client.getRefreshToken("admin"))
                .when().get("/api/admin")
                .then()
                .statusCode(401);
    }

    @Test
    public void testPermissionHttpInformationProvider() {
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().get("/api/permission/http-cip")
                .then()
                .statusCode(200)
                .body("preferred_username", equalTo("alice"));
    }

    @Test
    public void testDeniedAccessAdminResource() {
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().get("/api/admin")
                .then()
                .statusCode(403);
    }

    @Test
    public void testVerificationFailedNoBearerTokenAndBasicCreds() {
        RestAssured.given()
                .when().get("/api/users/me").then()
                .statusCode(401)
                .header("WWW-Authenticate", equalTo("basic"));
    }

    @Test
    public void testVerificationFailedInvalidToken() {
        RestAssured.given().auth().oauth2("123")
                .when().get("/api/users/me").then()
                .statusCode(401)
                .header("WWW-Authenticate", equalTo("Bearer resource_metadata=\"https://localhost:8081\""));
    }

    //see https://github.com/quarkusio/quarkus/issues/5809
    @RepeatedTest(20)
    public void testOidcAndVertxHandler() {
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().body("Hello World").post("/vertx")
                .then()
                .statusCode(200)
                .body(equalTo("Hello World"));
    }

    @Test
    public void testBearerAuthFailureWhereBasicIsRequired() {
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().get("/basic-only")
                .then()
                .statusCode(401)
                .header("WWW-Authenticate", equalTo("basic"));
    }

    @Test
    public void testBasicAuthWhereBasicIsRequired() {
        RestAssured.given().auth()
                .preemptive().basic("alice", "password")
                .when().get("/basic-only")
                .then()
                .statusCode(200)
                .body(equalTo("alice:/basic-only"));
    }

    @Test
    public void testBasicAuthFailureWhereBearerIsRequired() {
        RestAssured.given().auth().preemptive().basic("alice", "password")
                .when().get("/bearer-only")
                .then()
                .statusCode(401)
                .header("WWW-Authenticate", equalTo("Bearer resource_metadata=\"https://localhost:8081\""));
    }

    @Test
    public void testBearerAuthWhereBearerIsRequired() {
        RestAssured.given().auth().oauth2(getAccessToken("alice"))
                .when().get("/bearer-only")
                .then()
                .statusCode(200)
                .body(equalTo("alice@gmail.com:/bearer-only"));
    }

    @Test
    public void testExpiredBearerToken() throws InterruptedException {
        String token = getAccessToken("alice");

        await()
                .pollDelay(3, TimeUnit.SECONDS)
                .atMost(5, TimeUnit.SECONDS).until(
                        () -> RestAssured.given().auth().oauth2(token).when()
                                .get("/api/users/me").thenReturn().statusCode() == 401);
    }

    @Test
    public void testAuthenticationEvent() {
        RestAssured.given()
                .get("/authentication-event/failure-observed")
                .then()
                .statusCode(200)
                .body(Matchers.is("false"));
        RestAssured.given().auth()
                .preemptive().basic("alice", "wrongpassword")
                .header("keep-event", "true")
                .when().get("/authentication-event/secured")
                .then()
                .statusCode(401);
        RestAssured.given()
                .get("/authentication-event/failure-observed")
                .then()
                .statusCode(200)
                .body(Matchers.is("true"));
    }

    String getAccessToken(String username) {
        return client.getAccessToken(username);
    }
}
