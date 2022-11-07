package com.elcom.library.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_name")
    private String bookName;

    @Column(name = "amount")
    private Integer bookAmount;

    @Column(name = "first_letter")
    private String firstLetter;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authorId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category categoryId;

    public void borrowedOne(){
        this.bookAmount --;
    }
    public void returnOne(){
        this.bookAmount ++;
    }

}
