package com.tam.StudentManagement.Dto.Regulation;

import lombok.Data;

import java.time.LocalDate;

import com.tam.StudentManagement.Model.Regulation;

@Data
public class CreateRegulationDto {
    private Integer id;
    private String name;
    private String url;
    private Integer type;
    private Boolean isActive;
    private LocalDate effectiveDate;

    public CreateRegulationDto(Regulation entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.url = entity.getUrl();
        this.type = entity.getType();
        this.isActive = entity.getIsActive();
        this.effectiveDate = entity.getEffectiveDate();
    }
}