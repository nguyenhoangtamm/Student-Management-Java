package com.tam.StudentManagement.Enum.Student;

public enum ContractStatusEnum {
    CHUA_KY(0, "Chưa ký hợp đồng"),
    DA_KY(1, "Đã ký hợp đồng"),
    HET_HAN(2, "Hết hạn hợp đồng"),
    HUY_BO(3, "Hủy bỏ hợp đồng");

    private final int value;
    private final String label;

    ContractStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static ContractStatusEnum fromValue(int value) {
        for (ContractStatusEnum status : ContractStatusEnum.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid contract status value: " + value);
    }
}

