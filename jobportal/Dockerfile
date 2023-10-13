FROM maven:latest AS build
WORKDIR /app
COPY . /app
RUN mvn clean package -Dmaven.test.skip

FROM eclipse-temurin
WORKDIR /app
COPY --from=build /app/target/jobportal-0.0.1-SNAPSHOT.jar ./app.jar

# Install MySQL client to connect to the locally installed MySQL server
RUN apt-get update && apt-get install -y default-mysql-client

# Expose the port your Spring Boot application will run on
EXPOSE 8080

# Set environment variables for connecting to your local MySQL server
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/jobportal
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# Start the Spring Boot application
CMD ["java", "-jar", "app.jar"]


