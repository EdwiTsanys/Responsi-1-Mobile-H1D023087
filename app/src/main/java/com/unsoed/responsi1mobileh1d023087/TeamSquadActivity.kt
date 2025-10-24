package com.unsoed.responsi1mobileh1d023087

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023087.adapter.PlayerAdapter
import com.unsoed.responsi1mobileh1d023087.data.api.ApiClient
import com.unsoed.responsi1mobileh1d023087.data.model.Player
import com.unsoed.responsi1mobileh1d023087.fragment.PlayerDetail
import kotlinx.coroutines.launch


class TeamSquadActivity : AppCompatActivity() {

    private lateinit var squadRecyclerView: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.team_squad)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        fetchSquadData()
    }

    private fun setupRecyclerView() {
        squadRecyclerView = findViewById(R.id.squad_recycler_view)


        playerAdapter = PlayerAdapter(emptyList()) { player ->

            showPlayerDetail(player)
        }

        squadRecyclerView.adapter = playerAdapter
        squadRecyclerView.layoutManager = LinearLayoutManager(this)
    }


    private fun showPlayerDetail(player: Player) {
        val bottomSheet = PlayerDetail.newInstance(player)
        bottomSheet.show(supportFragmentManager, "PlayerDetailFragmentTag")
    }

    private fun fetchSquadData() {
        lifecycleScope.launch {
            try {

                val response = ApiClient.apiService.getTeam(548)

                playerAdapter.updatePlayers(response.squad)
            } catch (e: Exception) {

                Log.e("TeamSquadActivity", "Error fetching data: ${e.message}", e)
                Toast.makeText(this@TeamSquadActivity, "Failed to load squad data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}