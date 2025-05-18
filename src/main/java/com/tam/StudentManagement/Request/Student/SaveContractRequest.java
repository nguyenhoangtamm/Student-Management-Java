package com.tam.StudentManagement.Request.Student;
import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class SaveContractRequest {
    private Integer dormitoryId;

    @NotBlank(message = "Price cannot be blank")
    private String price;

    @NotBlank(message = "Room cannot be blank")
    private String room;

    @NotNull(message = "Status cannot be null")
    private Integer status;

    
}
