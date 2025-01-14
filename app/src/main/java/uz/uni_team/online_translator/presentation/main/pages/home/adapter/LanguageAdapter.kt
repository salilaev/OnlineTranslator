package uz.uni_team.online_translator.presentation.main.pages.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.uni_team.online_translator.data.local.entity.LanguageEntity
import uz.uni_team.online_translator.databinding.ListItemDropdownBinding

class LanguageAdapter: RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    private var languageList: List<LanguageEntity> = emptyList()
    private var languageItemClickListener: ((LanguageEntity) -> Unit)? = null


    fun setLanguageItemClickListener(block: (LanguageEntity) -> Unit) {
        languageItemClickListener = block
    }

    // Установить список языков
    @SuppressLint("NotifyDataSetChanged")
    fun setLanguageList(list: List<LanguageEntity>) {
        languageList = list
        notifyDataSetChanged()
    }

    inner class LanguageViewHolder(private val binding: ListItemDropdownBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val item = languageList[bindingAdapterPosition]
                languageItemClickListener?.invoke(item)
            }
        }

        fun onBind(languageEntity: LanguageEntity) {
            binding.apply {
                tvText.text = languageEntity.languageName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ListItemDropdownBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun getItemCount(): Int = languageList.size

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.onBind(languageList[position])
    }
}