package com.study.concurrent.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Entity
@Table(name = "resources")
@Comment("资源")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_gen")
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("类型")
    @Column(length = 2)
    private String type;


    @Comment("资源名称")
    private String name;

    @Comment("路径")
    @Column(length = 1)
    private String path;

    @Comment("是否存在")
    @Column(length = 1)
    private int exist;

    @ManyToMany(
//            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @JoinTable(
            name = "actor_resources",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "resources_id")
    )
    private Set<ActorEntity> actorSet;

}
