package com.shsxt.crm.data.dao;

import com.shsxt.crm.base.BaseDao;
import com.shsxt.crm.data.pojo.Datadic;

import java.util.List;
import java.util.Map;

/**
 * 基础数据Mapper
 */
public interface DatadicMapper extends BaseDao<Datadic> {

    /**
     * 根据数据名称查询基础数据
     *
     * @param dataDicName
     * @return
     */
    List<Map<Object, Object>> selectDatadicByDataDicName(String dataDicName);

}
