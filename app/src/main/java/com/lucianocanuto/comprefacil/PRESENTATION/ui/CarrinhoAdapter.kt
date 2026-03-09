package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoItem
import com.lucianocanuto.comprefacil.databinding.ItemCarrinhoBinding

class CarrinhoAdapter (
    private var lista: List<CarrinhoItem>,
    private val onRemoverClick : (CarrinhoItem) -> Unit
): RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder>() {

    inner class CarrinhoViewHolder(
        val binding : ItemCarrinhoBinding
    ): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarrinhoViewHolder {
        val binding = ItemCarrinhoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false
        )
        return CarrinhoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CarrinhoViewHolder,
        position: Int
    ) {
        val item = lista[position]

        holder.binding.textNome.text = item.titulo
        holder.binding.textPreco.text = "R$ ${item.preco}"
        holder.binding.textQuantidade.text = "Qtd: ${item.quantidade}"

        holder.binding.btnRemover.setOnClickListener {
            onRemoverClick(item)
        }

        Glide.with(holder.itemView.context)
            .load(item.imagem)
            .into(holder.binding.imgProduto)
    }

    override fun getItemCount() = lista.size

    fun atualizarLista(novaLista: List<CarrinhoItem>){

        lista = novaLista
        notifyDataSetChanged()

    }




}