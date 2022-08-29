package elcom.ex1.LibraryBooks.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="BOOK")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "AMOUNT")
    private Integer bookAmount;

    @Column(name = "FIRST_LETTER")
    private String firstLetter;

    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID")
    private Author authorId;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category categoryId;

}
