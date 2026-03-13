package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.CarrinhoViewModel
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
    private val carrinhoViewModel: CarrinhoViewModel by viewModels()
    private lateinit var adapter : ProdutosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtCarrinhoCont.setOnClickListener {
            startActivity(Intent(this, CarrinhoActivity::class.java))
            finish()
        }

        produtoViewModel.buscarProduto()
        // Carrega carrinho ao abrir tela
        carrinhoViewModel.listarCarrinho()
        binding.txtLogo.text = CompreFacilLogo()


        //Aqui configura recyclerView
        adapter = ProdutosAdapter { produto ->
            val click = Intent(this,ProdutoDetalhes::class.java)
            click.putExtra("produtoId" , produto.id)
            startActivity(click)
        }
        binding.rvListaProdutos.layoutManager = LinearLayoutManager(this)
        binding.rvListaProdutos.adapter = adapter

        produtoViewModel.produto.observe(this){ retorno ->

            when(retorno){
                is Resource.Carregando -> {

                    binding.loadingContainer.visibility = View.VISIBLE

                }
                is Resource.Sucesso -> {
                    binding.loadingContainer.visibility = View.GONE
                    adapter.submitList(retorno.data)

                }
                is Resource.Erro -> {
                    binding.loadingContainer.visibility = View.GONE
                    Toast.makeText(this, "${retorno.mensagem}", Toast.LENGTH_SHORT).show()

                }
            }
        }
        // Observer do contador do carrinho
        carrinhoViewModel.contadorCarrinho.observe(this){
            carrinhoViewModel.listarCarrinho()
            binding.txtCarrinhoCont.text = it.toString()

            binding.txtCarrinhoCont.visibility =
                if(it > 0) View.VISIBLE else View.GONE
        }

    }
}