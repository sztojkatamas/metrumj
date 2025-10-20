# 🔬 Benchmark Instrumentation Library for Java

A lightweight, annotation-driven benchmarking tool for measuring performance of Java methods — including CPU usage, memory consumption, threading, and more — during **unit tests** or **application runs**.

Supports:
- ✅ Plain Java applications
- ✅ Spring Boot services
- ✅ Static methods
- ✅ Compile-time weaving via AspectJ
- ✅ HTML report generation
- ✅ JUnit5 test context grouping

---

## 🚀 Features

| Metric            | Description                                       |
|------------------|---------------------------------------------------|
| `@Benchmark`      | Annotate any method (static or instance)         |
| Wall Time         | Total time spent executing the method            |
| CPU Time          | Total CPU time consumed by the executing thread  |
| CPU Usage (%)     | CPU efficiency across threads                    |
| Memory Used (KB)  | Heap memory used during execution                |
| Peak Threads      | Maximum thread count observed                    |
| Peak CPU Load (%) | Max process load sampled (from OS)               |
| HTML Report       | Automatically written to `benchmark-report.html`|

---

## 📦 Installation

### ⚙️ Gradle

#### 1. Apply Plugins

```groovy
plugins {
    id 'java'
    id 'io.freefair.aspectj' version '9.0.0'
}
```

#### 2. Add Dependencies

```groovy
dependencies {
    aspect project(':metrumj-lib') // or: implementation 'com.example:benchmark-lib:x.y.z'

    implementation 'org.aspectj:aspectjrt:1.9.19'
    aspect 'org.aspectj:aspectjweaver:1.9.19'
}
```

### ⚙️ Maven

#### 1. Enable AspectJ Compile-Time Weaving

```xml
<build>
  <plugins>
    <plugin>
      <groupId>io.freefair.maven</groupId>
      <artifactId>aspectj-compile</artifactId>
      <version>6.6.3</version>
      <executions>
        <execution>
          <goals>
            <goal>compile</goal>
            <goal>test-compile</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

#### 2. Add Dependencies

```xml
<dependencies>
  <dependency>
    <groupId>com.metrumj</groupId>
    <artifactId>metrumj-lib</artifactId>
    <version>1.0.0</version>
  </dependency>
  <dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.9.19</version>
  </dependency>
</dependencies>
```

---

## 🧪 JUnit Integration

Annotate test classes with `@ExtendWith(BenchmarkTestExtension.class)` to group metrics by test name.

```java
@ExtendWith(BenchmarkTestExtension.class)
class DemoServiceTest {

    @Test
    void benchmarkedTest() {
        myService.doHeavyWork();  // ✅ Metrics collected
    }
}
```

---

## 💡 Annotate Methods for Benchmarking

```java
@Benchmark
public void doHeavyWork() {
    // Heavy computation or business logic
}
```

This works on:
- Spring components
- Static utility methods
- Plain POJOs

---

## 🧼 Disable in Production

Metrics are only collected when this system property is set:

```bash
-Dbenchmark.enabled=true
```

In Gradle:

```groovy
tasks.named('test') {
    useJUnitPlatform()
    systemProperty "benchmark.enabled", "true"
}
```

In Maven:

```xml
<configuration>
  <systemPropertyVariables>
    <benchmark.enabled>true</benchmark.enabled>
  </systemPropertyVariables>
</configuration>
```

---

## 📄 Output Example

After test or app run, you’ll get:

```html
benchmark-report.html
```

Grouped by test name or `"Application"` if run outside test context.

---

## 📊 Sample Metrics

| Method                        | Wall Time | CPU Time | CPU Usage | Memory | Threads | Peak CPU |
|------------------------------|-----------|----------|-----------|--------|---------|----------|
| `MyService.doWork()`         | 425 ms    | 312 ms   | 73.4%     | 2048KB | 13      | 7.51%    |

---

## 🛠 Requirements

- Java 17+
- Gradle or Maven
- [AspectJ](https://www.eclipse.org/aspectj/) support

---

## 📈 Roadmap

- [x] HTML report generation
- [x] Peak CPU/thread tracking
- [ ] CSV/JSON export support
- [ ] GC impact metrics
- [ ] Method call count tracking

