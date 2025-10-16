package com.dam.fragmentsexchange

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var boton1: Button

    private lateinit var boton2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //BINDEO
        boton1 = findViewById(R.id.button)
        boton2 = findViewById(R.id.button2)

        //CREAR INSTANCIAS DE LOS FRAGMENTOS

        val fragmentoUno = fragmentoUno()
        val fragmentoDos = fragmentoDos()

        //OYENTES

        boton1.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.intercambioFragment, fragmentoUno)
                .commit()
        }

        boton2.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.intercambioFragment, fragmentoDos)
                .commit()
        }
    }
}