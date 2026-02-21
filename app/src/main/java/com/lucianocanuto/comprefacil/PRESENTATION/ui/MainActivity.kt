package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        produtoViewModel.buscarProduto()
        binding.txtLogo.text = CompreFacilLogo()

        produtoViewModel.produto.observe(this){ retorno ->

            when(retorno){
                is Resource.Carregando -> {

                }
                is Resource.Sucesso -> {


                }
                is Resource.Erro -> {

                }
            }
        }
    }
}