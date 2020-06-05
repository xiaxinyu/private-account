package com.account.persist.model;

import lombok.*;

/**
 * Created by Summer.Xia on 9/22/2015.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User {
    private String userName;
    private String password;
    private String name;
}
