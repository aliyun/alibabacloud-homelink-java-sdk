package com.aliyun.iotx.api.sdk.business.homelink.dto.scene;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 场景日志查询的结果
 * @author juanshi.yt
 * @date 2018/04/11
 */
@Data
public class SceneLogListDTO implements Serializable {

    private static final long serialVersionUID = -4484067017416659058L;
    private List<SceneLogDetailDTO> logs;
    private Integer pageNo;
    private Integer pageSize;
    private Integer total;
}
