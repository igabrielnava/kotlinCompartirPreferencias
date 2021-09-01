package com.example.compartirpreferencias

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var nombre:TextView
    lateinit var correo:TextView
    lateinit var compartirPreferencias:SharedPreferences

    lateinit var boton_guardar: Button
    lateinit var boton_limpiar:Button
    lateinit var boton_leer:Button

    companion object{
        val LLAVE_MIS_PREFERENCIAS = "mis preferencias"
        val LLAVE_NOMBRE = "llave nomber"
        val LLAVE_CORREO = "llave correo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nombre = findViewById<TextView>(R.id.nombre_edit_id)
        correo = findViewById<TextView>(R.id.correo_edit_id)

        boton_guardar = findViewById<Button>(R.id.boton_guardar)
        boton_limpiar = findViewById<Button>(R.id.boton_limpiar)
        boton_leer = findViewById<Button>(R.id.boton_leer)

        compartirPreferencias = getSharedPreferences(LLAVE_MIS_PREFERENCIAS, Context.MODE_PRIVATE)

        if(compartirPreferencias.contains(LLAVE_NOMBRE))
            nombre.text = compartirPreferencias.getString(LLAVE_NOMBRE,"")
        if(compartirPreferencias.contains(LLAVE_CORREO))
            correo.text = compartirPreferencias.getString(LLAVE_CORREO,"")

        boton_guardar.setOnClickListener {
            val editor = compartirPreferencias.edit()
            editor.putString(LLAVE_NOMBRE, nombre.text.toString())
            editor.putString(LLAVE_CORREO, correo.text.toString())
            editor.commit()
            Toast.makeText(this@MainActivity, "Datos guardados", Toast.LENGTH_LONG).show()
        }

        boton_limpiar.setOnClickListener {
            nombre.text = ""
            correo.text = ""
            Toast.makeText(this@MainActivity, "Datos borrados", Toast.LENGTH_LONG).show()
        }

        boton_leer.setOnClickListener {
            compartirPreferencias = getSharedPreferences(LLAVE_MIS_PREFERENCIAS,Context.MODE_PRIVATE)
            if(compartirPreferencias.contains(LLAVE_NOMBRE))
                nombre.text = compartirPreferencias.getString(LLAVE_NOMBRE, "")
            if(compartirPreferencias.contains(LLAVE_CORREO))
                correo.text = compartirPreferencias.getString(LLAVE_CORREO, "")

            Toast.makeText(this@MainActivity, "Datos recuperados", Toast.LENGTH_LONG).show()
        }
    }
}