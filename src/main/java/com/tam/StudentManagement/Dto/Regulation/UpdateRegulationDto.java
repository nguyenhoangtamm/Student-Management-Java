package com.tam.StudentManagement.Dto.Regulation;

import java.time.LocalDate;

import com.tam.StudentManagement.Model.Regulation;

import lombok.Data;

@Data
public class UpdateRegulationDto {
    private String name;
    private String url;
    private Integer type;
    private Boolean isActive;
    private LocalDate effectiveDate;

    public UpdateRegulationDto(Regulation entity) {
        this.name = entity.getName();
        this.url = entity.getUrl();
        this.type = entity.getType();
        this.isActive = entity.getIsActive();
        this.effectiveDate = entity.getEffectiveDate();
    }
}