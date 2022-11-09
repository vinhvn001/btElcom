package com.elcom.loan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "borrowed_list" , schema = "public")
public class Borrow implements Serializable {
    private static final long serialVersionUid = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "borrowed_date")
    private Timestamp borrowedDate;

    @Column(name = "limit_date")
    private Date limitDate;

    @Column(name = "return_date")
    private Date returnDate;

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
