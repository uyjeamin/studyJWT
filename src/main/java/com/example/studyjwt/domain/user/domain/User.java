package com.example.studyjwt.domain.user.domain;

import com.example.studyjwt.domain.user.domain.time.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Getter
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

    @Enumerated(EnumType.STRING)
    private Role role;

}
