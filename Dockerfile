FROM private-registry.sohucs.com/domeos-pub/maven:jdk-11
COPY . /code
WORKDIR /code
RUN cd /code && mvn clean package -Dmaven.test.skip=true && mv /code/target/simple-task-1.0.0-SNAPSHOT.jar /code/simple-task.jar
ENTRYPOINT ["java", "-Xms512m", "-Xmx512m","-XX:MaxMetaspaceSize=350m","-XX:+UnlockExperimentalVMOptions","-XX:+UseZGC","-Xlog:gc*","-Xlog:gc:/code/logs/gc.log", "-jar", "./data-lake.jar","--spring.profiles.active=test"]