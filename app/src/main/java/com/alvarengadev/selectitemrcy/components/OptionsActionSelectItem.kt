package com.alvarengadev.selectitemrcy.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.alvarengadev.selectitemrcy.databinding.OptionsActitionSelectItemBinding

class OptionsActionSelectItem(
    context: Context,
    attributeSet: AttributeSet
) : LinearLayout(context, attributeSet) {

    private val binding = OptionsActitionSelectItemBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    fun setOnClickInIbClose(onClickListener: OnClickListener): OptionsActionSelectItem {
        binding.ibClose.setOnClickListener(onClickListener)
        return this
    }

    fun setOnClickInIbTrash(onClickListener: OnClickListener): OptionsActionSelectItem {
        binding.ibDelete.setOnClickListener(onClickListener)
        return this
    }
}