package elcom.ex1.LibraryBooks.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="AUTHOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @Column(name="AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name="AUTHOR_NAME")
    private String authorName;


    //@OneToMany(mappedBy = "author")
    //private Collection<Book> books;
}
