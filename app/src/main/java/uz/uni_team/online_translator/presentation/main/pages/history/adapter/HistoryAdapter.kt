package uz.uni_team.online_translator.presentation.main.pages.history.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.uni_team.online_translator.R
import uz.uni_team.online_translator.data.local.entity.TranslatorEntity
import uz.uni_team.online_translator.databinding.ListItemStoryBinding

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var translatorList: List<TranslatorEntity> = emptyList()

    private var translatorItemClickListener: ((TranslatorEntity) -> Unit)? = null

    private var translatorFavouriteItemClickListener: ((TranslatorEntity) -> Unit)? = null


    fun setFavouriteClickListener(block: (TranslatorEntity) -> Unit) {
        translatorFavouriteItemClickListener = block
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setLanguageList(list: List<TranslatorEntity>) {
        translatorList = list
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: ListItemStoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val item = translatorList[bindingAdapterPosition]
                translatorItemClickListener?.invoke(item)
            }
            binding.btnStar.setOnClickListener{
                val item = translatorList[bindingAdapterPosition]
                translatorFavouriteItemClickListener?.invoke(item)
            }
        }

        fun onBind(translatorEntity: TranslatorEntity) {
            binding.apply {
                tvLanguageShort.text = translatorEntity.fromLanguage
                tvLanguageShortSecond.text = translatorEntity.toLanguage
                unTranslatedText.text = translatorEntity.unTranslatedText
                translatedText.text = translatorEntity.translatedText
                val image = if (translatorEntity.isFavourite) R.drawable.ic_rate else R.drawable.ic_favourite
                btnStar.setImageResource(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(ListItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = translatorList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(translatorList[position])
    }
}