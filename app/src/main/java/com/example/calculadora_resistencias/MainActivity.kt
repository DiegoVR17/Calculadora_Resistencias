package com.example.calculadora_resistencias

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.calculadora_resistencias.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val resultObserver = Observer<String>{result ->
            mainBinding.textViewResult.text = result
        }

        mainViewModel.result.observe(this,resultObserver)

        val errorMsgObserver = Observer<String>{errorMsg ->
            Snackbar.make(view,errorMsg, Snackbar.LENGTH_INDEFINITE)
                .setAction("Continuar"){}
                .show()
        }
        mainViewModel.errorMsg.observe(this,errorMsgObserver)

        val valoresR = resources.getStringArray(R.array.resistencia_valores)
        val valoresM = resources.getStringArray(R.array.multiplicador_valores)
        val valoresT = resources.getStringArray(R.array.tolerancia_valores)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, valoresR)
        val adapterM = ArrayAdapter(this, android.R.layout.simple_spinner_item, valoresM)
        val adapterT = ArrayAdapter(this, android.R.layout.simple_spinner_item, valoresT)
        val coloresResistencia = mapOf(
            "Negro" to 0,
            "Marrón" to 1,
            "Rojo" to 2,
            "Naranja" to 3,
            "Amarillo" to 4,
            "Verde" to 5,
            "Azul" to 6,
            "Violeta" to 7,
            "Gris" to 8,
            "Blanco" to 9
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainBinding.spinnerBand1.adapter = adapter

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainBinding.spinnerBand2.adapter = adapter

        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainBinding.spinnerBand3.adapter = adapterM

        adapterT.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mainBinding.spinnerBand4.adapter = adapterT


        mainBinding.buttonCalculate.setOnClickListener {
            mainViewModel.getResis(mainBinding.spinnerBand1.selectedItem.toString(),
                mainBinding.spinnerBand2.selectedItem.toString(),
                mainBinding.spinnerBand3.selectedItem.toString(),
                mainBinding.spinnerBand4.selectedItem.toString(),
                coloresResistencia)
        }

    }
}