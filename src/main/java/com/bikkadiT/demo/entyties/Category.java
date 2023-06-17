package com.bikkadiT.demo.entyties;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name = "category_title",length = 60, nullable = false)
    private String title;

    @Column(name = "category_desc",length = 50)
    private String description;

    private String coverImage;


}
