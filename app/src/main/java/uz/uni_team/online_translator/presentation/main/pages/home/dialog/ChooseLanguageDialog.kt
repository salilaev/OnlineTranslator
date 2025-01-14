package uz.uni_team.online_translator.presentation.main.pages.home.dialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.uni_team.online_translator.R
import uz.uni_team.online_translator.data.local.entity.LanguageEntity
import uz.uni_team.online_translator.databinding.DialogChooseLanguageBinding
import uz.uni_team.online_translator.presentation.main.pages.home.adapter.LanguageAdapter

class ChooseLanguageDialog(
    private val languages: List<LanguageEntity>,
    private val onLanguageSelected: (LanguageEntity) -> Unit,
) : DialogFragment(R.layout.dialog_choose_language) {

    private val binding: DialogChooseLanguageBinding by viewBinding()

    private val adapter = LanguageAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvLanguage.adapter = adapter

        adapter.setLanguageList(languages)

        adapter.setLanguageItemClickListener { language ->
            onLanguageSelected(language)
            dismiss()
        }
    }
}