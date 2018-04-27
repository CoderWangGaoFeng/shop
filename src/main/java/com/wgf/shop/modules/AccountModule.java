package com.wgf.shop.modules;

import com.wgf.shop.modules.enmu.AccountStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 用户帐号信息
 */
@Entity
@Table(name="table_account")
@Data
@Accessors(chain = true)
public class AccountModule {

    @Id
    @GenericGenerator(name="uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    /**
     * 用户名
     */
    @NotNull
    private String userName;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 帐号状态
     */
    private AccountStatus status;

    @OneToOne(cascade={javax.persistence.CascadeType.ALL})
    @JoinColumn(name="info_id")
    private AccountInfoModule info;


}
