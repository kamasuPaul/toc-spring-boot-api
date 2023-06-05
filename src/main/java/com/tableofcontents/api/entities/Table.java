package com.tableofcontents.api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "tables")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;
    private String description;
    private String category;

    @Column(name = "user_id",nullable = false)
    private String userId;
    @Column(name = "image_url",nullable = true)
    private String imageUrl;
    @Column(name = "content_url",nullable = true)
    private String contentUrl;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime upatedAt;
}
