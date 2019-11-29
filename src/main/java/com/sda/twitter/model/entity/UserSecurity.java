package com.sda.twitter.model.entity;

import com.sda.twitter.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usersecurity")
public class UserSecurity extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String role;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date blockedDate;

    public Date getBlockedDate() {
        return blockedDate;
    }
}
