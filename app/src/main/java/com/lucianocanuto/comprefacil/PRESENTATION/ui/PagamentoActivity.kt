package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
                    binding.btnPagar.isEnabled = false

                }

                is PagamentoStatus.Sucesso -> {

                    binding.progressPagamento.visibility = View.GONE

                    Toast.makeText(
                        this,
                        it.mensagem,
                        Toast.LENGTH_LONG
                    ).show()

                }

                is PagamentoStatus.Erro -> {

                    Toast.makeText(
                        this,
                        it.erro,
                        Toast.LENGTH_LONG
                    ).show()

                }

                else -> {}

            }

        }

    }

}