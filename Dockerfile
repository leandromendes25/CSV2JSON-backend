# Etapa 1: build
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# Copia só arquivos necessários primeiro (cache de dependências)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Dá permissão
RUN chmod +x gradlew

# Baixa dependências (melhora cache do Docker)
RUN ./gradlew dependencies --no-daemon

# Agora copia o resto do projeto
COPY . .

# Build do projeto
RUN ./gradlew build -x test --no-daemon

# Etapa 2: runtime (mais leve)
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Copia o JAR final
COPY --from=build /app/build/libs/*.jar app.jar

ENV PORT=8080

EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]