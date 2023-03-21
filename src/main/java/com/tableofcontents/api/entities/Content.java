package com.tableofcontents.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@jakarta.persistence.Table(name = "contents")
@ToString
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(name = "page_no")
    private String pageNo;
    @Column(name = "group_uuid")
    private String groupUuid;
    @Column(name = "parent_id",nullable = false,updatable = false)
    private String parentId;

    @ManyToOne()
    @JoinColumn(name = "table_id",referencedColumnName = "id")
    private Table table;
    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date upatedAt;

}
