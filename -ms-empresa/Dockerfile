# Imagem base com JDK
FROM openjdk:23-jdk-slim

# Diretório de trabalho no container
WORKDIR /app

# Copia o arquivo JAR gerado para o container
COPY target/empresa-0.0.1-SNAPSHOT.jar app.jar

# Porta exposta (padrão do Spring Boot)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]