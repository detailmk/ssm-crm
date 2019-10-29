package com.shsxt.crm.base.task;

import com.shsxt.crm.customer.service.CustomerLossServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 收集预流失客户信息定时任务
 */
@Component
public class PrepareLossCustomerTask {

    public static Logger logger = LoggerFactory.getLogger(PrepareLossCustomerTask.class);

    @Autowired
    private CustomerLossServiceI customerLossService;

    // cron 秒 分 时 天 月 年
    @Scheduled(cron = "0/5 * * * * ?")// 每天每5秒执行一次
    public void job() {
        customerLossService.saveBatchCustomerLoss();
        logger.info("----- 预流失客户信息收集成功 -----");
    }

}
