# Etapa 1 — Build con Maven + JDK
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar POM y descargar dependencias para acelerar builds
COPY pom.xml .
RUN mvn -q -e dependency:go-offline

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2 — Imagen final sólo con JRE (más liviana)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el JAR generado en la etapa de build
COPY --from=build /app/target/monitoreo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Usamos el perfil prod en contenedor (lo refuerzo desde Docker Compose igual)
ENTRYPOINT ["java", "-jar", "app.jar"]
