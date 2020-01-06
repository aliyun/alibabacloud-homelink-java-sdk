package com.aliyun.iotx.api.sdk.business.homelink.dto.user;


import java.io.Serializable;
import java.util.List;

/**
 * @author qianxing.hp
 * @date 19-01-25.
 */
public class ThirdAccountListDTO implements Serializable {

    private static final long serialVersionUID = 888315377488838879L;

    private List<ThirdAccountMeta> thirdAccountMetaList;

    private Integer total;

    private Integer pageNo;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }


    public List<ThirdAccountMeta> getThirdAccountMetaList() {
        return thirdAccountMetaList;
    }

    public void setThirdAccountMetaList(List<ThirdAccountMeta> thirdAccountMetaList) {
        this.thirdAccountMetaList = thirdAccountMetaList;
    }
}
