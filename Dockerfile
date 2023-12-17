# Use the official OpenJDK 11 base image
FROM openjdk:11-jre-slim
FROM maven:3.8.5-openjdk-17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY . .

RUN mvn clean install

# Expose port 8080
EXPOSE 8080
# Specify the command to run on container startup
CMD mvn spring-boot:run