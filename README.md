![tarpit-logo](tarpit-logo.png)

# Tarpit Java
### A web application seeded with vulnerabilities, rootkits, backdoors and data leaks

Tarpit is a Java web application that is seeded with vulnerable conditions (OWASP based, Business Logic Flaws, Rootkits and Data Leaks). Its main goal is to be an aid for security professionals to test with [Ocular](https://ocular.shiftleft.io), help web developers better understand the processes of securing web applications.


### Common Vulnerabilities

| File | Description |
| --- | --- |
| [`ServletTarpit.java`](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/SecuredServlet.java) | Common OWASP categorized vulnerabilities | 
| [`DocumentTarpit.java`](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/DocumentTarpit.java) | XXE based vulnerability |

### Insider Attacks/Backdoor Patterns

| File | Description |
| --- | --- |
| [`Insider.java`](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/Insider.java) | |

### Data Leaks

| File | Description |
| --- | --- |
| [`ServletTarpit.java`](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/SecuredServlet.java) | Hardcoded credentials, sensitive data leaking on channels |

## Building

Tarpit uses Maven build system. Make sure you have maven installed on your system. Then use the following command to build the application,

```
mvn clean compile package
```

Note: Tarpit application uses sun.misc libraries which are confirmed to work with Java 1.8. In the event of build errors, please see: https://stackoverflow.com/a/52652249

The `servlettarpit.war` artifact is generated in the `target` directory which can be used for further analysis.

> :information_source: This packaged WAR file is intended NOT to run or be deployed in a web container. Its main goal is to be an aid for security professionals to test with [Ocular](https://ocular.shiftleft.io)

- - -

## :warning: Disclaimer

We do not take responsibility for the way in which any one uses this application. We have made the purposes of the application clear and it should not be used maliciously.
