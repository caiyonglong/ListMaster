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

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by D22434 on 2017/8/2.
 */

public class Record extends BaseObservable implements Serializable {

    private String id;
    private String title;
    private String content;
    private String time;
    private String repeat;
    private String repeat_num;
    //状态
    private boolean status;
    //进度
    private int schedule;

    public Record(int i) {
        id = UUID.randomUUID().toString();
        title = "0" + i;
        content = "content";

        status = Math.random() > 0.5;
        schedule = (int) (Math.random() * 100);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getRepeat_num() {
        return repeat_num;
    }

    public void setRepeat_num(String repeat_num) {
        this.repeat_num = repeat_num;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getSchedule() {
        return schedule;
    }

    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }
}
