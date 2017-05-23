package com.example.zhou.litepaltest;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by zhou on 2017/2/14.
 */

public class Category extends DataSupport {

    private int id;
    private String categoryName;
    private int categoryCode;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }
}
