package com.shsxt.crm.data.controller;

import com.shsxt.crm.data.service.DatadicServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 基础数据Controller
 */
@Controller
@RequestMapping("/datadic")
public class DatadicController {

    @Autowired
    private DatadicServiceI datadicService;

    /**
     * 根据数据名称查询基础数据
     *
     * @param dataDicName
     * @return
     */
    @RequestMapping("/selectDatadicByDataDicName")
    @ResponseBody
    public List<Map<Object, Object>> selectDatadicByDataDicName(String dataDicName) {
        return datadicService.selectDatadicByDataDicName(dataDicName);
    }

}
