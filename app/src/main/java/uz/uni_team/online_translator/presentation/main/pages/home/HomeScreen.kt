package uz.uni_team.online_translator.presentation.main.pages.home

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.uni_team.online_translator.R
import uz.uni_team.online_translator.databinding.ScreenHomeBinding
import uz.uni_team.online_translator.presentation.main.pages.home.dialog.ChooseLanguageDialog
import uz.uni_team.online_translator.presentation.main.pages.home.dialog.LoadingDialog

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var drawerLayout: DrawerLayout

    private val loadingDialog by lazy { LoadingDialog() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawerLayout = binding.root.findViewById(R.id.drawer_layout)

        setupMenuButton()
        setupClearButton()
        setupTranslateButton()
        setupObservers()

        binding.firstLanguage.setOnClickListener {
            val dialog = ChooseLanguageDialog(
                viewModel.languages.value ?: emptyList()
            ) {
                viewModel.setFromLanguage(it)
            }
            dialog.show(parentFragmentManager, dialog.tag)
        }
        binding.secondLanguage.setOnClickListener {
            val dialog = ChooseLanguageDialog(
                viewModel.languages.value ?: emptyList()
            ){
                viewModel.setToLanguage(it)
            }
            dialog.show(parentFragmentManager, dialog.tag)
        }

        viewModel.getLanguages()
    }

    private fun setupMenuButton() {
        binding.btnMenu.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun setupClearButton() {
        binding.btnX.setOnClickListener {
            binding.myEditText.text.clear()
        }
    }

    private fun setupTranslateButton() {
        binding.btnTranslate.setOnClickListener {
            val inputText = binding.myEditText.text.toString().trim()
            if (inputText.isNotEmpty()) {
                viewModel.translateText(inputText)
            }
        }
    }

    private fun setupObservers() {
        viewModel.translatedText.observe(viewLifecycleOwner) { translatedText ->
            binding.cardText.visibility = View.VISIBLE
            binding.tvTranslateText.visibility = View.VISIBLE
            binding.tvTranslateText.text = translatedText
        }
        viewModel.fromLanguageText.observe(viewLifecycleOwner) { language ->
            binding.firstLanguage.text = language.languageName
            binding.tvFromLanguageText.text = language.languageName
        }

        viewModel.toLanguageText.observe(viewLifecycleOwner) { language ->
            binding.secondLanguage.text = language.languageName
            binding.tvToLanguageText.text = language.languageName
        }

        viewModel.loadingState.observe(viewLifecycleOwner) {
            if (it) {
                loadingDialog.show(childFragmentManager, null)
            } else {
                loadingDialog.dismiss()
            }
        }
    }


}
