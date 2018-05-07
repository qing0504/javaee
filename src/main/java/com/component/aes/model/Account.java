package com.component.aes.model;

import com.component.aes.annotation.Decrypt;
import com.component.aes.annotation.Ignore;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author wanchongyang
 * @date 2018/5/6 下午2:10
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Ignore
    private Integer id;
    private String name;
    @Decrypt
    private BigDecimal balance;
    @Decrypt
    private String bankCode;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", bankCode='" + bankCode + '\'' +
                '}';
    }
}
