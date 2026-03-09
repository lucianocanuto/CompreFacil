package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoDao
import com.lucianocanuto.comprefacil.DATA.RoomDataBase.CarrinhoItem
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.DestalhesProdutoViewModel
import com.lucianocanuto.comprefacil.R
import com.lucianocanuto.comprefacil.UTIL.CompreFacilLogo
import com.lucianocanuto.comprefacil.UTIL.Resource
import com.lucianocanuto.comprefacil.databinding.ActivityProdutoDetalhesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProdutoDetalhes : AppCompatActivity() {

    private val binding by lazy {
        ActivityProdutoDetalhesBinding.inflate(layoutInflater)
    }

    private val dtProdutoViewModel: DestalhesProdutoViewModel by viewModels()

    private var produtoAtual: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("teste", "TELA DETALHES ABRIU")
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtLogo.text = CompreFacilLogo()

        val id = intent.getIntExtra("produtoId", -1)

        if (id != -1) {
            dtProdutoViewModel.buscarProdutoDt(id)
        } else {
            finish()
        }

        dtProdutoViewModel.produtoDt.observe(this) { resource ->

            when (resource) {

                is Resource.Carregando -> {
                    // loading
                }

                is Resource.Sucesso -> {
                    val produto = resource.data
                    produtoAtual = produto

                    binding.txtTituloDetalhe.text = produto?.titulo
                    binding.txtPrecoDetalhe.text = "R$ ${produto?.preco}"
                    binding.txtDescricaoDetalhe.text = produto?.descricao

                    Glide.with(this)
                        .load(produto?.imagem)
                        .into(binding.imgProdutoDetalhe)
                }

                is Resource.Erro -> {
                    // erro
                }
            }

        }

        binding.btnCarrinho.setOnClickListener {

                produtoAtual?.let {
                    dtProdutoViewModel.adicionarAoCarrinho(it)
                }
            Toast.makeText(this,
                "Produto: ${it.id} adicionado com sucesso!",
                Toast.LENGTH_SHORT).show()

        }

        binding.btnVerCarrinho.setOnClickListener {
            val abrirCarrinho = Intent(this, CarrinhoActivity::class.java)
            startActivity(abrirCarrinho)
        }


    }
}