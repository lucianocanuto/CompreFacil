package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.DestalhesProdutoViewModel
import com.lucianocanuto.comprefacil.R
import com.lucianocanuto.comprefacil.UTIL.CompreFacilLogo
import com.lucianocanuto.comprefacil.UTIL.Resource
import com.lucianocanuto.comprefacil.databinding.ActivityProdutoDetalhesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProdutoDetalhes : AppCompatActivity() {

    private val binding by lazy {
        ActivityProdutoDetalhesBinding.inflate(layoutInflater)
    }

    private val dtProdutoViewModel: DestalhesProdutoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}