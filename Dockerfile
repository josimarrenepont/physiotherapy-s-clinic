FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p /app-clinic  # Criando o diretório de trabalho no contêiner
RUN bash -c 'touch /app.jar'
WORKDIR /app-clinic  # Definindo o diretório de trabalho
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
