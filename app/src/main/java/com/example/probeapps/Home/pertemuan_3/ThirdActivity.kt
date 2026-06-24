package com.example.probeapps.Home.pertemuan_3

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.probeapps.MainActivity
import com.example.probeapps.R
import com.example.probeapps.databinding.ActivityThirdBinding
import com.example.probeapps.utils.NotificationHelper
import com.example.probeapps.utils.PermissionHelper
import com.example.probeapps.utils.ReminderHelper // Pastikan ReminderHelper di-import jika berada di package utils
import java.util.Calendar // Tambahan import untuk mengambil waktu saat ini

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Cek permission saat halaman diakses
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // --- BAGIAN YANG DIUBAH Sesuai Arahan Modul ---
        binding.btnKirim.setOnClickListener {
            val noTujuan = binding.inputNoTujuan.text.toString()
            val intent = Intent(this, ThirdResultActivity::class.java)

            // startActivity(intent) // -> DINONAKTIFKAN

            // NotificationHelper.showNotification(
            //     this,
            //     "Pesanan Anda",
            //     "Halo $noTujuan, Pesanan Anda Sedang Diproses",
            //     intent
            // ) // -> DINONAKTIFKAN

            // Menyiapkan waktu: Waktu sekarang + 1 menit
            val calendar = Calendar.getInstance().apply {
                add(Calendar.MINUTE, 1)
            }

            // Mendaftarkan Alarm Reminder lewat ReminderHelper
            ReminderHelper.setReminder(
                context = this,
                hour = calendar.get(Calendar.HOUR_OF_DAY),
                minute = calendar.get(Calendar.MINUTE),
                title = "Reminder 1 Menit",
                message = "Halo $noTujuan, reminder ini muncul 1 menit setelah tombol ditekan",
                targetActivity = ThirdResultActivity::class.java
            )

            // Menampilkan Toast info agar user menunggu
            Toast.makeText(this, "Silahkan tunggu 1 Menit untuk menerima Notifikasi...", Toast.LENGTH_SHORT).show()
        }
        // ----------------------------------------------

        binding.btnKembali.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}