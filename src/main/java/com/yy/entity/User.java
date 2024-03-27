package com.yy.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author young
 * @since 2024年03月16日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nick_name")
    private String nickName;

    @TableField("address")
    private String address;

    @TableField("sex")
    private String sex;

    @TableField("age")
    private Integer age;

    @TableField(exist = false)
    private List<TodoEvent> todoEvents;

    @TableField("imag")
    private  String imag;

}
