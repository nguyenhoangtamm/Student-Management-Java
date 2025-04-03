package com.tam.StudentManagement.Dto.Common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationInfo {
    private int currentPage;
    private int perPage;
    private long total;
    private int lastPage;
}