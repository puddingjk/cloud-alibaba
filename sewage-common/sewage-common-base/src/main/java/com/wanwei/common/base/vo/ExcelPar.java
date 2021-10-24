package com.wanwei.common.base.vo;

import java.util.List;

/**
 * @Author zhangjing
 * @Description //excel参数
 * @Date 12:00 2018/9/7
 * @Param 
 * @return 
 **/
public class ExcelPar {
    List<Object[]> data;
    String[] head;
    String title;
    String time;
    String[] headTop;
    int[] headTopCol;
    String endStr;
    String sheetName;

    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
        this.data = data;
    }

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getHeadTop() {
        return headTop;
    }

    public void setHeadTop(String[] headTop) {
        this.headTop = headTop;
    }

    public int[] getHeadTopCol() {
        return headTopCol;
    }

    public void setHeadTopCol(int[] headTopCol) {
        this.headTopCol = headTopCol;
    }

    public String getEndStr() {
        return endStr;
    }

    public void setEndStr(String endStr) {
        this.endStr = endStr;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
