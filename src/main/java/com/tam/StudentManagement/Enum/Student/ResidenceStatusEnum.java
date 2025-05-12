package com.tam.StudentManagement.Enum.Student;

public enum ResidenceStatusEnum {
    TRONG_KTX(1, "Trong KTX"),
    NGOAI_TRU(2, "Ngoại trú"),
    O_NHA(3, "Ở nhà");

    private final int value;
    private final String label;

    ResidenceStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static ResidenceStatusEnum fromValue(int value) {
        for (ResidenceStatusEnum status : ResidenceStatusEnum.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid residence status value: " + value);
    }
}

