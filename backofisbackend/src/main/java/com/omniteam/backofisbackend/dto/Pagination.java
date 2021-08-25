package com.omniteam.backofisbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pagination {
    private int length;
    private int size;
    private int page;
    private int lastPage;
    private int startIndex;
    private int endIndex;
}
