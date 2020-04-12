package com.example.hw2_lyco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   val listado = arrayListOf<String>()
    var band = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Lista_diara.setOnItemClickListener { Lista, dato, position, id ->
            val textView = dato as TextView
            Toast.makeText(this, dato.text.toString(), Toast.LENGTH_SHORT).show()
            actualiza_lista(dato.text.toString())
            band = 0
            }

        /* De esta manera no me deja borrar
        listaAgregar.setOnItemLongClickListener { parent, view, position, id ->
            val textico = view as TextView
            listado.remove(textico.text.toString())
            Toast.makeText(this, view.text.toString(), Toast.LENGTH_SHORT).show()
            adaptador.notifyDataSetChanged()
            listaAgregar.adapter = adaptador
            return true
        }*/
        listaAgregar.setOnItemClickListener { Lista, dato, position, id ->
            val textView = dato as TextView
            band = 1
            actualiza_lista(dato.text.toString())
        }
    }
    fun actualiza_lista(m:String){
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado)
        listaAgregar.adapter = adaptador
        if (band == 0) {
            listado.add(m)
        }
        if (band == 1) {
            listado.remove(m)
            band=0
        }
        adaptador.notifyDataSetChanged()
    }

    fun agregarItem(este : View){
        val nuevo = findViewById(R.id.editText) as EditText
        actualiza_lista(nuevo.text.toString())
        nuevo.setText("")
    }
}
