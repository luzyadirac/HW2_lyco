package com.example.hw2_lyco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //val listado = mutableListOf<string>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listado = arrayListOf<String>()
            listado.add("uno")
            listado.add("dos")
            listado.add("tres")

        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado)
        listaAgregar.adapter = adaptador

        /*listaAgregar.setOnItemLongClickListener { parent, view, position, id ->
            val textico = view as TextView
            listado.remove(textico.text.toString())
            Toast.makeText(this, view.text.toString(), Toast.LENGTH_SHORT).show()
            adaptador.notifyDataSetChanged()
            listaAgregar.adapter = adaptador
        }   */
        Lista_diara.setOnItemClickListener { Lista, dato, position, id ->
            val textView = dato as TextView
            Toast.makeText(this, dato.text.toString(), Toast.LENGTH_SHORT).show()
            listado.add(dato.text.toString())
            adaptador.notifyDataSetChanged()
            }
    }
}
