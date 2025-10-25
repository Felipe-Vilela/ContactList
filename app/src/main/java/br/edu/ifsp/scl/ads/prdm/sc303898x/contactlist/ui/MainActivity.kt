package br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.R
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.adapter.ContactAdapter
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.databinding.ActivityMainBinding
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.model.Contact

class MainActivity : AppCompatActivity() {

    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data source
    private val  contactList: MutableList<Contact> = mutableListOf()

    //Adapter
    private val contactAdapter: ContactAdapter by lazy {
        ContactAdapter(this, contactList)
    }

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        setSupportActionBar(amb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.R.string.list)

        fillContactList()

        amb.contactTv.adapter = contactAdapter

        carl = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if (result.resultCode == RESULT_OK){
                val contact = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    result.data?.getParcelableExtra(EXTRA_CONTACT, Contact::class.java)
                } else {
                    result.data?.getParcelableExtra(EXTRA_CONTACT)
                }
                contact?.let{ newContact ->
                    contactList.add(newContact)
                    contactAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.add_contact_mi -> {
                carl.launch( Intent(this, ContactActivity::class.java))
                true
            }
            else -> { false }
        }
    }
    private fun fillContactList(){
        for (i in 1 .. 10){
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