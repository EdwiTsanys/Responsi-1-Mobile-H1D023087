package com.unsoed.responsi1mobileh1d023087

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainLayout: View? = findViewById(R.id.main)
        if (mainLayout != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainLayout) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }


        val clubHistoryCard = findViewById<CardView>(R.id.clubHistoryCard)
        val headCoachCard = findViewById<CardView>(R.id.headCoachCard)
        val teamSquadCard = findViewById<CardView>(R.id.teamSquadCard)


        clubHistoryCard.setOnClickListener {

            val intent = Intent(this, ClubHistoryActivity::class.java)
            startActivity(intent)
        }

        headCoachCard.setOnClickListener {

            val intent = Intent(this, HeadCoachActivity::class.java)
            startActivity(intent)
        }

        teamSquadCard.setOnClickListener {
            val intent = Intent(this, TeamSquadActivity::class.java)
            startActivity(intent)
        }
    }
}