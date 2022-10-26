# TestContainer demo project #

In this project I will be building a Springboot-api with a cassandra database.
I will be testing the api with the use of testcontainers.

The project will be used for teaching others how to work with test containers.

# Dutch information #

# Het doel #

Het doel is om een test line coverage van 85% te krijgen.
Het bestand [CoverageProfile.run.xml](CoverageProfile.run.xml) kun je importeren in je profile links bovenin in intellij
En dan de testen aftrappen met de run with coverage optie. Dit is een play button met een schild.
Dan verschijnt er op rechts een resultaat en moet de all folder 85% line coverage hebben.
[foto voor coverage en de knop](overzichtIntellijSchermen.png)

## Informatie over het project ##

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

### Het gebruik van dit project ##

in de [postgress](/src/it/resources/postgress) folder staan 3 sql bestanden.
<br> De [insert.sql](/src/it/resources/postgress/insert.sql) is het bestand dat de tabellen in de database plaatst.
<br> Naast het gebruiken van de POST calls can je in de scripts folder sql bestanden plaatsen. Deze worden tijdens het
opstarten ingelezen.
<br> Hierdoor is het mogelijk om gebruik te maken van je bestaande test database of een hele concrete dataset te
generen.

### De verschillende folders en bestanden ###

1. helper folder
    1. In de helper folder zet je stukjes code die op andere plekken verwarring opleveren <br>
       in mijn geval heb ik daar de conversies van een gherkin tabel naar een object erin gezet.

2. model folder
    1. in deze folder staan de DTO's (Data Transfer Object) van elke benodigde database tabel.
    2. Deze verzend je naar de api en krijg je ook weer terug van de api.
    3. Voor deze DTO's is gebruik gemaakt van LOMBOK hierdoor worden de getters, setters, builders and constructors
       automagisch gegeneerd.
3. step folder
    1. In de step folder plaats je de code voor je feature files in een class met bij voorkeur dezelfde naam als je
       feature file.2
    2. Om te zorgen dat je over stappen heen kan gaan is de `State` class geïntroduceerd hierin kun je je response
       tussentijds opslaan.
4. support folder
    1. Hierin staan de services die communiceren met de api.
    2. Elke service moet een `@service` tag boven in het bestand hebben (zie de voorbeelden)
    3. Dit alles gaat op basis van rest assured zie ook https://www.baeldung.com/rest-assured-tutorial
5. repository folder
    1. Mocht jij nou handmatig je database willen controleren dan kun je vanuit deze repository dat doen.
    2. Voeg de repository toe:

```java
@Autowired
AuthorRepository authorRepository;
```

Hiermee kun je dan de `authorRepository.` en dan wat je wil doen;
