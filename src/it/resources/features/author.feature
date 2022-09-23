#language: nl
Functionaliteit: Werken met author

  Achtergrond:
    Gegeven De "author" sql is ingelezen

  Scenario: get all authors
    Als de gebruiker alle authors ophaalt
    Dan heeft hij 1 author


  Scenario: get 1 author
    Als de gebruiker de author met id 1 ophaalt
    Dan heeft hij een author met de volgende gegevens
      | id | first_name | last_name | date_of_birth              | origin_id | origin_country_name | origin_capital | residence_id | residence_country_name | residence_capital |
      | 1  | John       | Wick      | 2022-07-29 15:43:20.000000 | 1         | America             | new york       | 1            | America                | new york          |
