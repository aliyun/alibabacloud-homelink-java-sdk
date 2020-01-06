package com.aliyun.iotx.api.sdk.business.homelink.dto.space;

import com.alibaba.fastjson.JSONArray;
import com.aliyun.iotx.api.sdk.dto.PageSearchDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author wb-lihuiyun
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpaceQueryDTO extends PageSearchDTO {
	/**
	 * 当前空间ID
	 */
	private String spaceId;
	/**
	 * 顶层空间ID
	 */
	private String rootSpaceId;
	/**
	 * 按照空间名字检索，精确匹配
	 */
	private String name;
	/**
	 * 按照空间名字检索，模糊匹配
	 */
	private String nameLike;
	/**
	 * 按照空间类型做检索
	 */
	private JSONArray typeCodeList;
}
