package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lucianocanuto.comprefacil.DOMAIN.model.Produto
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.CarrinhoViewModel
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.DestalhesProdutoViewModel
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
    private val carrinhoViewModel: CarrinhoViewModel by viewModels()

    private var produtoAtual: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e("teste", "TELA DETALHES ABRIU")

        enableEdgeToEdge()
        setContentView(binding.root)

        binding.txtLogo.text = CompreFacilLogo()

        binding.txtCarrinhoCont.setOnClickListener {
            startActivity(Intent(this, CarrinhoActivity::class.java))
            finish()
        }

        // Carrega carrinho ao abrir tela
        carrinhoViewModel.listarCarrinho()



        val id = intent.getIntExtra("produtoId", -1)

        if (id != -1) {
            dtProdutoViewModel.buscarProdutoDt(id)
        } else {
            finish()
        }

        dtProdutoViewModel.produtoDt.observe(this) { resource ->

            when (resource) {

                is Resource.Carregando -> {
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
                    Toast.makeText(this, "Erro ao carregar produto", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.btnCarrinho.setOnClickListener {
            produtoAtual?.let {
                dtProdutoViewModel.adicionarAoCarrinho(it)

                Toast.makeText(
                    this,
                    "Produto adicionado ao carrinho!",
                    Toast.LENGTH_SHORT
                ).show()

            }

            animarProdutoAoCarrinho(
                binding.imgProdutoDetalhe,
                binding.iconCarrinho
            )

        }


        binding.btnVerCarrinho.setOnClickListener {
            val abrirCarrinho = Intent(this, CarrinhoActivity::class.java)
            startActivity(abrirCarrinho)

        }

        binding.btnContinuarCompra.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        // Observer do contador do carrinho
        carrinhoViewModel.contadorCarrinho.observe(this){
            carrinhoViewModel.listarCarrinho()
            binding.txtCarrinhoCont.text = it.toString()

            binding.txtCarrinhoCont.visibility =
                if(it > 0) View.VISIBLE else View.GONE
        }

    }

    fun animarProdutoAoCarrinho(imgProduto: ImageView, carrinho: View) {

        val root = window.decorView as ViewGroup

        val animImg = ImageView(this)
        animImg.setImageDrawable(imgProduto.drawable)

        val locationProduto = IntArray(2)
        val locationCarrinho = IntArray(2)

        imgProduto.getLocationOnScreen(locationProduto)
        carrinho.getLocationOnScreen(locationCarrinho)

        val params = FrameLayout.LayoutParams(
            imgProduto.width,
            imgProduto.height
        )

        animImg.layoutParams = params
        animImg.x = locationProduto[0].toFloat()
        animImg.y = locationProduto[1].toFloat()

        root.addView(animImg)

        animImg.animate()
            .x(locationCarrinho[0].toFloat())
            .y(locationCarrinho[1].toFloat())
            .scaleX(0.3f)
            .scaleY(0.3f)
            .setDuration(600)
            .withEndAction {

                root.removeView(animImg)

            }
            .start()
    }


}