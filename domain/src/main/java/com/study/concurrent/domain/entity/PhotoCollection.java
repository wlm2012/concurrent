package com.study.concurrent.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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


    @Comment("图片")
    @Column(length = 160000)
    private byte[] photos;


}
