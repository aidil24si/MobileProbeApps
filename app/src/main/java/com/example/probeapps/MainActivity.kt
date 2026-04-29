package com.example.probeapps

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.probeapps.databinding.ActivityMainBinding
import com.example.probeapps.Home.pertemuan_2.SecondActivity
import com.example.probeapps.Home.pertemuan_3.ThirdActivity
import com.example.probeapps.Home.pertemuan_4.FourthActivity
import com.example.probeapps.Home.pertemuan_5.FifthActivity
import com.example.probeapps.Home.pertemuan_7.SeventhActivity


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

        // Fitur Intent yang sudah ada
        binding.btnToFive.setOnClickListener {
            val intent = Intent(this, FifthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Fitur Intent yang sudah ada
        binding.btnToThree.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Fitur Intent yang sudah ada
        binding.btnToTwo.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        binding.btnToSeventh.setOnClickListener {
            val intent = Intent(this, SeventhActivity::class.java)
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