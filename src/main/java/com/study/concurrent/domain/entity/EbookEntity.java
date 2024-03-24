package com.study.concurrent.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "ebook")
@Comment("电子书表")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EbookEntity {

    @Id
    @Comment("主键")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    private Long id;

    @Comment("编号")
    @Column
    private String code;

    @Comment("书名")
    private String name;
}
