package com.example.calculadora_resistencias

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class MainViewModel: ViewModel() {

    val result : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()
    val errorMsg: LiveData<String> = _errorMsg
    fun getResis(
        band1Value: String,
        band2Value: String,
        band3Value: String,
        band4Value: String,
        coloresResistencia: Map<String, Int>
    ) {
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
        result.value = "Valor de resistencia calculado: $resistencia ohms, Tolerancia: ±$tolerancia%"

    }

}