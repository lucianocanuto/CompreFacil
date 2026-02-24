package com.lucianocanuto.comprefacil.PRESENTATION.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucianocanuto.comprefacil.R
import com.lucianocanuto.comprefacil.databinding.ActivityProdutoDetalhesBinding

class ProdutoDetalhes : AppCompatActivity() {

    private val binding by lazy {
        ActivityProdutoDetalhesBinding.inflate(layoutInflater)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

    }
}