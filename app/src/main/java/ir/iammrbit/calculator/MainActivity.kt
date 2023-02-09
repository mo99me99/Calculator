package ir.iammrbit.calculator

import android.os.Binder
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import ir.iammrbit.calculator.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun onDigit(view : View){
        binding.tvInput.append((view as Button).text)
    }
    fun onCLR(view: View){
        binding.tvInput.text = ""
    }


}