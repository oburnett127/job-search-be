FROM openjdk:19

COPY target/jobsearch-0.0.1-SNAPSHOT.jar /usr/src/jobsearch-0.0.1-SNAPSHOT.jar

WORKDIR /usr/src

CMD ["java", "-jar", "jobsearch-0.0.1-SNAPSHOT.jar"]
