/**
 *
 *   @function:$
 *   @description: $
 *   @param:$
 *   @return:$
 *   @history:
 * 1.date:$ $
 *           author:$
 *           modification:
 */

package com.cw.filterlistview.ui;

import com.cw.filterlistview.model.CompareModel;

/**
 *
 * @author Cw
 * @date 16/8/21
 */
public class PersonModel extends CompareModel {

    public String name;
    public String sex;
    public int age;

    public PersonModel(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        //此处设置要按哪个字段排序
        setCompareField(this.name);
    }

}