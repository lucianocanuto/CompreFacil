package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.ProdutosViewModel
import com.lucianocanuto.comprefacil.UTIL.CompreFacilLogo
import com.lucianocanuto.comprefacil.UTIL.Resource
import com.lucianocanuto.comprefacil.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val produtoViewModel: ProdutosViewModel by viewModels()
    private lateinit var adapter : ProdutosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        produtoViewModel.buscarProduto()
        binding.txtLogo.text = CompreFacilLogo()


        //Aqui configura recyclerView
        adapter = ProdutosAdapter()
        binding.rvListaProdutos.layoutManager = LinearLayoutManager(this)
        binding.rvListaProdutos.adapter = adapter

        produtoViewModel.produto.observe(this){ retorno ->

            when(retorno){
                is Resource.Carregando -> {

                }
                is Resource.Sucesso -> {
                    adapter.submitList(retorno.data)

                }
                is Resource.Erro -> {
                    Toast.makeText(this, "${retorno.mensagem}", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}