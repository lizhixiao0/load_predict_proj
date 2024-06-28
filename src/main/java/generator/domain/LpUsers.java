package generator.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName lp_users
 */
@TableName(value ="lp_users")
@Data
public class LpUsers implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(value = "username")
    private String username;

    /**
     * 
     */
    @TableField(value = "password")
    private String password;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "phone_number")
    private String phone_number;

    /**
     * 
     */
    @TableField(value = "role")
    private Object role;

    /**
     * 
     */
    @TableField(value = "created_at")
    private Date created_at;

    /**
     * 
     */
    @TableField(value = "updated_at")
    private Date updated_at;

    /**
     * 
     */
    @TableField(value = "status")
    private Object status;

    /**
     * 
     */
    @TableField(value = "profile_info")
    private Object profile_info;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}