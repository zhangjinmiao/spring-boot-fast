package com.zjm.springbootfast.param;

import com.zjm.springbootfast.common.pagination.BasePageOrderParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <pre>
 * user 分页参数对象
 * </pre>
 *
 * @author zjm
 * @date 2022-09-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "user分页参数")
public class UserPageParam extends BasePageOrderParam {
    private static final long serialVersionUID = 1L;
}
