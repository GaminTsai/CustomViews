package com.zzt8888.FunVideo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.zzt8888.base.BaseActivity
import com.zzt8888.materialdesign.R
import kotlinx.android.synthetic.main.activity_funvideo.*

class FunVideoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funvideo)
        textView.setOnClickListener({
            v ->
            Log.d("tag", "msg")
            Log.d("tag", "msg2")
        })
    }



    companion object {
        @JvmStatic
        fun startActivity(context: Context) {
            var intent = Intent(context, FunVideoActivity.javaClass)
            context.startActivity(intent)
        }
    }
}