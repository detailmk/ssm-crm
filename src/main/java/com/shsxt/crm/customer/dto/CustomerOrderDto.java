package com.shsxt.crm.customer.dto;

import com.shsxt.crm.customer.pojo.CustomerOrder;

import java.math.BigDecimal;

/**
 * 客户历史订单Dto
 */
public class CustomerOrderDto extends CustomerOrder {

    private BigDecimal totalPrice;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
