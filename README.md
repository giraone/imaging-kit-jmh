# JMH Benchmark for imaging-kit

This is a [JMH](https://github.com/openjdk/jmh) project for benchmarking [imaging-kit](https://github.com/giraone/imaging-kit).

It tests the creation of JPEG thumbnails (size 160x120 pixels) from different JPEG input files.

- the test uses 5 different input files with sizes from 147 KB to 9.6 MB
- the input files are read from the file system
- the output files are written to the TMP directory
- the output files are deleted also in the loop
- the tests are performed with `@Fork(value = 1, warmups = 5)` and `@BenchmarkMode(Mode.AverageTime)`

## Build / Run

```shell
export JAVA_HOME=<Path-to-JDK>
export JAVA_HOME=<Path-to-JDK>
mvn clean package
$JAVA_HOME/bin/java -version
$JAVA_HOME/bin/java -jar target/benchmarks.jar
```

## Summary of results

- Machine 1: Windows 10 Pro (22H2) PC with i7-4910 (2.9 GHz) CPU

| Machine | JRE runtime | JDK source | Jar Version | File/Path | RESULT   | VARIANCE |
|:-------:|-----------:-|-----------:|------------:|----------:|---------:|---------:|
|    1    | Oracle 1.8  |          8 |       1.2.0 |   File    |    0,058 | +/-0,003 |
|    1    | OpenJDK 11  |          8 |       1.2.0 |   File    |    0,056 | +/-0,008 |
|    1    | OpenJDK 11  |         11 |       1.2.0 |   File    |    0,054 | +/-0,001 |
|    1    | OpenJDK 17  |         17 |       1.2.0 |   File    |    0,054 | +/-0,001 |
|    1    | OpenJDK 21  |         21 |       1.2.0 |   File    |    0,052 | +/-0,003 |
|    1    | OpenJDK 21  |         21 |       1.3.0 |   File    |    0,054 | +/-0,007 |
|    1    | OpenJDK 21  |         21 |       1.3.0 |   Path(t) |    0,048 | +/-0,002 |
|    1    | OpenJDK 21  |         21 |       1.3.0 |   Path(a) |    0,051 | +/-0,001 |

*File/Path*: Just a test, using `java.nio.file.Path` vs. `java.io.File`.
- Path(t) means, *Path* is used only in the test
- Path(a) means, *Path* is used also in the imaging-kit JAR

## Project creation

*The project was created using ...*

```shell
mvn archetype:generate \
-DinteractiveMode=false \
-DarchetypeGroupId=org.openjdk.jmh \
-DarchetypeArtifactId=jmh-java-benchmark-archetype \
-DgroupId=com.giraone.imaging \
-DartifactId=imaging-kit-jmh \
-Dversion=1.3.0
```