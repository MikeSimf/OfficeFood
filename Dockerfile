FROM adoptopenjdk/openjdk8:ubi
ARG JAR_FILE=target/*.jar
#ENV BOT_NAME=simf_office_buns_bot
#ENV BOT_TOKEN=5103373845:AAEKGuolzLCGE5lUhvZWafcE4BnG2lKC1mQ
#ENV OFFICE_DB_PASSWORD=postgres
#ENV OFFICE_DB_USERNAME=masterkey
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-Dspring.datasource.password=${OFFICE_DB_PASSWORD}", "-Dspring.datasource.username=${OFFICE_DB_USERNAME}","-jar", "/app.jar"]

