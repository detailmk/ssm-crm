package com.shsxt.crm.data.service.impl;

import com.shsxt.crm.data.dao.DatadicMapper;
import com.shsxt.crm.data.service.DatadicServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 基础数据Service
 */
@Service
public class DatadicServiceImpl extends DatadicServiceI {

    @Autowired
    private DatadicMapper datadicMapper;

    /**
     * 根据数据名称查询基础数据
     *
     * @param dataDicName
     * @return
     */
    @Override
    public List<Map<Object, Object>> selectDatadicByDataDicName(String dataDicName) {
        return datadicMapper.selectDatadicByDataDicName(dataDicName);
    }

}
