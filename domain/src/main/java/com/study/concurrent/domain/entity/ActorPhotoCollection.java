package com.study.concurrent.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.Comment;

@Embeddable
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ActorPhotoCollection extends PhotoCollection {


    @Comment("图片类型")
    @Builder.Default
    private String photoType = "1";


    @Comment("图片")
    @Column(length = 160000)
    private byte[] photos;


}
