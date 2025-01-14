package uz.uni_team.online_translator.presentation.pager

import uz.uni_team.online_translator.presentation.main.pages.home.HomeScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.uni_team.online_translator.presentation.main.pages.camera.CameraScreen
import uz.uni_team.online_translator.presentation.main.pages.docs.DocScreen
import uz.uni_team.online_translator.presentation.main.pages.favourites.FavouriteScreen
import uz.uni_team.online_translator.presentation.main.pages.history.HistoryScreen
import javax.inject.Inject

class ViewPagerAdapter @Inject constructor(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DocScreen()
            1 -> CameraScreen()
            2 -> HomeScreen()
            3 -> HistoryScreen()
            else -> FavouriteScreen()
        }
    }
}