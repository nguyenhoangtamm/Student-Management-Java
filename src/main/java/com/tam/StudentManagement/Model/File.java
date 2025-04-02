package com.tam.StudentManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class File extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String path;

    @Column(length = 255)
    private String type;

    @Column(name = "mime_type", length = 255)
    private String mimeType;

    private Integer size;

    @Column(name = "fileable_type", length = 255)
    private String fileableType;

    @Column(name = "fileable_id")
    private Long fileableId;
}