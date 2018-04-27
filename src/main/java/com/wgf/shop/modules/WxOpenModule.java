package com.wgf.shop.modules;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Accessors(chain = true)
@Table(name="table_wx")
public class WxOpenModule{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid",name="uuid")
    private String id;

    private String openId;

    private String seesionId;
}
