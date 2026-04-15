package com.example.probeapps.pertemuan_5

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.probeapps.R
import com.example.probeapps.databinding.ActivityFifthBinding

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding
    private var mActionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Inisialisasi Toolbar (Materi 5.1)
        setSupportActionBar(binding.toolbarFifth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // 2. Popup Menu (Materi 5.3)
        binding.btnShowPopup.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menuInflater.inflate(R.menu.main_menu, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                Toast.makeText(this, "Selected: ${item.title}", Toast.LENGTH_SHORT).show()
                true
            }
            popup.show()
        }

        // 3. Contextual Action Mode (Materi 5.4)
        binding.tvLongClick.setOnLongClickListener {
            if (mActionMode != null) return@setOnLongClickListener false

            mActionMode = startActionMode(object : ActionMode.Callback {
                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.main_menu, menu)
                    mode?.title = "Choose Action"
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    Toast.makeText(this@FifthActivity, "Action: ${item?.title}", Toast.LENGTH_SHORT).show()
                    mode?.finish()
                    return true
                }

                override fun onDestroyActionMode(mode: ActionMode?) {
                    mActionMode = null
                }
            })
            true
        }
    }

    // 4. Option Menu (Materi 5.2)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> {
                Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
                super.onOptionsItemSelected(item)
            }
        }
    }
}