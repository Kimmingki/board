package com.practice.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Image image;

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
