package uz.uni_team.online_translator.presentation.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.online_translator.R
import uz.uni_team.online_translator.databinding.ScreenMainBinding
import uz.uni_team.online_translator.presentation.pager.ViewPagerAdapter

@SuppressLint("CustomMainScreen")
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpMain.adapter = ViewPagerAdapter(requireActivity())
        binding.vpMain.isUserInputEnabled = false

        binding.vpMain.setCurrentItem(2, false)
        binding.bottomNavView.selectedItemId = R.id.main

        binding.bottomNavView.setOnItemSelectedListener { item ->
            val position = when (item.itemId) {
                R.id.doc -> 0
                R.id.camera -> 1
                R.id.main -> 2
                R.id.history -> 3
                else -> 4
            }
            binding.vpMain.setCurrentItem(position, false)
            true
        }

        binding.fabTranslate.setOnClickListener {
            binding.bottomNavView.selectedItemId = R.id.main
            binding.vpMain.setCurrentItem(2, false)
        }

    }
}