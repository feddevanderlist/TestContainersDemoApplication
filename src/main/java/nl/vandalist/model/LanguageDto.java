package nl.vandalist.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Builder
@Entity
@Table(name = "language")
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NonNull
    @Column
    private String name;

    @ManyToMany(mappedBy = "languages", cascade = CascadeType.ALL)
    private Set<AuthorDto> authors = new HashSet<>();
}
