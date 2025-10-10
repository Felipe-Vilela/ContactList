package br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data source
    private val  contactList: MutableList<Contact> = mutableListOf()

    //Adapter
    private val contactAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            contactList.map { "${it.name} - ${it.phone}" }
        )

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillContactList()

        amb.contactTv.adapter = contactAdapter
    }

    private fun fillContactList(){
        for (i in 1 .. 50){
            contactList.add(
                Contact(
                    i,
                    "Name: $i",
                    "Address: $i",
                    "Phone: $i",
                    "Email: $i",
                    )
                )
        }
    }

}