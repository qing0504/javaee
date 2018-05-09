package com.component.aes.model;

import com.component.aes.annotation.Encrypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wanchongyang
 * @date 2018/5/6 下午2:10
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class EncryptAccount extends BaseEntity{
    private String name;
    @Encrypt
    private String balance;
    @Encrypt
    private String bankCode;

    @Override
    public String toString() {
        return "EncryptAccount{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", balance='" + balance + '\'' +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}

