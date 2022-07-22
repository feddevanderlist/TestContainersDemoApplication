package nl.vandalist.model;

import lombok.*;


import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@Table(name = "book")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column
    String title;

    @NonNull
    @OneToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    AuthorDto author;

    @OneToMany(targetEntity = GenreDto.class)
    Set<GenreDto> genres;

    @NonNull
    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    LanguageDto language;

}
