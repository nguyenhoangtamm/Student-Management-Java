package com.tam.StudentManagement.Enum.Student;

public enum EducationTypeEnum {
    CHINH_QUY(1, "Chính quy"),
    LIEN_THONG(2, "Liên thông"),
    TAI_CHUC(3, "Tại chức"),
    TU_XA(4, "Từ xa");

    private final int value;
    private final String label;

    EducationTypeEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static EducationTypeEnum fromValue(int value) {
        for (EducationTypeEnum type : EducationTypeEnum.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid education type value: " + value);
    }
}

