FROM openjdk:17

# Diretório de trabalho no contêiner
WORKDIR /app

# Copie o código da aplicação para o contêiner
COPY api-server.jar /api-server.jar

CMD ["java", "-jar", "/api-server.jar"]
