package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.CarrinhoViewModel
import com.lucianocanuto.comprefacil.R
import com.lucianocanuto.comprefacil.UTIL.CompreFacilLogo
import com.lucianocanuto.comprefacil.UTIL.Resource
import com.lucianocanuto.comprefacil.databinding.ActivityCarrinhoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarrinhoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCarrinhoBinding.inflate(layoutInflater)
    }
    private lateinit var carrinhoAdapter : CarrinhoAdapter

    private val carrinhoViewModel : CarrinhoViewModel by viewModels()

    private var totalCarrinho: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)



        binding.txtLogo.text = CompreFacilLogo()

        binding.recyclerCarrinho.layoutManager = LinearLayoutManager(this)
        carrinhoAdapter = CarrinhoAdapter(emptyList()) { item ->
            carrinhoViewModel.removerItem(item)
            Toast.makeText(this, "Item removido", Toast.LENGTH_SHORT).show()

        }
        binding.recyclerCarrinho.adapter = carrinhoAdapter

        carrinhoViewModel.listarCarrinho()

        carrinhoViewModel.listaCarrinho.observe(this) { lista ->

            when (lista) {

                is Resource.Carregando -> {
                }

                is Resource.Sucesso -> {
                    carrinhoAdapter.atualizarLista(lista.data ?: emptyList())

                    if (lista.data.isNullOrEmpty()) {
                        binding.txtCarrinhoVazio.visibility = View.VISIBLE
                        binding.recyclerCarrinho.visibility = View.GONE
                        binding.btnFinalizar.visibility = View.GONE
                    } else {
                        binding.txtCarrinhoVazio.visibility = View.GONE
                        binding.recyclerCarrinho.visibility = View.VISIBLE
                        binding.btnFinalizar.visibility = View.VISIBLE
                    }
                }

                is Resource.Erro -> {
                    Toast.makeText(this, "${lista.mensagem}", Toast.LENGTH_SHORT).show()
                }

            }

        }

        carrinhoViewModel.totalCarrinho.observe(this) { total ->

            totalCarrinho = total

            binding.textSubTotal.text = "Total: R$ %.2f".format(total)
            binding.txtFrete.text = "Frete: Voce tem frete gratis!"
            binding.txtTotal.text = "Total: R$ %.2f".format(total)

        }




        binding.btnFinalizar.setOnClickListener {

            val usuarioLogado = getSharedPreferences("usuario", MODE_PRIVATE)
            val logado = usuarioLogado.getBoolean("logado", false)

            if (logado) {
                abrirPagamento()
            } else {
                val intent = Intent(this, CadastroActivity::class.java)
                intent.putExtra("TOTAL", totalCarrinho)
                startActivity(intent)

            }
        }

        binding.btnContinuarCompra.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }




    private fun abrirPagamento() {


        val intent = Intent(this, PagamentoActivity::class.java)

        intent.putExtra("TOTAL", totalCarrinho)

        startActivity(intent)

    }

    }


