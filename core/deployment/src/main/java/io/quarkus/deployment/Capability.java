package io.quarkus.deployment;

/**
 * Represents a capability of a core extension.
 */
public interface Capability {

    String QUARKUS_PREFIX = "io.quarkus";

    /**
     * A datasource connection pool implementation
     */
    String AGROAL = QUARKUS_PREFIX + ".agroal";

    /**
     * An assistant implementation
     */
    String ASSISTANT = QUARKUS_PREFIX + ".assistant";

    /**
     * JSR 365 compatible contexts and dependency injection
     */
    String CDI = QUARKUS_PREFIX + ".cdi";

    String CONFIG_YAML = QUARKUS_PREFIX + ".config.yaml";

    /**
     * Java Servlet API
     */
    String SERVLET = QUARKUS_PREFIX + ".servlet";

    /**
     * Java Transaction API (JTA)
     */
    String TRANSACTIONS = QUARKUS_PREFIX + ".transactions";

    String LRA_PARTICIPANT = QUARKUS_PREFIX + ".lra.participant";

    String JACKSON = QUARKUS_PREFIX + ".jackson";
    String JSONB = QUARKUS_PREFIX + ".jsonb";

    String JAXB = QUARKUS_PREFIX + ".jaxb";
    String JAXP = QUARKUS_PREFIX + ".jaxp";

    String KOTLIN = QUARKUS_PREFIX + ".kotlin";

    String HAL = QUARKUS_PREFIX + ".hal";

    String REACTIVE_ROUTES = QUARKUS_PREFIX + ".reactive-routes";

    String REST = QUARKUS_PREFIX + ".rest";
    String REST_CLIENT = REST + ".client";
    String REST_CLIENT_REACTIVE = REST_CLIENT + ".reactive";
    String REST_CLIENT_REACTIVE_JSON = REST_CLIENT + ".reactive.json";
    String REST_CLIENT_REACTIVE_JACKSON = REST_CLIENT_REACTIVE_JSON + ".jackson";
    String REST_CLIENT_REACTIVE_JSONB = REST_CLIENT_REACTIVE_JSON + ".jsonb";
    String REST_JACKSON = REST + ".jackson";
    String REST_JSONB = REST + ".jsonb";

    String RESTEASY = QUARKUS_PREFIX + ".resteasy";
    String RESTEASY_JSON = RESTEASY + ".json";
    String RESTEASY_CLIENT = RESTEASY + ".client";

    String RESTEASY_JSON_JACKSON = RESTEASY_JSON + ".jackson";
    String RESTEASY_JSON_JACKSON_CLIENT = RESTEASY_JSON_JACKSON + ".client";

    String RESTEASY_JSON_JSONB = RESTEASY_JSON + ".jsonb";
    String RESTEASY_JSON_JSONB_CLIENT = RESTEASY_JSON_JSONB + ".client";

    String RESTEASY_MUTINY = RESTEASY + ".mutiny";
    String RESTEASY_REACTIVE = RESTEASY + ".reactive";
    String RESTEASY_REACTIVE_JSON = RESTEASY_REACTIVE + ".json";
    String RESTEASY_REACTIVE_JSON_JACKSON = RESTEASY_REACTIVE_JSON + ".jackson";
    String RESTEASY_REACTIVE_JSON_JSONB = RESTEASY_REACTIVE_JSON + ".jsonb";

    String RESTEASY_MULTIPART = RESTEASY + ".multipart";

    String JWT = QUARKUS_PREFIX + ".jwt";

    /**
     * @deprecated Tika has been moved to the Quarkiverse
     */
    @Deprecated
    String TIKA = QUARKUS_PREFIX + ".tika";

    String MONGODB_CLIENT = QUARKUS_PREFIX + ".mongodb-client";
    String MONGODB_PANACHE = QUARKUS_PREFIX + ".mongodb.panache";
    String MONGODB_PANACHE_KOTLIN = MONGODB_PANACHE + ".kotlin";

    String FLYWAY = QUARKUS_PREFIX + ".flyway";
    String LIQUIBASE = QUARKUS_PREFIX + ".liquibase";

    String SECURITY = QUARKUS_PREFIX + ".security";
    String SECURITY_ELYTRON_OAUTH2 = SECURITY + ".elytron.oauth2";
    String SECURITY_ELYTRON_JDBC = SECURITY + ".elytron.jdbc";
    String SECURITY_ELYTRON_LDAP = SECURITY + ".elytron.ldap";
    String SECURITY_JPA = SECURITY + ".jpa";

    String QUARTZ = QUARKUS_PREFIX + ".quartz";
    String KUBERNETES_SERVICE_BINDING = QUARKUS_PREFIX + ".kubernetes.service.binding";
    String KUBERNETES_CLIENT = QUARKUS_PREFIX + ".kubernetes.client";

