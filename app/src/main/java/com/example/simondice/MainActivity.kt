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
    var almacenamiento=arrayListOf<String>()
    var almacenamiento2 = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val comprobar:Button=findViewById(R.id.comprobar)
        val jugar: Button = findViewById(R.id.Jugar)
        comprobar.setEnabled(false)
        jugar.setEnabled(true)
        //Listener
        jugar.setOnClickListener {

            almacenamiento.clear()
            almacenamiento2.clear()
            clickJugar()
            mostrarRonda()
            ejecutarSecuencia()
            comprobar.setEnabled(true)
            jugar.setEnabled(false)
        }
        comprobar.setOnClickListener{
            turnoJugador()
            comprobarSecuencia()
            comprobar.setEnabled(false)
            jugar.setEnabled(true)

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
        delay(500L)
        rojo.setBackgroundColor(resources.getColor(R.color.rojo))
    }
    suspend fun bamarillo(amarillo: Button) {
        amarillo.setBackgroundColor(resources.getColor(R.color.amarilloClaro))
        delay(500L)
        amarillo.setBackgroundColor(resources.getColor(R.color.amarillo))
        delay(500L)
    }
    suspend fun bverde(verde: Button) {
        verde.setBackgroundColor(resources.getColor(R.color.verdeClaro))
        delay(500L)
        verde.setBackgroundColor(resources.getColor(R.color.verde))
        delay(500L)
    }
    suspend fun bazul(azul: Button) {
        azul.setBackgroundColor(resources.getColor(R.color.azulClaro))
        delay(500L)
        azul.setBackgroundColor(resources.getColor(R.color.azul))
        delay(500L)
    }


    suspend fun eleccion() {
        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)

        for(i in 1..4){

            delay(1000L)
            val randomInt = java.util.Random().nextInt(4) + 1
            val secuencia = when (randomInt) {
                1 -> {
                    val job1 = GlobalScope.launch(Dispatchers.Main) {
                        brojo(rojo)
                    }
                    almacenamiento.add("rojo")

                }
                2 -> {
                    val job2 = GlobalScope.launch(Dispatchers.Main) {
                        bamarillo(amarillo)
                    }
                    almacenamiento.add("amarillo")
                }
                3 -> {
                    val job3 = GlobalScope.launch(Dispatchers.Main) {
                        bazul(azul)
                    }
                    almacenamiento.add("azul")
                }
                else -> {
                    val job4 = GlobalScope.launch(Dispatchers.Main) {
                        bverde(verde)
                    }
                    almacenamiento.add("verde")
                }
            }
        }
    }
    fun ejecutarSecuencia() {
        val job = GlobalScope.launch(Dispatchers.Main) {
            eleccion()
        }
    }
    fun turnoJugador() {

        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)
        for(i in 1..4) {
            rojo.setOnClickListener { almacenamiento2.add("rojo") }
            amarillo.setOnClickListener { almacenamiento2.add("amarillo") }
            verde.setOnClickListener { almacenamiento2.add("verde") }
            azul.setOnClickListener { almacenamiento2.add("azul") }
        }
    }
    fun comprobarSecuencia(){
        if (almacenamiento == almacenamiento2){
            Toast.makeText(this, "PERFECTO,SUBES DE NIVEL ", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "DESCIENDES DE NIVEL ", Toast.LENGTH_LONG).show()
            val ronda: TextView = findViewById(R.id.ronda)
            numeroRonda--
            ronda.text = ("RONDA: " + numeroRonda)
        }
    }
}
