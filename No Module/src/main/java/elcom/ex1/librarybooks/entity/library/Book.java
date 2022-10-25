package elcom.ex1.librarybooks.entity.library;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="BOOK")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

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

    public void borrowedOne(){
        this.bookAmount --;
    }
    public void returnOne(){
        this.bookAmount ++;
    }

}
