# Usa a imagem do Maven com Java 25 para compilar a aplicação
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /app
COPY gestao-custos/ .
RUN mvn clean package -DskipTests

# Usa uma imagem do JRE Java 25 para rodar o .jar gerado
FROM eclipse-temurin:25-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]
