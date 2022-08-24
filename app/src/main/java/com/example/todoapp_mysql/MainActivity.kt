package com.example.todoapp_mysql

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Viewの取得
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val lv: ListView = findViewById(R.id.lv)

        //アダプターに入れてListViewにセット。
        // 最後のリストの中身が空で型推論が効かないので、必ず型を指定するように。
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        lv.adapter = adapter

        //+ボタンのアラートダイアログ
        btnAdd.setOnClickListener {
            //EditTextのインスタンスを生成
            val et = EditText(this)

            AlertDialog.Builder(this).setTitle("項目の追加")
                .setTitle("項目の追加")
                .setMessage("タスクを入力してください")
                .setView(et)
                .setPositiveButton("追加", DialogInterface.OnClickListener { dialogInterface, i ->
                    //add()メソッドでアダプターに追加
                    val myToDo = et.text.toString()
                    adapter.add(myToDo)
                })
                .setNegativeButton("キャンセル", null)
                .show()
        }

        //ListViewをクリックしたとき、アラー度ダイアログを出す処理
        lv.setOnItemClickListener { adapterView, view, i, l ->
            AlertDialog.Builder(this)
                .setTitle("削除しますか？")
                //「はい」を押したらremoveメソッドで削除
                .setPositiveButton("はい", DialogInterface.OnClickListener { dialogInterface2, j ->
                    adapter.remove(adapter.getItem(i))
                    //画面を更新
                    adapter.notifyDataSetChanged()
                })
                .setNegativeButton("いいえ", null)
                .show()
        }
    }
}