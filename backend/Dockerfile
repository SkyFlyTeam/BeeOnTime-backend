# Usar a imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Definir diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo JAR do build para dentro do container
COPY target/backend.jar app.jar

# Expor a porta da aplicação (deve coincidir com o application.properties)
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]
