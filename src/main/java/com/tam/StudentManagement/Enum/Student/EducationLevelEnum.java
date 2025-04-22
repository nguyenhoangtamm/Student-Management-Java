package com.tam.StudentManagement.Enum.Student;

public enum EducationLevelEnum {
    CAO_DANG(0, "Cao đẳng"),
    DAI_HOC(1, "Đại học"),
    THAC_SI(2, "Thạc sĩ"),
    TIEN_SI(3, "Tiến sĩ");

    private final int value;
    private final String label;

    EducationLevelEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static EducationLevelEnum fromValue(int value) {
        for (EducationLevelEnum level : EducationLevelEnum.values()) {
            if (level.value == value) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid education level value: " + value);
    }
}

