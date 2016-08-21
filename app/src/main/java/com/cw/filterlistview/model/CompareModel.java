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

package com.cw.filterlistview.model;

import android.support.annotation.NonNull;

import com.cw.filterlistview.util.PinyinUtil;

/**
 * @author Cw
 * @date 16/8/20
 */
public class CompareModel implements Comparable<CompareModel> {

    private String compareField;
    private String pinyin;


    public CompareModel() {
        super();

    }

    public void setCompareField(String compareField){
        this.compareField = compareField;
        this.pinyin = PinyinUtil.getPinyin(compareField);
    }

    public String getCompareField() {
        return compareField;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    @Override
    public int compareTo(@NonNull CompareModel model) {
        return pinyin.compareTo(model.pinyin);
    }
}