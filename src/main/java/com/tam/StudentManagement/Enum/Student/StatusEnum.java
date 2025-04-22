package com.tam.StudentManagement.Enum.Student;

public enum StatusEnum {
    DANGHOC(0, "Đang học"),
    BAOLUU(1, "Bảo lưu"),
    TOTNGHIEP(2, "Tốt nghiệp"),
    THOIHOC(3, "Thôi học");

    private final int value;
    private final String label;

    StatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    // Optional: Tìm theo value
    public static StatusEnum fromValue(int value) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status value: " + value);
    }
}
