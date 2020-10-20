package br.com.teste.sicredi.feature.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.teste.sicredi.R

class EventDetailActivity : AppCompatActivity() {

    companion object {
        fun launchIntent(context: Context) = Intent(context, EventDetailActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}