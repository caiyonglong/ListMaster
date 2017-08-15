/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ckt.cyl.listmaster;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ckt.cyl.listmaster.utils.TimeUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by D22434 on 2017/8/2.
 */

public class LRecord extends BaseObservable implements Serializable {

    /**
     * id、title、time、money
     */
    private String id;
    //记录
    private String title;
    //消费时间
    private Date time;
    //消费金额
    private int money;

    public LRecord() {
        id = UUID.randomUUID().toString();
    }

    public LRecord(String title, Date time, int money) {
        id = UUID.randomUUID().toString();
        this.title = title;
        this.time = time;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "LRecord{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", time=" + time +
                ", money=" + money +
                '}';
    }
}
