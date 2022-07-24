package nl.vandalist.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "genre")
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NonNull
    @Column
    private String name;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    private Set<BookDto> books = new HashSet<>();
}
