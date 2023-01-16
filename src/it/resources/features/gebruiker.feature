#language: nl
Functionaliteit: Werken met gebruiker

  Achtergrond:
    Gegeven de "gebruiker" sql is ingelezen

  Scenario: Haal alle gebruikers op
    Als de gebruiker alle gebruikers ophaalt
    Dan heeft hij 1 gebruikers
    En is de voornaam van deze gebruiker 'Fedde'

  Scenario: haal 1 gebruiker op
    Als de gebruiker de gebruiker met id 1 ophaalt
    Dan heeft hij een gebruiker met de volgende gegevens
      | id | voornaam | achternaam   | leeftijd | titel  | is_admin |
      | 1  | Fedde    | van der List | 12       | Tester | true     |

  Scenario: create een gebruiker
    Als de gebruiker een nieuwe gebruiker aanmaakt met de volgende gegevens
      | voornaam | achternaam | leeftijd | titel  | is_admin |
      | Test     | Test       | 25       | Tester | false    |
    Dan heeft hij een gebruiker met de volgende gegevens
      | voornaam | achternaam | leeftijd | titel  | is_admin |
      | Test     | Test       | 25       | Tester | false    |

  Scenario: update een gebruiker
    Als de gebruiker een gebruiker update
      | id | voornaam | achternaam | leeftijd | titel  | is_admin |
      | 1  | John     | Wick       | 26       | Tester | true     |
    Dan heeft hij een gebruiker met de volgende gegevens
      | id | voornaam | achternaam | leeftijd | titel  | is_admin |
      | 21 | John     | Wick       | 26       | Tester | true     |

  Scenario: create een gebruiker met hetzelfde id
    Als de gebruiker een nieuwe gebruiker aanmaakt met de volgende gegevens
      | voornaam | achternaam | leeftijd | titel  | is_admin |
      | xd       | xd         | 25       | Tester | false    |
    Dan heeft hij een gebruiker met de volgende gegevens
      | voornaam | achternaam | leeftijd | titel  | is_admin |
      | xd       | xd         | 25       | Tester | false    |
