package com.wanwei.common.base.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author zhangjing
 * @Description //结合前端生成树
 * @Date 10:48 2018/9/19
 * @Param
 * @return
 **/
public class Tree {
    //id
    String id;
    //value 为了生成多级联动使用。
    String value;
    //名称
    String label;
    //图标
    String icon;

    List<Tree> children;
    //等级，1代表区域，2代表企业，3代表排口
    Integer level;
    //用于统计 ,默认值为0
    private Integer remark =0;

    public Tree() {
        super();
    }

    /**
     * @Author zhangjing
     * @Description // 等级，1代表区域，2代表企业，3代表排口
     * @Date 16:16 2018/9/22
     * @Param [id, lable, level]
     * @return 
     **/
    public Tree(String id, String lable,Integer level) {
        this.id = id;
        this.value = id;
        this.label = lable;
        this.level = level;
        this.children = new ArrayList<Tree>();
    }

    public Tree(String id, String lable, String icon) {
        this.id = id;
        this.value = id;
        this.label = lable;
        this.icon = icon;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(id, tree.id) &&
                Objects.equals(label, tree.label) &&
                Objects.equals(level, tree.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, level);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
