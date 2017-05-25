package com.zwonb.mylibrary;

import org.litepal.crud.DataSupport;

/**
 * Created by zyb on 2017/5/24.
 */

public class ModuleBean extends DataSupport {

    private String name;
    private int number;
    private String live;

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
