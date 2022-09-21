package com.xhateya.idn.hadeeth

import android.app.ProgressDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.xhateya.idn.hadeeth.model.ModelHadith
import com.xhateya.idn.hadeeth.network.Api

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import java.util.ArrayList


class MainActivity : AppCompatActivity(), HadithAdapter.OnSelectedData {


    private var hadithAdapter: HadithAdapter? = null
    var modelHadith: MutableList<ModelHadith> = ArrayList()
    var progressDialog: ProgressDialog? = null
    private var today: String? = null
    private var date: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val todaysDate = Calendar.getInstance().time
        today = DateFormat.format("EEEE", todaysDate) as String
        date = DateFormat.format("d MMMM yyyy", todaysDate) as String
        tvToday.text = "$today,"
        tvDate.text = date

        rvHadith.layoutManager = LinearLayoutManager(this)
        rvHadith.setHasFixedSize(true)


        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("Showing Data...")

        Listhadith()

    }

    private fun Listhadith() {
        progressDialog!!.show()
        AndroidNetworking.get(Api.URL_HADITH_LIST)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener{
                override fun onResponse(response: JSONArray) {
                    for (i in 0 until response.length()) {
                        try {
                            progressDialog!!.dismiss()
                            val dataApi = ModelHadith()
                            val jsonObject = response.getJSONObject(i)
                            dataApi.urutan = jsonObject.getString("urutan")
                            dataApi.nama = jsonObject.getString("nama")
                            dataApi.arab = jsonObject.getString("arab")
                            dataApi.arti = jsonObject.getString("arti")
                            dataApi.arti = jsonObject.getString("arti")
                            dataApi.nomer= jsonObject.getString("nomer")
                            modelHadith.add(dataApi)
                            showListhadith()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(
                                this@MainActivity, "Failed to show data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                override fun onError(anError: ANError) {
                    progressDialog!!.dismiss()
                    Toast.makeText(
                        this@MainActivity, "No internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    private fun showListhadith() {
        hadithAdapter= HadithAdapter(modelHadith,this)
        rvHadith!!.adapter= hadithAdapter
    }

    override fun onSelected(modelHadith: ModelHadith?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("detailHadith", modelHadith)
        startActivity(intent)
    }


}



