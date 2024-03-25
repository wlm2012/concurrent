package com.study.concurrent.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Table(name = "author")
@Comment("作者")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @Comment("主键")
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    private Long id;

    private String code;

    @OneToMany(mappedBy = "authorEntity")
    @ToString.Exclude
//    @BatchSize(size = 25)
    private Set<EbookEntity> ebookEntitySet;


}
