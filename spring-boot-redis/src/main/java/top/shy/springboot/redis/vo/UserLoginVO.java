package top.shy.springboot.redis.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "用户登录vo")
public class UserLoginVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3212668594458387113L;

    @Schema(description = "用户ID")
    private Long pkId;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "令牌")
    private String accessToken;
}