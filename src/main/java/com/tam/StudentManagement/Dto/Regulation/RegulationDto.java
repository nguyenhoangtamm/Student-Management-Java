package com.tam.StudentManagement.Dto.Regulation;

import java.time.LocalDate;

import lombok.Data;
import com.tam.StudentManagement.Model.Regulation;

@Data
public class RegulationDto {
    private Integer id;
    private String name;
    private String url;
    private Integer type;
    private Boolean isActive;
    private LocalDate effectiveDate;


    public RegulationDto(Regulation entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.url = entity.getUrl();
        this.type = entity.getType();
        this.isActive = entity.getIsActive();
        this.effectiveDate = entity.getEffectiveDate();
    }
}