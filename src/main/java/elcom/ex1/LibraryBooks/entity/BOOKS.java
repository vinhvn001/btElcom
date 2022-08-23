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
public class BOOKS {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name="BOOK_NAME")
    private String book_name;

    @Column(name="AMOUNT")
    private Integer book_amount;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="AUTHOR_NAME")
    private String author_name;


}
