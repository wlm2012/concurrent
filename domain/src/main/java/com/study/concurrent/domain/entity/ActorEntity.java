package com.study.concurrent.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "actor")
@Comment("演员")
@Getter
@Setter
@Builder
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

    @ElementCollection
    @SQLRestriction("photo_type='1'")
    @CollectionTable(name = "photo",
            joinColumns = {@JoinColumn(name = "ref_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
            })
    private List<PhotoCollection> photos;

    public void setPhotos(Set<byte[]> photos) {
        if (CollectionUtils.isEmpty(photos)) {
            return;
        }
        ArrayList<PhotoCollection> photoCollections = new ArrayList<>();
        photos.forEach(e -> photoCollections.add(PhotoCollection.builder()
                .photos(e)
                .build()));
        this.photos = photoCollections;
    }

    public static class ActorEntityBuilder {
        public ActorEntityBuilder photos(Set<byte[]> photos) {
            if (CollectionUtils.isEmpty(photos)) {
                return this;
            }

            ArrayList<PhotoCollection> photoCollections = new ArrayList<>();
            photos.forEach(e -> photoCollections.add(PhotoCollection.builder()
                    .photos(e)
                    .build()));
            this.photos = photoCollections;
            return this;
        }
    }

    @ManyToMany(mappedBy = "actorSet"
//            , cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    @ToString.Exclude
    private Set<ResourcesEntity> resourcesSet;

}
