package com.tam.StudentManagement.Dto.Common;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto<T> {
    private List<T> data;
    private PaginationInfo pagination;
}
