package uz.uni_team.online_translator.presentation.main.pages.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uni_team.online_translator.R
import uz.uni_team.online_translator.databinding.ScreenHistoryBinding
import uz.uni_team.online_translator.databinding.ScreenHomeBinding
import uz.uni_team.online_translator.presentation.main.pages.history.adapter.HistoryAdapter
import uz.uni_team.online_translator.presentation.main.pages.home.HomeViewModel
import uz.uni_team.online_translator.presentation.main.pages.home.dialog.ChooseLanguageDialog

@AndroidEntryPoint
class HistoryScreen:Fragment(R.layout.screen_history) {

    private val binding: ScreenHistoryBinding by viewBinding()
    private val viewModel: HistoryViewModel by viewModels()
    private val adapter = HistoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvStory.adapter = adapter


        adapter.setFavouriteClickListener {
            viewModel.updateFavouriteStatus(it)
        }

        viewModel.translatorList.observe(viewLifecycleOwner) { list ->
            adapter.setLanguageList(list)
        }

    }

}