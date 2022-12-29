package nl.vandalist.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
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
    private String firstName;
    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @OneToOne
    @JoinColumn(name = "origin_id", referencedColumnName = "id")
    private CountryDto countryOfOrigin;
    @NonNull
    @OneToOne
    @JoinColumn(name = "residence_id", referencedColumnName = "id")
    private CountryDto countryOfResidence;
    @NonNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "author_language",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<LanguageDto> languages = new HashSet<>();
}
