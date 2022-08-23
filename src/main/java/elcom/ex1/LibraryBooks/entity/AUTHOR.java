package elcom.ex1.LibraryBooks.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="AUTHOR")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AUTHOR {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(name="AUTHOR_NAME")
    private String author_name;


}
