package com.study.concurrent.domain.entity;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;


@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhotoCollection {


    @Comment("图片")
    @Column(length = 160000)
    private byte[] photos;


}
