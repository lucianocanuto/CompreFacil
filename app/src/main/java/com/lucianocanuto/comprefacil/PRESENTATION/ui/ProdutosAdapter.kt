package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto
import com.lucianocanuto.comprefacil.R

class ProdutosAdapter(
    private val onClick: (Produto) -> Unit
) : ListAdapter<Produto, ProdutosAdapter.ProdutosViewHolder>(ListaProdutos()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProdutosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_produto, parent, false)
        return ProdutosViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProdutosViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ProdutosViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val imgProduto: ImageView =
            itemView.findViewById(R.id.imgProduto)
        private val nomeProduto: TextView =
            itemView.findViewById(R.id.txtNomeProduto)
        private val precoProduto: TextView =
            itemView.findViewById(R.id.txtPreco)

        fun bind(produto: Produto) {

            itemView.setOnClickListener {
                onClick(produto)
            }

            nomeProduto.text = produto.titulo
            precoProduto.text = produto.preco.toString()

            Glide.with(itemView.context)
                .load(produto.imagem)
                .into(imgProduto)
        }
    }

    class ListaProdutos : DiffUtil.ItemCallback<Produto>() {

        override fun areItemsTheSame(
            oldItem: Produto,
            newItem: Produto
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Produto,
            newItem: Produto
        ) = oldItem == newItem
    }
}