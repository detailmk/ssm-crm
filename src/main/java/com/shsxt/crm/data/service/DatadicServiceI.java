package com.shsxt.crm.data.service;

import com.shsxt.crm.base.BaseService;
import com.shsxt.crm.data.pojo.Datadic;

import java.util.List;
import java.util.Map;

/**
 * 基础数据Service
 */
public abstract class DatadicServiceI extends BaseService<Datadic> {

    /**
     * 根据数据名称查询基础数据
     *
     * @param dataDicName
     * @return
     */
    public abstract List<Map<Object, Object>> selectDatadicByDataDicName(String dataDicName);

}
