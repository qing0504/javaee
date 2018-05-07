package com.component.aes.model;

import com.component.aes.annotation.Decrypt;
import com.component.aes.annotation.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wanchongyang
 * @date 2018/5/7 下午4:57
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Decrypt
public class BankInfo {
    @Ignore
    private Integer id;
    private String bankCode;
    private String cardNo;
    @Ignore
    private String userName;
    private Integer cardType;

    @Override
    public String toString() {
        return "BankInfo{" +
                "id=" + id +
                ", bankCode='" + bankCode + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", userName='" + userName + '\'' +
                ", cardType=" + cardType +
                '}';
    }
}
