# Utiliser une image Java 17 comme base
FROM eclipse-temurin:17-jdk-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier Maven Wrapper
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Télécharger les dépendances Maven
RUN ./mvnw dependency:go-offline

# Copier le code source
COPY src ./src

# Compiler l'application
RUN ./mvnw package -DskipTests

# Exposer le port 8183
EXPOSE 8183

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "target/GestionnaireFormation-0.0.1-SNAPSHOT.jar"] 