    /**
     * @deprecated Use more precise capability {@link Capability#SMALLRYE_METRICS}
     * @see io.quarkus.deployment.metrics.MetricsCapabilityBuildItem
     */
    @Deprecated
    String METRICS = QUARKUS_PREFIX + ".metrics";
    String CONTAINER_IMAGE_JIB = QUARKUS_PREFIX + ".container.image.jib";
    String CONTAINER_IMAGE_DOCKER = QUARKUS_PREFIX + ".container.image.docker";
    String CONTAINER_IMAGE_PODMAN = QUARKUS_PREFIX + ".container.image.podman";
    String CONTAINER_IMAGE_OPENSHIFT = QUARKUS_PREFIX + ".container.image.openshift";
    String CONTAINER_IMAGE_BUILDPACK = QUARKUS_PREFIX + ".container.image.buildpack";
    String HIBERNATE_ORM = QUARKUS_PREFIX + ".hibernate.orm";
    String HIBERNATE_ENVERS = QUARKUS_PREFIX + ".hibernate.envers";
    String HIBERNATE_REACTIVE = QUARKUS_PREFIX + ".hibernate.reactive";
    String HIBERNATE_VALIDATOR = QUARKUS_PREFIX + ".hibernate.validator";
    String OPENTELEMETRY_TRACER = QUARKUS_PREFIX + ".opentelemetry.tracer";
    String OPENTELEMETRY_METRICS = QUARKUS_PREFIX + ".opentelemetry.metrics";
    String OPENTELEMETRY_LOGS = QUARKUS_PREFIX + ".opentelemetry.logs";

    String OPENSHIFT = QUARKUS_PREFIX + ".openshift";
    String OPENSHIFT_CLIENT = OPENSHIFT + ".client";

    String OIDC = QUARKUS_PREFIX + ".oidc";
    String OIDC_CLIENT = OIDC + ".client";

    String KEYCLOAK_AUTHORIZATION = QUARKUS_PREFIX + ".keycloak.authorization";

    String SCHEDULER = QUARKUS_PREFIX + ".scheduler";

    String SMALLRYE_METRICS = QUARKUS_PREFIX + ".smallrye.metrics";
    String SMALLRYE_HEALTH = QUARKUS_PREFIX + ".smallrye.health";
    String SMALLRYE_OPENAPI = QUARKUS_PREFIX + ".smallrye.openapi";
    String SMALLRYE_GRAPHQL = QUARKUS_PREFIX + ".smallrye.graphql";
    String SMALLRYE_FAULT_TOLERANCE = QUARKUS_PREFIX + ".smallrye.faulttolerance";
    String SMALLRYE_STORK = QUARKUS_PREFIX + ".smallrye.stork";

    String SPRING_WEB = QUARKUS_PREFIX + ".spring.web";

    String VERTX = QUARKUS_PREFIX + ".vertx";
    String VERTX_CORE = VERTX + ".core";
    String VERTX_HTTP = VERTX + ".http";
    String VERTX_WEBSOCKETS = VERTX + ".websockets";

    String APICURIO_REGISTRY = QUARKUS_PREFIX + ".apicurio.registry";
    String APICURIO_REGISTRY_AVRO = APICURIO_REGISTRY + ".avro";
    String APICURIO_REGISTRY_JSON_SCHEMA = APICURIO_REGISTRY + ".json";

    String CONFLUENT_REGISTRY = QUARKUS_PREFIX + ".confluent.registry";
    String CONFLUENT_REGISTRY_AVRO = CONFLUENT_REGISTRY + ".avro";

    String PICOCLI = QUARKUS_PREFIX + ".picocli";

    String KAFKA = QUARKUS_PREFIX + ".kafka";

    @Deprecated
    /**
     * @deprecated use {@link #MESSAGING} instead
     */
    String SMALLRYE_REACTIVE_MESSAGING = QUARKUS_PREFIX + ".smallrye.reactive.messaging";
    String MESSAGING = QUARKUS_PREFIX + ".messaging";
    String REDIS_CLIENT = QUARKUS_PREFIX + ".redis";

    String CACHE = QUARKUS_PREFIX + ".cache";
    String JDBC_ORACLE = QUARKUS_PREFIX + ".jdbc.oracle";
    String REACTIVE_PG_CLIENT = QUARKUS_PREFIX + ".reactive-pg-client";
    String REACTIVE_ORACLE_CLIENT = QUARKUS_PREFIX + ".reactive-oracle-client";
    String REACTIVE_MYSQL_CLIENT = QUARKUS_PREFIX + ".reactive-mysql-client";
    String REACTIVE_MSSQL_CLIENT = QUARKUS_PREFIX + ".reactive-mssql-client";
    String REACTIVE_DB2_CLIENT = QUARKUS_PREFIX + ".reactive-db2-client";

    String GRPC = QUARKUS_PREFIX + ".grpc";

    String WEBSOCKETS_NEXT = QUARKUS_PREFIX + ".websockets.next";
}
