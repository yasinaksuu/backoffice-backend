package com.omniteam.backofisbackend.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.OffsetDateTime;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity  implements Serializable {


    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    private OffsetDateTime createdDate = OffsetDateTime.now();

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    private OffsetDateTime modifiedDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;




}
