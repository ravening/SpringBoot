---
description: "Comprehensive guide for migrating Spring Boot applications from 3.x to 4.0, focusing on Maven and pom.xml"
applyTo: "**/*.java, **/pom.xml, **/*.properties, **/*.yml, **/*.yaml"
---

# Spring Boot 3.x to 4.0 Migration Guide

## Project Context

This guide provides comprehensive GitHub Copilot instructions for upgrading Spring Boot projects from version 3.x to 4.0, with emphasis on Maven (`pom.xml`) and Java.

**Key architectural changes in Spring Boot 4.0:**
- Modular dependency structure with focused, smaller modules
- Spring Framework 7.x required
- Jakarta EE 11 (Servlet 6.1 baseline)
- Jackson 3.x migration (package namespace changes)
- Comprehensive property reorganization

## System Requirements

### Minimum Versions

- **Java**: 17+ (prefer latest LTS: Java 21 or 25)
- **Spring Framework**: 7.x (managed by Spring Boot 4.0)
- **Jakarta EE**: 11 (Servlet 6.1 baseline)
- **GraalVM** (for native images): 25+
- **Maven**: 3.9.0+
- **Maven CycloneDX Plugin**: 3.0.0+

### Verify Compatibility

```bash
# Check current versions
mvn --version
mvn dependency:tree
```

## Pre-Migration Steps

### 1. Upgrade to Latest Spring Boot 3.5.x

Before migrating to 4.0, upgrade to the latest 3.5.x release:

```xml
<!-- pom.xml -->
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.6</version> <!-- Latest 3.x before migrating to 4.0 -->
    <relativePath/>
</parent>
```

### 2. Clean Up Deprecations

Remove all deprecated API usage from Spring Boot 3.x. These will be compilation errors in 4.0:

```bash
# Build and review warnings
mvn clean package -Dmaven.compiler.showWarnings=true
```

### 3. Review Dependency Changes

