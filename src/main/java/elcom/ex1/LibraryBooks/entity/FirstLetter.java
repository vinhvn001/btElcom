package elcom.ex1.LibraryBooks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="BOOK_LETTER_ORDINAL")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FirstLetter {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name="LETTER")
    private String firstLetter;
}
