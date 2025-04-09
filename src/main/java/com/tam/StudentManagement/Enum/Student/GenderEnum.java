package com.tam.StudentManagement.Enum.Student;


    public enum GenderEnum {
        MALE(0, "Nam"),
        FEMALE(1, "Nữ"),
        OTHER(2, "Khác");

        private final int value;
        private final String label;

        GenderEnum(int value, String label) {
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
        public static GenderEnum fromValue(int value) {
            for (GenderEnum gender : GenderEnum.values()) {
                if (gender.value == value) {
                    return gender;
                }
            }
            throw new IllegalArgumentException("Invalid gender value: " + value);
        }
    }