Compare your dependencies against:
- [Spring Boot 3.5.x Dependency Versions](https://docs.spring.io/spring-boot/3.5/appendix/dependency-versions/coordinates.html)
- [Spring Boot 4.0.x Dependency Versions](https://docs.spring.io/spring-boot/4.0/appendix/dependency-versions/coordinates.html)

## Module Restructuring and Starter Changes

### Critical: Modular Architecture

Spring Boot 4.0 introduces **smaller, focused modules** replacing large monolithic jars. This requires dependency updates in most projects.

**Important for Library Authors:** Due to the modularization effort and package reorganization, **supporting both Spring Boot 3 and Spring Boot 4 within the same artifact is strongly discouraged**. Library authors should publish separate artifacts for each major version to avoid runtime conflicts and ensure clean dependency management.

### Migration Strategy: Choose One Approach

#### Option 1: Technology-Specific Starters (Recommended for Production)

Most technologies covered by Spring Boot now have **dedicated test starter companions**. This provides fine-grained control.

**Complete Starter Reference:** For comprehensive tables of all available starters (Core, Web, Database, Spring Data, Messaging, Security, Templating, Production-Ready, etc.) and their test companions, refer to the [official Spring Boot 4.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide#starters).

**pom.xml:**
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.0</version>
    <relativePath/>
</parent>

<dependencies>
    <!-- Core starters with dedicated test modules -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

#### Option 2: Classic Starters (Quick Migration, Deprecated)

For rapid migration, use **classic starters** that bundle all auto-configuration (like Spring Boot 3.x):

**pom.xml:**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-classic</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test-classic</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

**Warning**: Classic starters are **deprecated** and will be removed in future releases. Plan migration to technology-specific starters.

#### Option 3: Direct Module Dependencies (Advanced)

For explicit control over transitive dependencies:

**pom.xml:**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-webmvc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-webmvc-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Renamed Starters (Breaking Changes)

Update these artifact IDs in your `pom.xml`:

| Spring Boot 3.x | Spring Boot 4.0 | Notes |
|----------------|-----------------|-------|
| `spring-boot-starter-web` | `spring-boot-starter-webmvc` | Explicit naming |
| `spring-boot-starter-web-services` | `spring-boot-starter-webservices` | Hyphen removed |
| `spring-boot-starter-aop` | `spring-boot-starter-aspectj` | Only needed if using `org.aspectj.lang.annotation` |
| `spring-boot-starter-oauth2-authorization-server` | `spring-boot-starter-security-oauth2-authorization-server` | Security namespace |
| `spring-boot-starter-oauth2-client` | `spring-boot-starter-security-oauth2-client` | Security namespace |
| `spring-boot-starter-oauth2-resource-server` | `spring-boot-starter-security-oauth2-resource-server` | Security namespace |

**Migration Example (pom.xml):**
```xml
<dependencies>
    <!-- Old (Spring Boot 3.x) -->
    <!-- <artifactId>spring-boot-starter-web</artifactId> -->
    <!-- <artifactId>spring-boot-starter-oauth2-client</artifactId> -->

    <!-- New (Spring Boot 4.0) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security-oauth2-client</artifactId>
    </dependency>
</dependencies>
```

### AspectJ Starter Clarification

Only include `spring-boot-starter-aspectj` if you're **actually using AspectJ annotations**:

```java
// Only needed if code uses org.aspectj.lang.annotation package
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class MyAspect {
    @Before("execution(* com.example..*(..))")
    public void beforeAdvice() { }
}
```

If not using AspectJ, remove the dependency.

## Removed Features and Alternatives

### Embedded Servers

#### Undertow Removed

**Undertow is completely removed** - not compatible with Servlet 6.1 baseline.

**Migration:**
- Use **Tomcat** (default) or **Jetty**
- Do **not** deploy Spring Boot 4.0 apps to non-Servlet 6.1 containers

**pom.xml (switch to Jetty if needed):**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-webmvc</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <!-- Alternative to Tomcat -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
</dependencies>
```

### Session Management

#### Spring Session Hazelcast and MongoDB Removed

**Maintained by respective teams**, no longer in Spring Boot dependency management.

**Migration (pom.xml) — explicit versions required:**
```xml
<dependencies>
    <dependency>
        <groupId>com.hazelcast</groupId>
        <artifactId>spring-session-hazelcast</artifactId>
        <version>3.x.x</version> <!-- Check Hazelcast documentation -->
    </dependency>
    <dependency>
        <groupId>org.springframework.session</groupId>
        <artifactId>spring-session-data-mongodb</artifactId>
        <version>4.x.x</version> <!-- Check MongoDB documentation -->
    </dependency>
</dependencies>
```

### Reactive Messaging

#### Pulsar Reactive Removed

Spring Pulsar dropped Reactor support - reactive Pulsar client removed.

**Migration:**
- Use imperative Pulsar client
- Or migrate to alternative reactive messaging (Kafka, RabbitMQ)

### Testing

#### Spock Framework Removed

**Spock does not yet support Groovy 5** (required for Spring Boot 4.0).

**Migration:**
- Use JUnit 5
- Or wait for Spock Groovy 5 compatibility

### Build Features

#### Executable Jar Launch Scripts Removed

Embedded launch scripts for "fully executable" jars removed (Unix-specific, limited use).

**pom.xml (remove):**
```xml
<!-- Remove the executable configuration from spring-boot-maven-plugin -->
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <!-- REMOVE THIS - no longer supported -->
        <!-- <executable>true</executable> -->
    </configuration>
</plugin>
```

**Alternatives:**
- Use `java -jar app.jar` directly
- Use systemd service files

#### Classic Uber-Jar Loader Removed

The classic uber-jar loader has been removed. Remove any loader implementation configuration from your build.

**pom.xml - remove:**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <loaderImplementation>CLASSIC</loaderImplementation> <!-- REMOVE THIS -->
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Jackson 3 Migration

### Major Breaking Change: Package Namespace

Jackson 3 changes **group ID and package names**:

| Component | Old (Jackson 2) | New (Jackson 3) |
|-----------|----------------|-----------------|
| Group ID | `com.fasterxml.jackson` | `tools.jackson` |
| Packages | `com.fasterxml.jackson.*` | `tools.jackson.*` |
| Exception | `jackson-annotations` | Still uses `com.fasterxml.jackson.core` group |

**pom.xml:**
```xml
<dependencies>
    <!-- Jackson 3 uses new group ID — versions managed by Spring Boot 4.0 -->
    <dependency>
        <groupId>tools.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
    </dependency>

    <!-- Exception: annotations still use old group -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
    </dependency>
</dependencies>
```

### Class and Annotation Renames

Update imports and annotations:

| Spring Boot 3.x | Spring Boot 4.0 |
|----------------|-----------------|
| `Jackson2ObjectMapperBuilderCustomizer` | `JsonMapperBuilderCustomizer` |
| `JsonObjectSerializer` | `ObjectValueSerializer` |
| `JsonValueDeserializer` | `ObjectValueDeserializer` |
| `@JsonComponent` | `@JacksonComponent` |
| `@JsonMixin` | `@JacksonMixin` |

**Migration Example:**
```java
// Old (Spring Boot 3.x)
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class CustomSerializer extends JsonSerializer<MyType> { }

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> builder.simpleDateFormat("yyyy-MM-dd");
    }
}

// New (Spring Boot 4.0)
import tools.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.JsonMapperBuilderCustomizer;
import org.springframework.boot.jackson.JacksonComponent;

@JacksonComponent
public class CustomSerializer extends JsonSerializer<MyType> { }

@Configuration
public class JacksonConfig {
    @Bean
    public JsonMapperBuilderCustomizer customizer() {
        return builder -> builder.simpleDateFormat("yyyy-MM-dd");
    }
}
```

### Configuration Property Changes

**application.yml migration:**
```yaml
# Old (Spring Boot 3.x)
spring:
  jackson:
    read:
      enums-using-to-string: true
    write:
      dates-as-timestamps: false

# New (Spring Boot 4.0)
spring:
  jackson:
    json:
      read:
        enums-using-to-string: true
      write:
        dates-as-timestamps: false
```

### Jackson 2 Compatibility Module (Temporary)

For gradual migration, use the **temporary compatibility module** (deprecated, will be removed):

**pom.xml:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-jackson2</artifactId>
</dependency>
```

**application.yml:**
```yaml
spring:
  jackson:
    use-jackson2-defaults: true # Use Jackson 2 behavior
```

**Properties under `spring.jackson2.*` namespace** when using compatibility module.

**Plan migration away from this module** - it will be removed in future versions.

## Core Framework Changes

### Nullability Annotations: JSpecify

Spring Boot 4.0 adds **JSpecify nullability annotations** throughout the codebase.

**Impact:**
- Null checkers (SpotBugs, NullAway) may report new issues
- **RestClient methods like `body()` are now explicitly marked as nullable** - always check for null or use `Objects.requireNonNull()`

**Migration for Java:**
```java
// RestClient body() can return null — handle explicitly
String body = restClient.get()
    .uri("https://api.example.com/data")
    .retrieve()
    .body(String.class); // Nullable - handle appropriately

Objects.requireNonNull(body, "Response body must not be null");
```

**Actuator endpoint parameters:**
- Cannot use `javax.annotations.NonNull` or `org.springframework.lang.Nullable`
- Use `org.jspecify.annotations.Nullable` instead

**pom.xml (add JSpecify if needed explicitly):**
```xml
<dependency>
    <groupId>org.jspecify</groupId>
    <artifactId>jspecify</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Package Relocations

#### BootstrapRegistry

**Old import:**
```java
import org.springframework.boot.BootstrapRegistry;
```

**New import:**
```java
import org.springframework.boot.bootstrap.BootstrapRegistry;
```

#### EnvironmentPostProcessor

**Old import:**
```java
import org.springframework.boot.env.EnvironmentPostProcessor;
```

**New import:**
```java
import org.springframework.boot.EnvironmentPostProcessor;
```

**Update `META-INF/spring.factories`:**
```properties
# Old
org.springframework.boot.env.EnvironmentPostProcessor=com.example.MyPostProcessor

# New
org.springframework.boot.EnvironmentPostProcessor=com.example.MyPostProcessor
```

**Note:** Deprecated form still available temporarily but will be removed.

#### Entity Scan

**Old import:**
```java
import org.springframework.boot.autoconfigure.domain.EntityScan;
```

**New import:**
```java
import org.springframework.boot.persistence.autoconfigure.EntityScan;
```

### Logging Changes

#### Logback Default Charset

Log files now default to **UTF-8** (harmonized with Log4j2):

**logback-spring.xml (explicit configuration):**
```xml
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>app.log</file>
        <encoder>
            <charset>UTF-8</charset> <!-- Now default -->
            <pattern>%d{yyyy-MM-dd HH:mm:ss} - %msg%n</pattern>
        </encoder>
    </appender>
</configuration>
```

**Console logging:** Uses `Console#charset()` if available (Java 17+), otherwise falls back to UTF-8. This provides better platform compatibility while maintaining consistent encoding.

### DevTools Changes

#### Live Reload Disabled by Default

**application.yml:**
```yaml
spring:
  devtools:
    livereload:
      enabled: true # Must explicitly enable in 4.0
```

**pom.xml:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

### PropertyMapper API Behavioral Change

**Breaking change:** No longer calls adapter/predicate methods by default when source is `null`.

**Migration pattern:**
```java
// Old behavior (Spring Boot 3.x)
map.from(source::method).to(destination::method);
// Calls destination.method(null) if source returns null

// New behavior (Spring Boot 4.0)
map.from(source::method).to(destination::method);
// Skips call if source returns null

// Explicit null handling (new)
map.from(source::method).always().to(destination::method);
// Always calls destination.method(value), even if null
```

**Removed method:** `alwaysApplyingNotNull()` - use `always()` instead.

**Migration example:** Review [Spring Boot commit 239f384ac0](https://github.com/spring-projects/spring-boot/commit/239f384ac0893d151b89f204886874c6adb00001) to see how Spring Boot itself adapted to the new API.

## Dependency and Build Changes

### Maven Plugin Updates

**pom.xml:**
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.0</version>
    <relativePath/>
</parent>

<properties>
    <java.version>21</java.version> <!-- Or 17, 25 -->
</properties>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <!-- CycloneDX SBOM plugin - minimum 3.0.0 -->
        <plugin>
            <groupId>org.cyclonedx</groupId>
            <artifactId>cyclonedx-maven-plugin</artifactId>
            <version>3.0.0</version>
        </plugin>
    </plugins>
</build>
```

### Optional Dependencies in Maven Uber Jars

Optional dependencies are **no longer included in uber jars by default**.

**pom.xml (include optionals explicitly if needed):**
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <includeSystemScope>true</includeSystemScope>
        <!-- Set includeOptional if your plugin version supports it -->
    </configuration>
</plugin>
```

### Spring Retry → Spring Framework Core Retry

Spring Boot 4.0 removes dependency management for **Spring Retry** (portfolio migrating to Spring Framework 7.0 core retry).

**Migration Option 1: Use Spring Framework Core Retry (Recommended)**

```java
// Use built-in Spring Framework retry
import org.springframework.core.retry.RetryTemplate;
import org.springframework.core.retry.support.RetryTemplateBuilder;

@Configuration
public class RetryConfig {
    @Bean
    public RetryTemplate retryTemplate() {
        return new RetryTemplateBuilder()
            .maxAttempts(3)
            .fixedBackoff(1000)
            .build();
    }
}
```

**Migration Option 2: Explicit Spring Retry Version (Temporary)**

**pom.xml:**
```xml
<dependency>
    <groupId>org.springframework.retry</groupId>
    <artifactId>spring-retry</artifactId>
    <version>2.0.5</version> <!-- Explicit version required, no longer managed -->
</dependency>
```

**Plan migration to Spring Framework core retry.**

### Spring Authorization Server

Now part of Spring Security - explicit version management removed.

**pom.xml (before - Spring Boot 3.x):**
```xml
<!-- No longer works with a hardcoded version under Spring Boot management -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-authorization-server</artifactId>
    <version>1.3.0</version> <!-- No longer works -->
</dependency>
```

**Migration (Spring Boot 4.0):**
```xml
<!-- Version now managed via spring-security.version property -->
<properties>
    <spring-security.version>7.0.0</spring-security.version>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-oauth2-authorization-server</artifactId>
        <!-- No version needed, managed by Spring Boot 4.0 -->
    </dependency>
</dependencies>
```

### Elasticsearch Client Changes

#### Low-Level Client Replacement

**Deprecated low-level `RestClient` → new `Rest5Client`:**

**Note:** Higher-level clients (`ElasticsearchClient` and Spring Data's `ReactiveElasticsearchClient`) **remain unchanged** and have been updated internally to use the new low-level client.

**Imports:**
```java
// Old (Spring Boot 3.x)
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.RestClientBuilderCustomizer;

// New (Spring Boot 4.0)
import co.elastic.clients.transport.rest_client.Rest5Client;
import co.elastic.clients.transport.rest_client.Rest5ClientBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.Rest5ClientBuilderCustomizer;
```

**Configuration:**
```java
@Configuration
public class ElasticsearchConfig {

    // Old
    // @Bean
    // public RestClientBuilderCustomizer restClientCustomizer() {
    //     return builder -> builder.setRequestConfigCallback(config ->
    //         config.setConnectTimeout(5000)
    //     );
    // }

    // New
    @Bean
    public Rest5ClientBuilderCustomizer rest5ClientCustomizer() {
        return builder -> builder.setRequestConfigCallback(config ->
            config.setConnectTimeout(5000)
        );
    }
}
```

**Dependency Consolidation:**

Sniffer now included in `co.elastic.clients:elasticsearch-java` module.

**pom.xml:**
```xml
<dependencies>
    <!-- Remove these - no longer managed -->
    <!--
    <dependency>
        <groupId>org.elasticsearch.client</groupId>
        <artifactId>elasticsearch-rest-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.elasticsearch.client</groupId>
        <artifactId>elasticsearch-rest-client-sniffer</artifactId>
    </dependency>
    -->

    <!-- Use single dependency (includes sniffer) -->
    <dependency>
        <groupId>co.elastic.clients</groupId>
        <artifactId>elasticsearch-java</artifactId>
        <version>8.x.x</version>
    </dependency>
</dependencies>
```

### Hibernate Dependency Changes

**pom.xml:**
```xml
<dependencies>
    <!-- Renamed module: hibernate-jpamodelgen replaced by hibernate-processor -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-processor</artifactId>
    </dependency>

    <!-- These artifacts are NO LONGER PUBLISHED by Hibernate — remove them: -->
    <!-- hibernate-proxool - discontinued by Hibernate project -->
    <!-- hibernate-vibur - discontinued by Hibernate project -->
</dependencies>
```

**Note:** `hibernate-jpamodelgen` artifact still exists but is deprecated. Use `hibernate-processor` going forward.

## Configuration Property Changes

### MongoDB Property Restructuring

**Major reorganization:** Non-Spring Data properties moved to `spring.mongodb.*`:

**application.yml migration:**
```yaml
# Old (Spring Boot 3.x)
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/mydb
      database: mydb
      host: localhost
      port: 27017
      username: user
      password: pass
      authentication-database: admin
      replica-set-name: rs0
      additional-hosts:
        - host1:27017
        - host2:27017
      ssl:
        enabled: true
        bundle: my-bundle
      representation:
        uuid: STANDARD

management:
  health:
    mongo:
      enabled: true
  metrics:
    mongo:
      command:
        enabled: true
      connectionpool:
        enabled: true

# New (Spring Boot 4.0)
spring:
  mongodb:
    uri: mongodb://localhost:27017/mydb
    database: mydb
    host: localhost
    port: 27017
    username: user
    password: pass
    authentication-database: admin
    replica-set-name: rs0
    additional-hosts:
      - host1:27017
      - host2:27017
    ssl:
      enabled: true
      bundle: my-bundle
    representation:
      uuid: STANDARD # Explicit configuration now required

  data:
    mongodb:
      # Spring Data-specific properties remain here
      auto-index-creation: true
      field-naming-strategy: org.springframework.data.mapping.model.SnakeCaseFieldNamingStrategy
      gridfs:
        bucket: fs
        database: gridfs-db
      repositories:
        type: auto
      representation:
        big-decimal: DECIMAL128 # Explicit configuration now required

management:
  health:
    mongodb: # Renamed from "mongo"
      enabled: true
  metrics:
    mongodb: # Renamed from "mongo"
      command:
        enabled: true
      connectionpool:
        enabled: true
```

**Key changes:**
- **UUID representation**: **MANDATORY** - No default provided, must explicitly configure `spring.mongodb.representation.uuid` (e.g., `STANDARD`, `JAVA_LEGACY`, `PYTHON_LEGACY`, `C_SHARP_LEGACY`)
- **BigDecimal representation**: **MANDATORY** - No default provided, must explicitly configure `spring.data.mongodb.representation.big-decimal` (e.g., `DECIMAL128`, `STRING`)
- **Management properties**: `mongo` → `mongodb`
- **Failure to configure these will result in runtime errors when persisting UUID or BigDecimal values**

### Spring Session Property Renames

**application.yml migration:**
```yaml
# Old (Spring Boot 3.x)
spring:
  session:
    redis:
      namespace: myapp:session
      flush-mode: on-save
    mongodb:
      collection-name: sessions

# New (Spring Boot 4.0)
spring:
  session:
    data:
      redis:
        namespace: myapp:session
        flush-mode: on-save
      mongodb:
        collection-name: sessions
```

### Persistence Module Property Change

**application.yml migration:**
```yaml
# Old (Spring Boot 3.x)
spring:
  dao:
    exceptiontranslation:
      enabled: true

# New (Spring Boot 4.0)
spring:
  persistence:
    exceptiontranslation:
      enabled: true
```

## Web Framework Changes

### Static Resource Locations

`PathRequest#toStaticResources()` now includes `/fonts/**` by default.

**Security configuration (exclude fonts if needed):**
```java
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceLocation;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(
                PathRequest.toStaticResources()
                    .atCommonLocations()
                    .excluding(StaticResourceLocation.FONTS)
            ).permitAll()
            .anyRequest().authenticated()
        );
        return http.build();
    }
}
```

### HttpMessageConverters Deprecation

`HttpMessageConverters` deprecated due to framework improvements (conflated client/server converters).

**Migration:**
```java
// Old (Spring Boot 3.x)
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

@Configuration
public class WebConfig {
    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(new MyCustomConverter());
    }
}

// New (Spring Boot 4.0)
import org.springframework.boot.autoconfigure.http.client.ClientHttpMessageConvertersCustomizer;
import org.springframework.boot.autoconfigure.http.server.ServerHttpMessageConvertersCustomizer;

@Configuration
public class WebConfig {

    // Separate client and server converters
    @Bean
    public ClientHttpMessageConvertersCustomizer clientConvertersCustomizer() {
        return converters -> converters.add(new MyCustomClientConverter());
    }

    @Bean
    public ServerHttpMessageConvertersCustomizer serverConvertersCustomizer() {
        return converters -> converters.add(new MyCustomServerConverter());
    }
}
```

### Jersey and Jackson 3 Incompatibility

**Jersey 4.0 limitation:** Spring Boot 4.0 supports Jersey 4.0, which **does not yet support Jackson 3**.

**Solution:** Use `spring-boot-jackson2` compatibility module **either in place of or alongside** `spring-boot-jackson`:

**pom.xml:**
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jersey</artifactId>
    </dependency>
    <!-- Required for Jersey JSON processing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-jackson2</artifactId>
    </dependency>
    <!-- Optional: Keep Jackson 3 for non-Jersey parts of application -->
    <!--
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-jackson</artifactId>
    </dependency>
    -->
</dependencies>
```

**Note:** If using only Jersey in your application, you can replace Jackson 3 entirely with Jackson 2 compatibility module.

## Messaging Framework Changes

### Kafka Streams Customizer Replacement

**Deprecated `StreamBuilderFactoryBeanCustomizer` → `StreamsBuilderFactoryBeanConfigurer`:**

```java
// Old (Spring Boot 3.x)
import org.springframework.boot.autoconfigure.kafka.StreamsBuilderFactoryBeanCustomizer;

@Configuration
public class KafkaStreamsConfig {
    @Bean
    public StreamBuilderFactoryBeanCustomizer streamsCustomizer() {
        return factoryBean -> factoryBean.setKafkaStreamsCustomizer(streams -> {
            // Custom config
        });
    }
}

// New (Spring Boot 4.0)
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;

@Configuration
public class KafkaStreamsConfig {
    @Bean
    public StreamsBuilderFactoryBeanConfigurer streamsConfigurer() {
        return factoryBean -> factoryBean.setKafkaStreamsCustomizer(streams -> {
            // Custom config
        });
    }
}
```

**Note:** New configurer implements `Ordered` with default value `0`.

### Kafka Retry Property Change

**application.yml migration:**
```yaml
# Old (Spring Boot 3.x)
spring:
  kafka:
    retry:
      topic:
        backoff:
          random: true

# New (Spring Boot 4.0)
spring:
  kafka:
    retry:
      topic:
        backoff:
          jitter: 0.5 # More flexible than boolean
```

### RabbitMQ Retry Customizer Split

**Spring AMQP moved from Spring Retry → Spring Framework core retry**, with customizer split:

```java
// Old (Spring Boot 3.x)
import org.springframework.boot.autoconfigure.amqp.RabbitRetryTemplateCustomizer;

@Configuration
public class RabbitConfig {
    @Bean
    public RabbitRetryTemplateCustomizer retryCustomizer() {
        return template -> {
            // Applies to both RabbitTemplate and listeners
        };
    }
}

// New (Spring Boot 4.0)
import org.springframework.boot.autoconfigure.amqp.RabbitTemplateRetrySettingsCustomizer;
import org.springframework.boot.autoconfigure.amqp.RabbitListenerRetrySettingsCustomizer;

@Configuration
public class RabbitConfig {

    // For RabbitTemplate operations
    @Bean
    public RabbitTemplateRetrySettingsCustomizer templateRetryCustomizer() {
        return settings -> settings.setMaxAttempts(5);
    }

    // For message listeners
    @Bean
    public RabbitListenerRetrySettingsCustomizer listenerRetryCustomizer() {
        return settings -> settings.setMaxAttempts(3);
    }
}
```

## Testing Framework Changes

### Mockito Integration Removed

`MockitoTestExecutionListener` removed (deprecated in 3.4).

**Migration to MockitoExtension:**
```java
// Old (Spring Boot 3.x)
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.Captor;

@SpringBootTest
public class MyServiceTest {
    @Mock
    private MyRepository repository;

    @Captor
    private ArgumentCaptor<String> captor;
}

// New (Spring Boot 4.0)
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@SpringBootTest
@ExtendWith(MockitoExtension.class) // Explicit extension required
public class MyServiceTest {
    @Mock
    private MyRepository repository;

    @Captor
    private ArgumentCaptor<String> captor;
}
```

### @SpringBootTest Changes

`@SpringBootTest` no longer provides **MockMVC**, **WebTestClient**, or **TestRestTemplate** automatically.

#### MockMVC Configuration

```java
// Old (Spring Boot 3.x)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc; // Available automatically
}

// New (Spring Boot 4.0)
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.HtmlUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // Explicit annotation required
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;
}

// HtmlUnit configuration moved to annotation attribute
@AutoConfigureMockMvc(
    htmlUnit = @HtmlUnit(webClient = false, webDriver = false)
)
```

#### WebTestClient Configuration

```java
// Old (Spring Boot 3.x)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxTest {
    @Autowired
    private WebTestClient webTestClient; // Available automatically
}

