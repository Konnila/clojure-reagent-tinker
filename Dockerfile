FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/siilievents.jar /siilievents/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/siilievents/app.jar"]
