package com.rakeshv.hibernate.models.inheritance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ItemBook extends Item {

    @Column(length = 20)
    private String isbn;

    @Column(name = "nb_of_pages")
    private Integer nbOfPages;

    @Column(name = "publication_date")
    private LocalDate publicationDate;
}
