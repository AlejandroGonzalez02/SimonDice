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
    var numErrores=0
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

        //QUE PASA CANDO LLE DAN A XOGAR
        jugar.setOnClickListener {

            //LIMPAMOS ARRAYS
            almacenamiento.clear()
            almacenamiento2.clear()

            //PARA QUE NON PODAN PREMER NINGÚN BOTON
            val rojo: Button = findViewById(R.id.rojo)
            val amarillo: Button = findViewById(R.id.amarillo)
            val verde: Button = findViewById(R.id.verde)
            val azul: Button = findViewById(R.id.azul)
            rojo.setEnabled(false);
            amarillo.setEnabled(false);
            verde.setEnabled(false);
            azul.setEnabled(false);

            clickJugar()
            mostrarRonda()
            ejecutarSecuencia()
            comprobar.setEnabled(true)
            jugar.setEnabled(false)
            turnoJugador()
        }

        //QUE PASA CANDO LLE DAN A COMPROBAR
        comprobar.setOnClickListener{
            comprobarSecuencia()
            comprobar.setEnabled(false)
            jugar.setEnabled(true)
            println(almacenamiento)
            println(almacenamiento2)

    }

    }

    fun clickJugar() {
        Toast.makeText(this, "RECUERDA LA SECUENCIA!!!", Toast.LENGTH_LONG).show()
    }

    fun mostrarRonda() {
        //CONTADOR DE RONDA
        val ronda: TextView = findViewById(R.id.ronda)
        numeroRonda++
        ronda.text = ("RONDAS BIEN: " + numeroRonda)
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
                    val job2 = GlobalScope.launch(Dispatchers.Main) {bamarillo(amarillo)}
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
        delay(350L)
        //DEIXAMOS QUE ESCRIBAN
        rojo.setEnabled(true);
        amarillo.setEnabled(true);
        verde.setEnabled(true);
        azul.setEnabled(true);
        Toast.makeText(this, "REPITE LA SECUENCIA:", Toast.LENGTH_LONG).show()
    }
    fun ejecutarSecuencia() {
        val job = GlobalScope.launch(Dispatchers.Main) {
            eleccion()
        }

    }
    fun turnoJugador() {
        //CHAMAMOS AOS BOTONS
        val rojo: Button = findViewById(R.id.rojo)
        val amarillo: Button = findViewById(R.id.amarillo)
        val verde: Button = findViewById(R.id.verde)
        val azul: Button = findViewById(R.id.azul)

        //GARDAMOS SECUENCIA
            rojo.setOnClickListener(){ almacenamiento2.add("rojo") }
            amarillo.setOnClickListener(){ almacenamiento2.add("amarillo") }
            azul.setOnClickListener(){ almacenamiento2.add("azul") }
            verde.setOnClickListener(){ almacenamiento2.add("verde") }

        //NON DEIXAMOS QUE VOLVAN PREMER NOS BOTONS
        rojo.setEnabled(false);
        amarillo.setEnabled(false);
        verde.setEnabled(false);
        azul.setEnabled(false);
    }
    fun comprobarSecuencia(){
        if (almacenamiento == almacenamiento2){
                Toast.makeText(this, "PERFECTO \uD83D\uDE01", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "FALLASTE \uD83D\uDE22", Toast.LENGTH_LONG).show()

            val errores: TextView=findViewById(R.id.errores)
            numErrores++
            errores.text=("RONDAS MAL: "+numErrores)
        }
    }
}
