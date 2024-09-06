package com.example.studyjwt.domain.user;

import jakarta.persistence.*;


@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)",name = "accont_id")
    private String accontId;

    @Column(columnDefinition = "VARCHAR(50)",nullable = false)
    private String password;

    @Column(name = "divice_token")
    private String deviceToken;

    @Column(columnDefinition = "VARCHAR(50)",nullable = false)
    private String email;


}
