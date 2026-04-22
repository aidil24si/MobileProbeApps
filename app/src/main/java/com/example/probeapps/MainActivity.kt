package com.example.probeapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.probeapps.databinding.ActivityFourthBinding
import com.example.probeapps.databinding.ActivityMainBinding
import com.example.probeapps.databinding.ActivityThirdBinding
import com.example.probeapps.pertemuan_3.ThirdResultActivity
import com.example.probeapps.pertemuan_4.FourthActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        // ... kode ViewCompat Anda tetap di sini ...

        // Fitur Intent yang sudah ada
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // --- TAMBAHKAN KODE LOGOUT DI BAWAH INI ---
        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin keluar?")

            builder.setPositiveButton("Ya") { _, _ ->
                // Kembali ke AuthActivity (Login)
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                // Menutup MainActivity
                finish()
            }

            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }

            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}