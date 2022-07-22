package nl.vandalist.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "author")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "first_name")
    String firstName;
    @NonNull
    @Column(name = "last_name")
    String lastName;

    @NonNull
    @OneToOne
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    CountryDto countryOfOrigin;
    @NonNull
    @OneToOne
    @JoinColumn(name = "Residence_id", referencedColumnName = "id")
    CountryDto countryOfResidence;
    @NonNull
    @Column(name = "date_of_birth")
    Date dateOfBirth;

    @OneToMany(targetEntity = LanguageDto.class)
    Set<LanguageDto> languages;
}
