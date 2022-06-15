package com.example.tanyue.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "account_info")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator="postgresql_id")
    @SequenceGenerator(sequenceName="table_name_account_id_seq", name="postgresql_id")
    @Column(name = "account_id")
    private Long id;

    @Column(name = "login_name")
    @NotBlank(message="必须有姓名")
    private String name;
    @Column(name = "login_password")
    @NotBlank(message = "必须输入密码")
    private String password;

    private Date createTime;

    //TODO: HIBERNATE 时间修改
    private Date modifyTime;

    private int accountType;

    public User(String name, String password, int accountType) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.accountType==1){
            return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
        }else {
            return Arrays.asList(new SimpleGrantedAuthority("VISITOR"));
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    public void prePersist() {
        this.createTime = new Date();
    }
}
