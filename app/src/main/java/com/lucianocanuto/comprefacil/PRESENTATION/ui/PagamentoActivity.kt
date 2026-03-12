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
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.CarrinhoViewModel
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.PagamentoViewModel
import com.lucianocanuto.comprefacil.R
import com.lucianocanuto.comprefacil.UTIL.PagamentoStatus
import com.lucianocanuto.comprefacil.databinding.ActivityPagamentoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PagamentoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPagamentoBinding.inflate(layoutInflater)
    }
    private val pagamentoViewModel : PagamentoViewModel by viewModels()
    private val carrinhoViewModel: CarrinhoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val total = intent.getDoubleExtra("TOTAL",0.0)

        binding.txtTotal.text = "Total: R$ %.2f".format(total)

        binding.btnPagar.setOnClickListener {

            pagamentoViewModel.processarPagamento()

        }

        pagamentoViewModel.statusPagamento.observe(this){

            when(it){

                is PagamentoStatus.Processando -> {

                    binding.progressPagamento.visibility = View.VISIBLE
                    binding.txtStatusPagamento.text = "⏳ Processando pagamento..."
                    binding.btnPagar.isEnabled = false

                    binding.txtStatusPagamento.alpha = 0f
                    binding.txtStatusPagamento.animate()
                        .alpha(1f)
                        .setDuration(500)
                        .start()

                }

                is PagamentoStatus.Sucesso -> {

                    binding.progressPagamento.visibility = View.GONE
                    binding.txtStatusPagamento.text = "✅ Pagamento aprovado!"

                    carrinhoViewModel.limparCarrinho()

                    startActivity(Intent(this, MainActivity::class.java))
                    finish()

                }

                is PagamentoStatus.Erro -> {

                    binding.progressPagamento.visibility = View.GONE
                    binding.txtStatusPagamento.text = "❌ Falha no pagamento"

                }

                else -> {}

            }

        }

    }

}