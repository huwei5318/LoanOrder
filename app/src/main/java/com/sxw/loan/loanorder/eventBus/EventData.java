package com.sxw.loan.loanorder.eventBus;

import com.sxw.loan.loanorder.moudle.OrderVo;

/**
 * Created by Administrator on 2017/7/20.
 */

public class EventData extends BaseEvent {

    public OrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderVo orderVo) {
        this.orderVo = orderVo;
    }

    private OrderVo orderVo;

    public EventData(OrderVo orderVo) {
        this.orderVo = orderVo;
    }


}
