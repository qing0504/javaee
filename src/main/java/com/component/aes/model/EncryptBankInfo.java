package com.component.aes.model;

import com.component.aes.annotation.Encrypt;
import com.component.aes.annotation.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wanchongyang
 * @date 2018/5/7 下午5:00
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Encrypt
public class EncryptBankInfo {
    @Ignore
    private Integer id;
    private String bankCode;
    private String cardNo;
    @Ignore
    private String userName;
    private String cardType;

    @Override
    public String toString() {
        return "EncryptBankInfo{" +
                "id=" + id +
                ", bankCode='" + bankCode + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", userName='" + userName + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
