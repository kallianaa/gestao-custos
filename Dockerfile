# Usa a imagem do Maven para compilar a aplicação
FROM maven:3.8.6-eclipse-temurin-25 AS builder
WORKDIR /app
COPY  gestao-custos/ .
RUN mvn clean package -DskipTests

# Usa uma imagem do JDK para rodar o .jar gerado
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY -from=builder /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]