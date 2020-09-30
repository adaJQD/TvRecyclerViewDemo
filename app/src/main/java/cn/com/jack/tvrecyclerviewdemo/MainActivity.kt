package cn.com.jack.tvrecyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = List(10) { "item $it" }
        val adapter = MainAdapter()
        vertical_gridview.adapter = adapter
        adapter.submitList(list)
    }
}