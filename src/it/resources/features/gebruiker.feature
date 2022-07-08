#language: nl
Functionaliteit: Werken met gebruiker

  Scenario: Haal alle gebruikers op
    Als de gebruiker alle gebruikers ophaalt
    Dan heeft hij 1 gebruikers
    En is de voornaam van deze gebruiker 'Fedde'

  Scenario: haal 1 gebruiker op
    Als de gebruiker de gebruiker met id 1 ophaalt
    Dan heeft hij een gebruiker met de volgende gegevens
      | id | voornaam | achternaam   | leeftijd | titel  | is_admin |
      | 1  | Fedde    | van der List | 12       | Tester | true     |