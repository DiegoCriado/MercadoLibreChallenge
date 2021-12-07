package com.example.myapplication.ui.product.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.core.BaseViewHolder
import com.example.myapplication.databinding.ItemProductBinding
import com.example.myapplication.model.Product

class ProductAdapter(
    private var productList: List<Product>,
    private val itemClickListener: OnProductClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnProductClickListener {
        fun onProductClick(porduct: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ProductViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position =
                holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
            itemClickListener.onProductClick(productList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.bind(productList[position])
        }
    }

    override fun getItemCount(): Int = productList.size


    private inner class ProductViewHolder(val binding: ItemProductBinding, val context: Context) :
        BaseViewHolder<Product>(binding.root) {
        @SuppressLint("SetTextI18n")
        override fun bind(item: Product) {

            itemView.setOnClickListener { itemClickListener.onProductClick(item)}

            Glide.with(context).load(item.image).placeholder(android.R.drawable.ic_menu_gallery).centerCrop().into(binding.imagProduct)
            binding.txTitle.text = item.name
            binding.txPrecio.text = "$${item.price}"
        }
    }

}