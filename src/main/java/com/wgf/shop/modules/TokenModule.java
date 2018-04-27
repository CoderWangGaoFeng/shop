package com.wgf.shop.modules;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 登录token
 */
@Entity
@Table(name="table_token")
@Data
@Accessors(chain = true)
public class TokenModule {

    @Id
    @GenericGenerator(name="uuid",strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;

    private String token;

    /**
     * 登录用户id
     */
    private String accountId;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 逾期时间
     */
    private Timestamp overdueTime;
}