// New (Spring Boot 4.0)
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient // Explicit annotation required
public class WebFluxTest {
    @Autowired
    private WebTestClient webTestClient;
}
```

#### TestRestTemplate → RestTestClient (Recommended)

**Spring Boot 4.0 introduces `RestTestClient`** as modern replacement for `TestRestTemplate`.

```java
// Old approach (still works with annotation)
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureTestRestTemplate;
import org.springframework.boot.resttestclient.TestRestTemplate; // Updated package

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate // Required in 4.0
public class RestApiTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
}

// New recommended approach
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureRestTestClient;
import org.springframework.boot.resttestclient.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient // New annotation
public class RestApiTest {
    @Autowired
    private RestTestClient restTestClient;

    @Test
    void testEndpoint() {
        ResponseEntity<List<User>> response = restTestClient.get()
            .uri("/api/users")
            .retrieve()
            .toEntity(new ParameterizedTypeReference<List<User>>() {});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

**TestRestTemplate package change (if still using):**

**IMPORTANT:** If continuing to use `TestRestTemplate`, you must:
1. Add the `spring-boot-resttestclient` test dependency
2. **Update the package import** (class moved to new package)

**pom.xml:**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-resttestclient</artifactId>
    <scope>test</scope>
</dependency>
```

**Update package import (required):**
```java
// Old package import - will cause compilation failure
// import org.springframework.boot.test.web.client.TestRestTemplate;

// New package import - required in Spring Boot 4.0
import org.springframework.boot.resttestclient.TestRestTemplate;
```

### @PropertyMapping Annotation Relocation

```java
// Old (Spring Boot 3.x)
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.autoconfigure.properties.Skip;

// New (Spring Boot 4.0)
import org.springframework.boot.test.context.PropertyMapping;
import org.springframework.boot.test.context.PropertyMapping.Skip;
```

## Production-Ready Features and Modules

### Health, Metrics, and Observability Modules

Spring Boot 4.0 modularizes production-ready features into focused modules:

**pom.xml:**
```xml
<dependencies>
    <!-- Health monitoring -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-health</artifactId>
    </dependency>

    <!-- Micrometer metrics -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-metrics</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-metrics-test</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Micrometer observation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-observation</artifactId>
    </dependency>

    <!-- Distributed tracing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-tracing</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-tracing-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-tracing-brave</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-tracing-opentelemetry</artifactId>
    </dependency>

    <!-- OpenTelemetry integration -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-opentelemetry</artifactId>
    </dependency>

    <!-- Zipkin reporter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-zipkin</artifactId>
    </dependency>
</dependencies>
```

**pom.xml (example observability stack):**
```xml
<dependencies>
    <!-- Actuator with metrics and tracing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-observation</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-tracing-opentelemetry</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-opentelemetry</artifactId>
    </dependency>

    <!-- Test support -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-metrics-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-micrometer-tracing-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

**Note:** Most applications using starters (e.g., `spring-boot-starter-actuator`) won't need to declare these modules directly. Use direct module dependencies for fine-grained control.

## Actuator Changes

### Health Probes Enabled by Default

Liveness and readiness probes now **enabled by default**.

**application.yml (disable if needed):**
```yaml
management:
  endpoint:
    health:
      probes:
        enabled: false # Disable if not using Kubernetes probes
```

**Automatically exposes:**
- `/actuator/health/liveness`
- `/actuator/health/readiness`

## Build Configuration

### Java Version Configuration

**pom.xml:**
```xml
<properties>
    <java.version>21</java.version> <!-- Or 17, 25 -->
</properties>
```

### Java Preview Features (if using Java 25)

**pom.xml:**
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <compilerArgs>
                    <arg>--enable-preview</arg>
                </compilerArgs>
                <release>${java.version}</release>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <argLine>--enable-preview</argLine>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## Migration Checklist

### Pre-Migration

- [ ] Upgrade to latest Spring Boot 3.5.x
- [ ] Review and fix all deprecation warnings
- [ ] Document current dependency versions
- [ ] Run full test suite and verify green build
- [ ] Review [Spring Boot 3.5.x → 4.0 dependency changes](https://docs.spring.io/spring-boot/4.0/appendix/dependency-versions/coordinates.html)

### Core Migration

- [ ] Update `pom.xml` parent version to Spring Boot 4.0.0
- [ ] Rename starters: `spring-boot-starter-web` → `spring-boot-starter-webmvc`, etc.
- [ ] Add technology-specific test starters (or use classic starters temporarily)
- [ ] Remove Undertow dependency if present (switch to Tomcat/Jetty)
- [ ] Remove `spring-session-hazelcast` / `spring-session-mongodb` or add explicit versions

### Jackson 3 Migration

- [ ] Update imports: `com.fasterxml.jackson` → `tools.jackson`
- [ ] Update exception: `jackson-annotations` still uses `com.fasterxml.jackson.core`
- [ ] Rename: `@JsonComponent` → `@JacksonComponent`
- [ ] Rename: `Jackson2ObjectMapperBuilderCustomizer` → `JsonMapperBuilderCustomizer`
- [ ] Update properties: `spring.jackson.read.*` → `spring.jackson.json.read.*`
- [ ] Consider temporary `spring-boot-jackson2` module if needed

### Property Updates

- [ ] MongoDB: `spring.data.mongodb.*` → `spring.mongodb.*` (for non-Spring Data properties)
- [ ] Session: `spring.session.redis.*` → `spring.session.data.redis.*`
- [ ] Persistence: `spring.dao.exceptiontranslation` → `spring.persistence.exceptiontranslation`
- [ ] Kafka retry: `backoff.random` → `backoff.jitter`

### Code Updates

- [ ] Update package: `BootstrapRegistry` → `org.springframework.boot.bootstrap.BootstrapRegistry`
- [ ] Update package: `EnvironmentPostProcessor` → `org.springframework.boot.EnvironmentPostProcessor`
- [ ] Update package: `EntityScan` → `org.springframework.boot.persistence.autoconfigure.EntityScan`
- [ ] Update: `RestClient` → `Rest5Client` (Elasticsearch)
- [ ] Update: `StreamBuilderFactoryBeanCustomizer` → `StreamsBuilderFactoryBeanConfigurer` (Kafka)
- [ ] Split: `RabbitRetryTemplateCustomizer` → `RabbitTemplateRetrySettingsCustomizer` / `RabbitListenerRetrySettingsCustomizer`
- [ ] Replace: `HttpMessageConverters` → `ClientHttpMessageConvertersCustomizer` / `ServerHttpMessageConvertersCustomizer`
- [ ] Update: `PropertyMapper` usage with `.always()` if null handling needed

### Testing Updates

- [ ] Add `@ExtendWith(MockitoExtension.class)` to tests using `@Mock` / `@Captor`
- [ ] Add `@AutoConfigureMockMvc` to tests using `MockMvc`
- [ ] Add `@AutoConfigureWebTestClient` to tests using `WebTestClient`
- [ ] Migrate `TestRestTemplate` → `RestTestClient` (or add `@AutoConfigureTestRestTemplate`)
- [ ] Update: `@PropertyMapping` imports → `org.springframework.boot.test.context`

### Build Configuration

- [ ] Update Maven to 3.9.0+
- [ ] Update CycloneDX Maven plugin to 3.0.0+
- [ ] Review optional dependency inclusion in uber jars
- [ ] Remove `<loaderImplementation>CLASSIC</loaderImplementation>` if present
- [ ] Remove `<executable>true</executable>` launch script config if present

### Verification

- [ ] Run `mvn clean package`
- [ ] Run full test suite
- [ ] Verify integration tests with TestContainers
- [ ] Test Spring Boot Actuator endpoints
- [ ] Verify health probes (`/actuator/health/liveness`, `/actuator/health/readiness`)
- [ ] Performance test with new defaults

### Post-Migration

- [ ] Review Spring Boot 4.0 release notes for additional features
- [ ] Consider adopting new Spring Framework 7.0 features
- [ ] Plan migration away from classic starters (if used)
- [ ] Plan migration away from `spring-boot-jackson2` module (if used)
- [ ] Update CI/CD pipelines for Java 17+ requirement
- [ ] Update deployment manifests (Servlet 6.1 containers)

## Common Pitfalls

1. **Classic starters**: Remember these are deprecated - plan migration to technology-specific starters
2. **Undertow**: Completely removed, no workaround - must use Tomcat or Jetty
3. **Jackson 3 packages**: Easy to miss `jackson-annotations` still using old group ID
4. **MongoDB properties**: Many moved to `spring.mongodb.*` but some remain in `spring.data.mongodb.*`
5. **Test configuration**: `@SpringBootTest` no longer auto-configures MockMVC/WebTestClient/TestRestTemplate
6. **JSpecify**: Null checker tools (SpotBugs, NullAway) may surface new warnings
7. **PropertyMapper**: Behavioral change with null handling - review usage
8. **Jersey + Jackson 3**: Incompatible - use `spring-boot-jackson2` module
9. **Health probes**: Now enabled by default - may affect non-Kubernetes deployments

## Performance Considerations

- **Modular starters**: Smaller JARs and faster startup with technology-specific starters
- **Spring Framework 7**: Performance improvements in core framework
- **Jackson 3**: Improved JSON processing performance
- **Virtual threads**: Consider enabling with Java 21+ (`spring.threads.virtual.enabled=true`)

**Note:** This document is intended to provide a comprehensive overview of the migration process from Spring Boot 3.5.x to 4.0.0, covering all major changes, code updates, configuration adjustments, and best practices to ensure a smooth transition. Always refer to the official documentation and release notes for the most up-to-date information.

## Resources

- [Spring Boot 4.0 Migration Guide](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-4.0-Migration-Guide)
- [Spring Boot 4.0 Release Notes](https://github.com/spring-projects/spring-boot/releases)
- [Spring Framework 7.0 Documentation](https://docs.spring.io/spring-framework/reference/)
- [Jackson 3 Migration Guide](https://github.com/FasterXML/jackson/wiki/Jackson-3.0-Migration-Guide)
- [Spring Boot Maven Plugin Reference](https://docs.spring.io/spring-boot/maven-plugin/reference/)

---
