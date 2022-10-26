# TestContainer demo project #

In this project I will be building a Springboot-api with a cassandra database.
I will be testing the api with the use of testcontainers.

The project will be used for teaching others how to work with test containers.

# Dutch information #

In dit project is het doel om alle api calls te testen. De volgende urls zijn aanwezig:

- /authors (GET,POST,PATCH, DELETE) en ?authorName=
- /books (GET,POST,PATCH, DELETE) en ?bookTitle=
- /countries (GET,POST,PATCH, DELETE) en ?countryName
- /gebruikers (GET,POST,PATCH, DELETE) en ?gebruikersnaam
- /genres (GET,POST,PATCH, DELETE) en ?genreName
- /language (GET,POST,PATCH, DELETE) en ?languageTitle

in de folder staat het database model [database model](dbmodel.png)

Voor GET all is een 200 met empty body de verwachting <br>
Voor het filteren op de naam geld dat het een lijst met alle overeen komende entries terug geeft <br>
Voor DELETE geld dat er een 202 terug komt
