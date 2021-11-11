package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    //DATOS
    var numeroRonda = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jugar: Button = findViewById(R.id.Jugar)
        //Listener
        jugar.setOnClickListener {
            clickJugar()
            mostrarRonda()
            ejecutarSecuencia()
        }
    }

    fun clickJugar() {
        Toast.makeText(this, "OLE ", Toast.LENGTH_LONG).show()
    }

    fun mostrarRonda() {
        val ronda: TextView = findViewById(R.id.ronda)
        numeroRonda++
        ronda.text = ("RONDA: " + numeroRonda)
    }

    //CORRUTINES
    suspend fun brojo(rojo: Button) {
        rojo.setBackgroundColor(resources.getColor(R.color.rojoClaro))
        delay(2000L)
        rojo.setBackgroundColor(resources.getColor(R.color.rojo))
    }
    suspend fun bamarillo(amarillo: Button) {
        amarillo.setBackgroundColor(resources.getColor(R.color.amarilloClaro))
        delay(2000L)
        amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
        delay(2000L)
    }
    suspend fun bverde(verde: Button) {
        verde.setBackgroundColor(resources.getColor(R.color.verdeClaro))
        delay(2000L)
        verde.setBackgroundColor(resources.getColor(R.color.verde))
        delay(2000L)
    }
    suspend fun bazul(azul: Button) {
        azul.setBackgroundColor(resources.getColor(R.color.azulClaro))
        delay(2000L)
        azul.setBackgroundColor(resources.getColor(R.color.azul))
        delay(2000L)
    }

    suspend fun espera() {
        delay(10000L)

    }
    fun ejecutarSecuencia() {


        val rojo: Button = findViewById(R.id.Rojo)
        val amarillo: Button = findViewById(R.id.Amarillo)
        val azul: Button = findViewById(R.id.Verde)
        val verde: Button = findViewById(R.id.Azul)


        for(i in 1..4){
            val randomInt = java.util.Random().nextInt(4) + 1
            val secuencia = when (randomInt) {
                1 -> {
                    val job1 = GlobalScope.launch(Dispatchers.Main) {
                        brojo(rojo)
                    }
                }
                2 -> {
                    val job2 = GlobalScope.launch(Dispatchers.Main) {
                        bamarillo(amarillo)
                    }
                }
                3 -> {
                    val job3 = GlobalScope.launch(Dispatchers.Main) {
                        bazul(azul)
                    }
                }
                else -> {
                    val job4 = GlobalScope.launch(Dispatchers.Main) {
                        bverde(verde)
                    }
                }
            }
            val espera = GlobalScope.launch(Dispatchers.Main) {espera()}
        }
    }


    fun comprobarSecuencia() {

    }
}
