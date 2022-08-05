package com.financials.retirementcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {

    private lateinit var retirementButton: Button
    private lateinit var monthlySavingsEt : EditText
    private lateinit var interestRateEt : EditText
    private lateinit var currentAgeEt : EditText
    private lateinit var plannedRetirementAgeEt : EditText
    private lateinit var currentSavingsEt : EditText
    private lateinit var calculateRetirementResult : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(application, "130e0dbe-f201-484b-b192-a07f3cc6fb4b", Analytics::class.java, Crashes::class.java)

        retirementButton = findViewById(R.id.retirement_calculator_btn)
        monthlySavingsEt = findViewById(R.id.monthly_savings_et)
        interestRateEt = findViewById(R.id.interest_rate_et)
        currentAgeEt = findViewById(R.id.current_age_et)
        plannedRetirementAgeEt = findViewById(R.id.planned_retirement_age_et)
        currentSavingsEt = findViewById(R.id.current_savings_et)
        calculateRetirementResult = findViewById(R.id.retirement_calculator_result_tv)

        retirementButton.setOnClickListener {
           // throw Exception("Something went wrong!")
//            Crashes.generateTestCrash()
            val interestRate = interestRateEt.text.toString().toFloat()
            val currentAge = currentAgeEt.text.toString()
            val retirementAge = plannedRetirementAgeEt.text.toString()
            val monthlySavings = monthlySavingsEt.text.toString()
            val currentSavings = currentSavingsEt.text.toString()

            calculateRetirementResult.text = "At the current rate of ${interestRate}, if your monthly savings is $monthlySavings" +
                    "and your retirement age is $retirementAge, then your current savings is $currentSavings"


            if (interestRate <= 0) {
                Analytics.trackEvent("Wrong Interest Rate")
            }

            if (currentAge >= retirementAge) {
                Analytics.trackEvent("Wrong Age for retirement")
            }
        }
    }
}