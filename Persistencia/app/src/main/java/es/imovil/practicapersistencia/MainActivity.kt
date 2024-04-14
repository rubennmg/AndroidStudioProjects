package es.imovil.practicapersistencia

import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.preference.PreferenceManager
import es.imovil.practicapersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        Navigation.setViewNavController(binding.fab, navController)

        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val filename = sp.getString("filename.json", "books_repository")
        bookViewModel.filename = filename!!

        binding.fab.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.DataEntryFragment)
        )

        navHostFragment.navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when(destination.id) {
                R.id.ListFragment -> binding.fab.visibility = View.VISIBLE
                else -> binding.fab.visibility = View.GONE
            }
        }

        // default preferences from preferences.xml
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

    override fun onStart() {
        super.onStart()
        bookViewModel.restoreBookList()
    }

    override fun onPause() {
        super.onPause()
        bookViewModel.saveBookList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.settings -> NavigationUI.onNavDestinationSelected(item, navController)
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "archivename" -> {
                val newarchive = sharedPreferences?.getString(key, "books_repository").toString()
                bookViewModel.filename = newarchive
            }

            "storage_area" -> {
                val storageArea = sharedPreferences?.getString(key, "internal").toString()
                bookViewModel.changeLocalization(storageArea)
            }
        }
    }
}