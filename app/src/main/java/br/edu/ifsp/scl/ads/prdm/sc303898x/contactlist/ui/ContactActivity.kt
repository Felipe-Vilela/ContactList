package br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.R
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.model.Constant.EXTRA_CONTACT
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.databinding.ActivityContactBinding
import br.edu.ifsp.scl.ads.prdm.sc303898x.contactlist.model.Contact

class ContactActivity : AppCompatActivity() {
    private val acb: ActivityContactBinding by lazy {
        ActivityContactBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(acb.root)

        setSupportActionBar(acb.toolbarIn.toolbar)
        supportActionBar?.subtitle = getString(R.string.contact_details)

        with (acb){
            saveBt.setOnClickListener {
                val contact = Contact(
                    hashCode(),
                    nameEt.text.toString(),
                    addressEt.text.toString(),
                    phoneEt.text.toString(),
                    emailEt.text.toString(),
                ).let{
                    Intent().putExtra(EXTRA_CONTACT, it).apply {
                        setResult(RESULT_OK, this)
                        finish()
                    }
                }
            }
        }
    }
}