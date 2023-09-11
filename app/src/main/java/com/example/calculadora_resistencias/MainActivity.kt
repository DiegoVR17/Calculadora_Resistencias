package com.example.calculadora_resistencias

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import com.example.calculadora_resistencias.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

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
            val band1Value = mainBinding.spinnerBand1.selectedItem.toString()
            val band2Value = mainBinding.spinnerBand2.selectedItem.toString()
            val band3Value = mainBinding.spinnerBand3.selectedItem.toString()
            val band4Value = mainBinding.spinnerBand4.selectedItem.toString()

            // Obtén los valores de resistencia de las bandas
            val valorBanda1 = coloresResistencia[band1Value] ?: 0
            val valorBanda2 = coloresResistencia[band2Value] ?: 0
            val multiplicador = when (band3Value) {
                "Negro" -> 1
                "Marrón" -> 10
                "Rojo" -> 100
                "Naranja" -> 1000
                "Amarillo" -> 10000
                "Verde" -> 100000
                "Azul" -> 1000000
                "Violeta" -> 10000000
                "Gris" -> 100000000
                "Blanco" -> 1000000000
                else -> 0
            }

            val tolerancia = when (band4Value) {
                "Dorado" -> 5
                "Plateado" -> 10
                else -> 20
            }

            // Calcula el valor de la resistencia
            val resistencia = (valorBanda1 * 10 + valorBanda2) * multiplicador

            // Muestra el valor de la resistencia en el TextView
            mainBinding.textViewResult.text = "Valor de resistencia calculado: $resistencia ohms, Tolerancia: ±$tolerancia%"

        }
    }
}