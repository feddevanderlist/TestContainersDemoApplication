package nl.vandalist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class GebruikerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String voornaam;
    private String achternaam;
    private Integer leeftijd;
    private String titel;
    private Boolean isAdmin;

}
