package com.example.hw2_lyco

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.PrintStream
import java.util.*

class MainActivity : AppCompatActivity() {

   val listado = arrayListOf<String>()
    var band = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Larchivo()

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
            crea_archivo(dato.text.toString())
        }
    }

    fun Larchivo(){
        val entrada = Scanner ( openFileInput("tareas.txt"))

        while(entrada.hasNextLine()) {
            val linea = entrada.nextLine()
            listado.addAll(linea.split(","))
        }
        entrada.close()
    }

    fun crea_archivo(opcion :String){
        val salida = PrintStream (openFileOutput("tareas.txt", Context.MODE_APPEND))
        salida.println("$opcion")
        salida.close()
    }
    fun actualiza_lista(m:String){
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado)
        listaAgregar.adapter = adaptador
        if (band == 0) {
            listado.add(m)
            crea_archivo(m)
        }
        if (band == 1) {
            listado.remove(m)
            band=0
        }
        adaptador.notifyDataSetChanged()
    }

    fun agregarItem(este : View){
        val nuevo = findViewById(R.id.editText) as EditText
        if(nuevo.text.toString() == ""){
            Toast.makeText(this, "No hay información para agregar", Toast.LENGTH_SHORT).show()
        }
        else {
            actualiza_lista(nuevo.text.toString())
            nuevo.setText("")
        }
    }
}
