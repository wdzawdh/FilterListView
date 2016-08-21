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

package com.cw.filterlistview.adapter;

import android.text.TextUtils;

import com.cw.filterlistview.IndexBarView;
import com.cw.filterlistview.model.CompareModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 此adapter负责将数据按照Model指定的规则分类显示
 *
 * @author Cw
 * @date 16/8/20
 */
public abstract class FilterBaseAdapter extends SectionedBaseAdapter implements IndexBarView.OnTouchTypeChangedListener {

    private IndexBarView mIndexBar;
    private List<? extends CompareModel> mList;
    protected List<Map.Entry<String, List<? extends CompareModel>>> mGroupsList;

    public void updata(List<? extends CompareModel> list) {
        Collections.sort(list);
        this.mList = list;
        //将list按首字母分类
        mGroupsList = groupsByFirstLetter(mList);
        notifyDataSetChanged();
        //取出分类后key的集合给indexBar
        mIndexBar.setItems(getKeyList(mGroupsList));
    }

    public void bindIndexBar(IndexBarView indexBar) {
        this.mIndexBar = indexBar;
        mIndexBar.setOnTouchTypeChangedListener(this);
    }

    @Override
    public int getSectionCount() {
        return mGroupsList != null ? mGroupsList.size() : 0;
    }

    @Override
    public int getCountForSection(int section) {
        return mGroupsList != null ? mGroupsList.get(section).getValue().size() : 0;
    }

    @Override
    public void onTouchingLetterChanged(String letter, int index) {
        if (mList == null) {
            return;
        }

        // 从集合中查找第一个拼音首字母为letter的索引, 进行跳转
        for (int i = 0; i < mList.size(); i++) {
            CompareModel compareModel = mList.get(i);
            String s = compareModel.getPinyin().charAt(0) + "";
            if (TextUtils.equals(s, letter)) {
                // 匹配成功, 中断循环, 跳转到i位置(需要计算头条目)
                if (mListView != null) {
                    mListView.setSelection(i + index);
                    break;
                }
            }
        }

    }

    /**
     * 获取键值对集合中的Key集合
     */
    private List<String> getKeyList(List<Map.Entry<String, List<? extends CompareModel>>> groupsList) {
        ArrayList<String> strings = new ArrayList<>();
        for (Map.Entry<String, List<? extends CompareModel>> stringListEntry : groupsList) {
            strings.add(stringListEntry.getKey());
        }
        return strings;
    }

    /**
     * 对集合以首字母进行分类
     */
    private List<Map.Entry<String, List<? extends CompareModel>>> groupsByFirstLetter(List<? extends CompareModel> list) {
        //使用TreeMap保证排列顺序不变
        TreeMap<String, List<? extends CompareModel>> tm = new TreeMap<>();
        for (int i = 0; i < list.size(); i++) {
            CompareModel m = list.get(i);
            String key = m.getPinyin().charAt(0) + "";
            if (tm.containsKey(key)) {
                ArrayList oldList = (ArrayList) tm.get(key);
                oldList.add(m);
            } else {
                ArrayList newList = new ArrayList();
                newList.add(m);
                tm.put(key, newList);
            }

        }
        return new ArrayList<>(tm.entrySet());
    }


}