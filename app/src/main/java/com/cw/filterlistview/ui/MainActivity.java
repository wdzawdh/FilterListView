package com.cw.filterlistview.ui;

import android.os.Bundle;
import android.view.Window;

import com.cw.filterlistview.widget.IndexBarView;
import com.cw.filterlistview.widget.PinnedHeaderListView;
import com.cw.filterlistview.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String[] ITEMS = new String[] { "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea",
            "Eritrea", "Estonia", "Ethiopia", "Faeroe Islands", "Falkland Islands", "Fiji", "Finland", "Afghanistan",
            "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica",
            "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahrain",
            "Bangladesh", "Barbados", "Belarus", "Belgium", "Monaco", "Mongolia", "Montserrat", "Morocco",
            "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
            "New Caledonia", "New Zealand",
            "Saint Pierre and Miquelon", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Christmas Island",
            "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Cook Islands", "Costa Rica", "Croatia", "Cuba",
            "Cyprus", "Czech Republic", "Democratic Republic of the Congo", "Denmark", "Djibouti", "Dominica",
            "Dominican Republic", "Former Yugoslav Republic of Macedonia", "France", "French Guiana",
            "French Polynesia", "Macau", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta",
            "Marshall Islands", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        IndexBarView indexBarView = (IndexBarView) findViewById(R.id.indexBarView);

        PinnedHeaderListView pinnedHeaderListView = (PinnedHeaderListView) findViewById(R.id.pinnedHeaderListView);
        CompareAdapter compareAdapter = new CompareAdapter();
        pinnedHeaderListView.setAdapter(compareAdapter);

        //绑定indexBarView
        compareAdapter.bindIndexBar(indexBarView);

        /*
         * 此处模拟从网络获取的数据集合,model类需要继承CompareModel,重写setCompareField返回需要按哪个字段排序
         * 如果字段为中文会自动转换为拼音按照首字母排序
         */
        ArrayList<PersonModel> list = new ArrayList<>();
        for (int i = 0; i < ITEMS.length; i++) {
            PersonModel male = new PersonModel(ITEMS[i], "男", i);
            list.add(male);
        }
        compareAdapter.updata(list);
    }
}
