package com.example.appimcimm

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculadoraDeIMC :  AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora_de_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Definimos todos los Botones, TextView y EditText que componen la app
        val btnCalcular = findViewById<Button>(R.id.botonCalcular)
        val textoResultadoIMC = findViewById<TextView>(R.id.textViewResultadoIMC)
        val editTextMasa = findViewById<EditText>(R.id.editTextPesoEnKG)
        val editTextEstatura = findViewById<EditText>(R.id.editTextAlturaEnMetros)
        val textoRangoIMC = findViewById<TextView>(R.id.textViewRangoIMC)

        // Dotamos de funcionalidad al btn de calcular
        btnCalcular.setOnClickListener() {
            val masa = editTextMasa.text.toString().toDouble()
            val estatura = editTextEstatura.text.toString().toDouble()

            // Mtd para redondear el numero Double en 2 decimales
            val imc = calcularIMC(masa, estatura)
            val imcFormateado = DecimalFormat("#.##").format(imc)

            // Imprimimos la linea de txt mediante la TextView habilitada
            textoResultadoIMC.text = "Tu IMC actual es de $imcFormateado"

            // Clasificación de la OMS del estado nutricional de acuerdo con el IMC
            when (imc) {
                in 0.00..18.49 -> {
                    textoRangoIMC.text = "Clasificación: Infrapeso"
                }

                in 18.50..24.99 -> {
                    textoRangoIMC.text = "Clasificación: Peso normal"
                }

                in 25.00..29.99 -> {
                    textoRangoIMC.text = "Clasificación: Sobrepeso"
                }

                in 30.00..34.99 -> {
                    textoRangoIMC.text = "Clasificación: Obesidad de clase I"
                }

                in 35.00..39.99 -> {
                    textoRangoIMC.text = "Clasificación: Obesidad de clase II"
                }

                else -> {
                    textoRangoIMC.text = "Clasificación: Obesidad de clase III"
                }
            }

            val toast = Toast.makeText(this, "Procesado correctamente",Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    // Fun para calcular el IMC
    fun calcularIMC(peso: Double, altura: Double): Double {
        val alturaAlCuadrado: Double = altura * altura

        val imc = peso / alturaAlCuadrado
        return imc
    }
}