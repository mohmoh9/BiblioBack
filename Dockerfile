# =========================
# 1️⃣ Build stage
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Télécharger les dépendances (cache Docker)
RUN mvn dependency:go-offline -B

# Copier le code source
COPY src src

# Compiler le projet
RUN mvn clean package -DskipTests

# =========================
# 2️⃣ Runtime stage
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copier le JAR depuis l'étape build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port Spring Boot
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
