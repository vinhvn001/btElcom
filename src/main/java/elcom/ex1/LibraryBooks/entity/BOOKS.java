package elcom.ex1.LibraryBooks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="BOOK")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Books {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name="BOOK_NAME")
    private String bookName;

    @Column(name="AMOUNT")
    private Integer bookAmount;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="AUTHOR_NAME")
    private String authorName;


}
