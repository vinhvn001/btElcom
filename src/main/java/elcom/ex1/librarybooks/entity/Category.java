package elcom.ex1.librarybooks.entity;

import lombok.*;


import javax.persistence.*;


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
