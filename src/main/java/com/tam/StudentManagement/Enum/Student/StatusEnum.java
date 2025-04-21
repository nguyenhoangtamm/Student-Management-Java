package com.tam.StudentManagement.Enum.Student;

public enum StatusEnum {
    DANGHOC(0),
    BAOLUU(1),
    TOTNGHIEP(2),
    THOIHOC(3);

    private final int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
