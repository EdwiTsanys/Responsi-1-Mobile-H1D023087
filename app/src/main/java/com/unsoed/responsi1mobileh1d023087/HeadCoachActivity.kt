package com.unsoed.responsi1mobileh1d023087

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.unsoed.responsi1mobileh1d023087.data.api.ApiClient
import com.unsoed.responsi1mobileh1d023087.data.model.Coach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


class HeadCoachActivity : AppCompatActivity() {

    private lateinit var coachNameText: TextView
    private lateinit var coachNationalityText: TextView
    private lateinit var coachDobText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.head_coach)

        coachNameText = findViewById(R.id.coach_name_text)
        coachNationalityText = findViewById(R.id.coach_nationality_text)
        coachDobText = findViewById(R.id.coach_dob_text)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fetchCoachData()
    }

    private fun fetchCoachData() {

        lifecycleScope.launch {
            try {

                val response = ApiClient.apiService.getTeam(548)

                populateCoachData(response.coach)
            } catch (e: Exception) {

                Log.e("HeadCoachActivity", "Error fetching data: ${e.message}", e)
                Toast.makeText(this@HeadCoachActivity, "Failed to load coach data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateCoachData(coach: Coach) {
        coachNameText.text = coach.name
        coachNationalityText.text = "${coach.nationality}"
        coachDobText.text = "${formatDate(coach.dateOfBirth)}"
    }

    private fun formatDate(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) } ?: dateString
        } catch (e: Exception) {
            dateString
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}