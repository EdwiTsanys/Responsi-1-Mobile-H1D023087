package com.unsoed.responsi1mobileh1d023087.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023087.R
import com.unsoed.responsi1mobileh1d023087.data.model.Player


class PlayerAdapter(
    private var playerList: List<Player>,
    private val onPlayerClick: (Player) -> Unit
) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player_card, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(playerList[position])
    }

    override fun getItemCount(): Int = playerList.size

    fun updatePlayers(newPlayerList: List<Player>) {
        playerList = newPlayerList.sortedBy { getPositionOrder(it) }
        notifyDataSetChanged()
    }

    private fun getPositionOrder(player: Player): Int {
        return when {
            player.position.contains("Goalkeeper") -> 1
            player.position.contains("Defender") || player.position.contains("Back") -> 2
            player.position.contains("Midfield") -> 3
            player.position.contains("Forward") || player.position.contains("Winger") -> 4
            else -> 5
        }
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.player_name_text)

        private val nationalityTextView: TextView = itemView.findViewById(R.id.player_nationality_text)
        private val cardView: CardView = itemView.findViewById(R.id.player_card)

        fun bind(player: Player) {
            nameTextView.text = player.name

            nationalityTextView.text = player.nationality


            val colorResource = when {
                player.position.contains("Goalkeeper") -> R.color.color_goalkeeper
                player.position.contains("Defender") || player.position.contains("Back") -> R.color.color_defender
                player.position.contains("Midfield") -> R.color.color_midfielder
                player.position.contains("Forward") || player.position.contains("Winger") -> R.color.color_forward
                else -> R.color.color_default_player
            }
            cardView.setCardBackgroundColor(ContextCompat.getColor(itemView.context, colorResource))


            itemView.setOnClickListener {
                onPlayerClick(player)
            }
        }
    }
}