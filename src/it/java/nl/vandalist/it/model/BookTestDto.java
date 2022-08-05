package nl.vandalist.it.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookTestDto {

    private Long id;
    private String title;
    private AuthorTestDto author;
    private LanguageTestDto language;
    private Set<GenreTestDto> genres = new HashSet<>();
}
