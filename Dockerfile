FROM maven

MAINTAINER Phan Vu Dinh (itphanvudinh@gmail.com)

COPY . /tmp/workspace

EXPOSE 8080

CMD cd /tmp/workspace && mvn clean && mvn install && java -jar target/TDD-0.0.1-SNAPSHOT.jar


