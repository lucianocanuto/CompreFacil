package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucianocanuto.comprefacil.DOMAIN.model.Carrinho
import com.lucianocanuto.comprefacil.databinding.ItemCarrinhoBinding

class CarrinhoAdapter (
    private var lista: List<Carrinho>
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
    }

    override fun getItemCount() = lista.size

    fun atualizarLista(novaLista: List<Carrinho>){

        lista = novaLista
        notifyDataSetChanged()

    }


}