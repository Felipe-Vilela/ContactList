package br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.R
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.databinding.TileContactBinding
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.model.Contact
import org.w3c.dom.Text

class ContactAdapter(context: Context, private val contactList: MutableList<Contact>):
    ArrayAdapter<Contact>(context, R.layout.tile_contact, contactList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //Recuperar contato que sera usado ara preencher os campos da celula
        val contact = contactList[position]

        //verifico se existe um celula reciclada ou se é necessário inflar uma nova
        var contactTileView = convertView
        if(contactTileView == null){
             TileContactBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false).apply {
                    contactTileView = root
                    val tileContactViewHolder = TileContactViewHolder(
                        contactTileView.findViewById(R.id.name_tv)!!,
                        contactTileView.findViewById(R.id.email_tv)!!
                    )
                 contactTileView.tag = tileContactViewHolder
            }
        }

        //preencher a celula com os dados do contato

        val tlViewHolder = contactTileView?.tag as TileContactViewHolder
        tlViewHolder.nameTv.text = contact.name
        tlViewHolder.emailTv.text = contact.email

        //Retornar a view preenchida
        return contactTileView!!
    }

    private data class TileContactViewHolder(val nameTv: TextView, val emailTv: TextView)
}