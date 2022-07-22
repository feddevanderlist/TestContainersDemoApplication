package nl.vandalist.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@Table(name = "gebruiker")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class GebruikerDto {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column
    private String voornaam;
    @NonNull
    @Column
    private String achternaam;
    @NonNull
    @Column
    private Integer leeftijd;
    @NonNull
    @Column
    private String titel;
    @Column(name = "is_admin")
    private boolean isAdmin;

}
