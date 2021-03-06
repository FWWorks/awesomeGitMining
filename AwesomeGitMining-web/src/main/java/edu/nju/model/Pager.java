package edu.nju.model;

/**
 * Created by Dora on 2016/5/2.
 */

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Pager<T> {
    private List<T> datas;
    private int offset;
    private int size;
   // private long total;

    public Pager() {
    }

    public Pager(List<T> datas, int offest, int size) {
        this.datas = datas;
        this.offset = offest;
        this.size = size;

    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


}
