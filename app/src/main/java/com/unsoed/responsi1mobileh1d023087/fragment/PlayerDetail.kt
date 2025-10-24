package com.unsoed.responsi1mobileh1d023087.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.unsoed.responsi1mobileh1d023087.R
import com.unsoed.responsi1mobileh1d023087.data.model.Player
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Locale

class PlayerDetail : BottomSheetDialogFragment() {

    private var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                player = it.getParcelable(ARG_PLAYER, Player::class.java)
            } else {

                @Suppress("DEPRECATION")
                player = it.getParcelable(ARG_PLAYER)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_player_detail, container, false)

        val nameText: TextView = view.findViewById(R.id.detail_player_name)
        val positionText: TextView = view.findViewById(R.id.detail_player_position)
        val nationalityText: TextView = view.findViewById(R.id.detail_player_nationality)
        val dobText: TextView = view.findViewById(R.id.detail_player_dob)


        player?.let {
            nameText.text = it.name
            positionText.text = "${it.position}"
            nationalityText.text = "${it.nationality}"
            dobText.text = "${formatDate(it.dateOfBirth)}"
        }

        return view
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

    companion object {
        private const val ARG_PLAYER = "player_data"


        fun newInstance(player: Player): PlayerDetail {
            val fragment = PlayerDetail()
            val args = Bundle()
            args.putParcelable(ARG_PLAYER, player)
            fragment.arguments = args
            return fragment
        }
    }
}