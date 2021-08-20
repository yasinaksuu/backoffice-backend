package com.omniteam.backofisbackend.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity  implements Serializable {


    @CreatedDate
    @Column(name = "created_date", updatable = false, nullable = false)
    private OffsetDateTime createdDate = OffsetDateTime.now();

    @LastModifiedDate
    @Column(name = "modified_date", nullable = false)
    private OffsetDateTime modifieDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;




}
