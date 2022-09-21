package com.xhateya.idn.hadeeth
import android.app.ProgressDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.view.MenuItem
import com.androidnetworking.BuildConfig
import com.xhateya.idn.hadeeth.model.ModelHadith
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null
    private var nama: String? = null
    private var arab: String?= null
    private var terjemahan:String?= null
    private var nomer: String? = null
    private var modelHadith: ModelHadith? = null
    private var mHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        toolbar_detail.title = null
        setSupportActionBar(toolbar_detail)
        if (BuildConfig.DEBUG && supportActionBar == null) {
            error("Assertion failed")
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        mHandler = Handler()


        modelHadith = intent.getSerializableExtra("detailHadith") as ModelHadith
        if (modelHadith != null) {
            nomer = modelHadith!!.nomer
            nama= modelHadith!!.nama
            terjemahan = modelHadith!!.arti
            arab = modelHadith!!.arab




            tvTitleDetail.text= nomer
            tvArab.text = arab
            tvTitle.text = nama
            tvTerjemahan.text = terjemahan



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) tvTerjemahan.text = Html.fromHtml(
                terjemahan,
                Html.FROM_HTML_MODE_COMPACT
            )
            else {
                tvTerjemahan.text = Html.fromHtml(terjemahan)
            }

    }

        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Showing Data...")

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}