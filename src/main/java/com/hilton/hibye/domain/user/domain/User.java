package com.hilton.hibye.domain.user.domain;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
