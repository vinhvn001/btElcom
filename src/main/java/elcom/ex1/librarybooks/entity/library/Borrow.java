package elcom.ex1.librarybooks.entity.library;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrowed_list ")
public class Borrow implements Serializable {
    private static final long serialVersionUid = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User borrowerId;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookId;

    @Column(name = "borrowed_date")
    private Timestamp borrowedDate;

    @Column(name = "limit_date")
    private Date limitDate;

    @PrePersist
    void preInsert() {
        if (this.getBorrowedDate() == null)
            this.setBorrowedDate(new Timestamp(System.currentTimeMillis()));
    }

    @PreUpdate
    void preUpdate() {
        if (this.getBorrowedDate() == null)
            this.setBorrowedDate(new Timestamp(System.currentTimeMillis()));
    }
}
