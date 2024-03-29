package nl.vandalist.model;

import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "book")
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BookDto {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column
    private String title;

    @NonNull
    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private AuthorDto author;

    @NonNull
    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private LanguageDto language;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<GenreDto> genres = new HashSet<>();

}
