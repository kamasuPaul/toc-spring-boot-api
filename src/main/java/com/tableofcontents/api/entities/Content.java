package com.tableofcontents.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@jakarta.persistence.Table(name = "contents")
@ToString
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(name = "page_no")
    private String pageNo;

    @Column(nullable = false)
    private int ordering;
    @Column(nullable = false)
    private int level;
    @Column(name = "group_uuid",nullable = false)
    private String groupUuid;
    @Column(name = "parent_id", updatable = false)
    private String parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;
    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedAt;

}
