package com.metacoding.studysecurityjwt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String roles; // USER, ADMIN과 같이 저장

    // role이 하나일 경우에는 해당 메소드 필요없음. 또한 role이 여러개여도 role테이블을 한 개 더 만든다면 필요없다.
    // 하지만 role 테이블만드는 것 보다 아래 방식이 편리.(비정규화..?)
    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
