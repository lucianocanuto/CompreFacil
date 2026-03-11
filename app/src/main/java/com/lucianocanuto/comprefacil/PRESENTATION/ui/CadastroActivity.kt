package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucianocanuto.comprefacil.PRESENTATION.viewmodel.CadastroViewModel
import com.lucianocanuto.comprefacil.R
import com.lucianocanuto.comprefacil.databinding.ActivityCadastroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }
    private val cadastroViewModel: CadastroViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnBuscarCep.setOnClickListener {

            val cep = binding.editCep.text.toString()

            if(cep.isNotEmpty()){
                cadastroViewModel.buscarCep(cep)
            }
        }

        cadastroViewModel.endereco.observe(this){

            binding.editRua.setText(it.logradouro)
            binding.editBairro.setText(it.bairro)
            binding.editCidade.setText(it.localidade)
            binding.editEstado.setText(it.uf)
        }

        binding.btnCadastrar.setOnClickListener {
            val usuarioLogado = getSharedPreferences("usuario", MODE_PRIVATE)
            usuarioLogado.edit().putBoolean("logado",true).apply()

            Toast.makeText(this,"Cadastro realizado!",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, CarrinhoActivity::class.java))
            finish()
        }

    }
}