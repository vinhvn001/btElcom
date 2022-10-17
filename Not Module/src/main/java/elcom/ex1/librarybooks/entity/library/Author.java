package elcom.ex1.librarybooks.entity.library;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTHOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

    @Id
    @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHOR_NAME")
    private String authorName;



}
