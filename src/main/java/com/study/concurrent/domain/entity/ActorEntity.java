package com.study.concurrent.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Set;


@Entity
@Table(name = "actor")
@Comment("演员")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActorEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("中文名")
    @Column(length = 30)
    private String name;

    @Comment("日文名")
    @Column(length = 30)
    private String japanName;

    @Comment("颜值")
    @Column(length = 3)
    private int appearance;

    @Comment("身材")
    @Column(length = 3)
    private int figure;

    @Comment("描述")
    @Column(length = 1024)
    private String description;

    @Comment("是否确认")
    @Column(length = 1)
    private int confirm;

    @Comment("是否存在")
    @Column(length = 1)
    private int exist;

    @Comment("照片")
    @Lob
    @Column(length = 160000)
    private byte[] photo;

    @ManyToMany(mappedBy = "actorSet"
//            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private Set<ResourcesEntity> resourcesSet;

}
