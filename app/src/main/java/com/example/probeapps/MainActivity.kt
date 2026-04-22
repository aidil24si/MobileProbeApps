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

        //Kode ini harus selalu dipanggil saat butuh akses "user_pref"
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

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
            AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
//                dialog.dismiss()
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                // Kembali ke AuthActivity (Login)
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                // Menutup MainActivity
                finish()
            }

            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
                .show()
        }
    }
}