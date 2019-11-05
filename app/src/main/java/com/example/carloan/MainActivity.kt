package com.example.carloan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCalculate.setOnClickListener {
            calculateLoan();

        }

    }

    private fun calculateLoan() {

        var currency: BigDecimal? = BigDecimal.ZERO
        val carPrice: Int = editTextCarPrice.text.toString().toInt();
        val downPayment: Int = editTextDownPayment.text.toString().toInt();
        val loanPeriod: Int = editTextDownPeriod.text.toString().toInt();
        val InteresetRate: Float = editTextInterestRate.text.toString().toFloat();
        if (editTextCarPrice.text.isEmpty()) {
            editTextCarPrice.setError((getString(R.string.input_error)))
            return
        }
        if (editTextDownPayment.text.isEmpty()) {
            editTextDownPayment.setError((getString(R.string.input_error)))
            return
        }
        if (editTextDownPeriod.text.isEmpty()) {
            editTextDownPeriod.setError((getString(R.string.input_error)))
            return
        }
        if (editTextInterestRate.text.isEmpty()) {
            editTextInterestRate.setError((getString(R.string.input_error)))
            return
        }
        if (downPayment > carPrice) {
            editTextDownPayment.setError((getString(R.string.input_error)))
            return
        }

        val carLoan: Int = carPrice - downPayment;
        val Interest: Float = carLoan * InteresetRate * loanPeriod;
        val monthlyPayment: Float = (carLoan + Interest) / loanPeriod / 12;
        textViewInterest.text = String.format(getString(R.string.interest) + Interest.toString());
        textViewLoan.text = String.format(getString(R.string.loan)+ carLoan.toString());
        textViewMontlyPayment.text = String.format(getString((R.string.monthly_payment))+ monthlyPayment.toString());

    }

    fun reset(view: View) {
        editTextCarPrice.setText("");
        editTextDownPayment.setText("");
        editTextDownPeriod.setText("");
        editTextInterestRate.setText("");
    }


}
