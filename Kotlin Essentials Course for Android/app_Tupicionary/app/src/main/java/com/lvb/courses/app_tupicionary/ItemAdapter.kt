package com.lvb.courses.app_tupicionary

import android.content.Context
import android.widget.ArrayAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import kotlin.collections.ArrayList

class ItemAdapter(context: Context, list: ArrayList<Item>, private val backgroundColor: Int) :
    ArrayAdapter<Item?>(
        context, 0, list as List<Item?>
    ) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemView = convertView

        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val item = getItem(position)

        val titulo = itemView!!.findViewById<View>(R.id.item_titulo) as TextView
        titulo.text = item!!.titulo

        val desc = itemView.findViewById<View>(R.id.item_desc) as TextView
        desc.text = item.descricao

        val icone = itemView.findViewById<View>(R.id.item_icone) as ImageView
        icone.setImageResource(item.imagem)

        val layoutTextos = itemView.findViewById<View>(R.id.item_layout_textos) as LinearLayout
        val cor = ContextCompat.getColor(context, backgroundColor)
        layoutTextos.setBackgroundColor(cor)

        return itemView
    }
}