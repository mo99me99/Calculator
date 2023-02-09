package ir.iammrbit.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.persistableBundleOf
import ir.iammrbit.calculator.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var lastNumeric :Boolean = false
    var lastDot : Boolean = false
    var prefix = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }


    fun onDigit(view : View){
        binding.tvInput.append((view as Button).text)
        lastNumeric = true

    }
    fun onCLR(view: View){
        binding.tvInput.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onOperator(view: View){
        val temp  = isOperatorAdded(binding.tvInput.text.toString())
        if ((lastNumeric) && !temp){
            binding.tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }
    private fun isOperatorAdded(str: String): Boolean {
        return if(str.startsWith("-")) {
            false
        }else{
            (str.contains("/") || str.contains("*")
                    ||str.contains("+")||str.contains("-"))
        }
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = binding.tvInput.text.toString()

            try {
                if (binding.tvInput.text.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    var val1 = splitValue[0]
                    var val2 = splitValue[1]

                    if (prefix.isNotEmpty())
                        val1 = prefix + val1
                    binding.tvInput.text = (val1.toDouble() - val2.toDouble()).toString()

                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var val1 = splitValue[0]
                    var val2 = splitValue[1]

                    if (prefix.isNotEmpty())
                        val1 = prefix + val1
                    binding.tvInput.text = (val1.toDouble() + val2.toDouble()).toString()

                } else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var val1 = splitValue[0]
                    var val2 = splitValue[1]

                    if (prefix.isNotEmpty())
                        val1 = prefix + val1
                    binding.tvInput.text = (val1.toDouble() / val2.toDouble()).toString()
                } else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var val1 = splitValue[0]
                    var val2 = splitValue[1]

                    if (prefix.isNotEmpty())
                        val1 = prefix + val1
                    binding.tvInput.text = (val1.toDouble() * val2.toDouble()).toString()
                }
                binding.tvInput.text = removeZeroAfterDot(binding.tvInput.text.toString())
            }catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }
    private fun removeZeroAfterDot(result: String):String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }
}