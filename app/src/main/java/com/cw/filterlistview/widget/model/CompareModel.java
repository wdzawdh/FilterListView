/**
 *   @function:$
 *   @description: $
 *   @param:$
 *   @return:$
 *   @history:
 * 1.date:$ $
 *           author:$
 *           modification:
 */

package com.cw.filterlistview.widget.model;

import android.support.annotation.NonNull;

import com.cw.filterlistview.widget.util.PinyinUtil;

/**
 * 当需要Model的显示需要按照某个字段排序时继承此类
 * setCompareField方法来设置按哪个字段排序
 *
 * @author Cw
 * @date 16/8/20
 */
public abstract class CompareModel implements Comparable<CompareModel> {

    private String compareField;

    public abstract String setCompareField();

    public String getCompareField() {
        return compareField;
    }

    public String getPinyin() {
        this.compareField = setCompareField();
        return PinyinUtil.getPinyin(compareField);
    }

    @Override
    public int compareTo(@NonNull CompareModel model) {
        return getPinyin().compareTo(model.getPinyin());
    }
}