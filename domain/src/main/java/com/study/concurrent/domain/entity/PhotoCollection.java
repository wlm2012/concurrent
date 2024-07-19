package com.study.concurrent.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.*;
import org.hibernate.annotations.Comment;


@Embeddable
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhotoCollection {

    @Comment("图片类型")
    private String photoType;


    @Lob
    @Comment("图片")
    @Column(columnDefinition = "mediumblob")
    private byte[] photos;


}
