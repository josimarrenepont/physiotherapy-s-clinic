FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar
RUN mkdir -p /app-clinic  # Certifique-se de criar o diretório se necessário
WORKDIR /app-clinic  # Defina o diretório de trabalho no contêiner
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
