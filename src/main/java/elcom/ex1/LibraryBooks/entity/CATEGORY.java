package elcom.ex1.LibraryBooks.entity;

import lombok.*;


import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name="CATEGORY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {
    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    //@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)

    // private Collection<Book> books;
}
