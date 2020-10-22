package com.boom.success.bo;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Data
@Table("project")
public class Project {
    @Id
    private Integer id;

    @Column
    private String name;
}
