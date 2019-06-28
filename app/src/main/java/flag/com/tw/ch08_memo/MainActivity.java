package flag.com.tw.ch08_memo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    String[] aMemo = {
            "1. 按一下可以編輯備忘",
            "2. 長按可以清除備忘", "3.", "4. ", "5. ", "6. "
    };

    ListView lv; //顯示備忘錄的listview
    ArrayAdapter<String> aa; //將aMemo資料儲存至lv

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, aMemo);

        lv.setAdapter(aa);

        lv.setOnItemClickListener(this); //設定按一下的監聽器
        lv.setOnItemLongClickListener(this); //設定長按的監聽器
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent it = new Intent(this, Edit.class);

        it.putExtra("編號", i + 1); // 加編號
        it.putExtra("備忘", aMemo[i]); // 加內容
        it.putExtra("日期", new Date().toString()); // 取得日期時間

        startActivity(it);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        aMemo[i] = (i + 1) + ". "; // 將內容清除
        aa.notifyDataSetChanged(); // 通知listView更新顯示內容

        return true; // 傳回true表示以處理
    }
}
