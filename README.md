# TARPIT - A WEB APPLICATION SEEDED WITH VULNERABILITIES, ROOTKITS, BACKDOORS AND DATA LEAKS

Tarpit is a Java web application that is seeded with vulnerable conditions (OWASP based, Business Logic Flaws, Rootkits and Data Leaks) . Its main goal is to be an aid for security professionals to test with [Ocular](https://ocular.shiftleft.io), help web developers better understand the processes of securing web applications.

1. Common Vulnerabilities:
      * [ServletTarpit.java](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/SecuredServlet.java) - Common OWASP categorized vulnerabilities 
      * [DocumentTarpit.java](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/DocumentTarpit.java) - XXE based vulnerability
2. Insider/Backdoor based patterns:
      * [Insider.java](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/Insider.java)
3. Data Leaks:
      * [ServletTarpit.java](https://github.com/conikeec/tarpit/blob/master/src/main/java/io/shiftleft/tarpit/SecuredServlet.java) - Hardcoded credentials, sensitive data leaking on channels 

- - -

### Disclaimer

- - -

We do not take responsibility for the way in which any one uses this application. We have made the purposes of the application clear and it should not be used maliciously.

- - -

### Package creation

- - -

`mvn clean compile package`

NOTE: This package WAR file is intended NOT to run or be deployed in a web container. Its main goal is to be an aid for security professionals to test with [Ocular](https://ocular.shiftleft.io)

- - -
