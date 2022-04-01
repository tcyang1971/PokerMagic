package tw.edu.pu.csim.tcyang.pokermagic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.abs

class MainActivity : AppCompatActivity(), View.OnTouchListener, GestureDetector.OnGestureListener {

    lateinit var gDetector: GestureDetector
    var color:String = "" //花色
    var number:Int = 1  //點數

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        img.setOnTouchListener(this)
        gDetector = GestureDetector(this, this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(event?.action == MotionEvent.ACTION_DOWN){
            //txv.text = "手指按下"
            color  = ""
        }
        if(event?.action == MotionEvent.ACTION_UP){
            //txv.text = "手指彈開"
            if (color == ""){
                img.setImageResource(R.drawable.joker)
            }
            else{
                var res:Int = getResources().getIdentifier(color + number.toString(),
                    "drawable", getPackageName())
                img.setImageResource(res)
            }
        }
        gDetector.onTouchEvent(event)
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onShowPress(p0: MotionEvent?) {

    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, p2: Float, p3: Float): Boolean {
        //txv.text = "拖曳"
        if (e1!!.x >= e2!!.x){
            if (e1!!.y >= e2!!.y){  //左上
                color = "c"
            }
            else{  //左下
                color = "d"
            }
        }
        else{
            if (e1!!.y >= e2!!.y){  //右上
                color = "h"
            }
            else{
                color = "s"  //右下
            }
        }
        //處理點數
        number = abs(e1!!.y.toInt() - e2!!.y.toInt())/(img.height/26)+1
        if (number>13){ number=13 }
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {

    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        return true
    }
}