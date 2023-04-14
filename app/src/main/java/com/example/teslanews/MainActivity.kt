package com.example.teslanews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.teslanews.databinding.ActivityMainBinding
import com.example.teslanews.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    private val sharedViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve NavController from the NavHostFragment
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        setSupportActionBar(binding.appBarLayout.toolBar)
        setupActionBarWithNavController(navController)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)

        //Hides up arrow on specified screens
        navController.addOnDestinationChangedListener{controller, destination, arguments ->
            when(destination.id) {
                R.id.baseTabFragment -> {supportActionBar?.setDisplayHomeAsUpEnabled(false)
                supportActionBar?.setDisplayShowTitleEnabled(false)
                }

                else -> {
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
                    supportActionBar?.setDisplayShowTitleEnabled(false)
                }

            }

        }


        //Hides the app bar on specified screens
        navController.addOnDestinationChangedListener{controller, destination, arguments ->

            when(destination.id) {
                R.id.splashFragment -> supportActionBar?.hide()

                else -> supportActionBar?.show()
            }
        }




    }


    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || findNavController(R.id.host_fragment).navigateUp()
    }


    override fun onBackPressed() {

        when(navController.currentDestination!!.id) {
            R.id.baseTabFragment -> {
                sharedViewModel.backButtonPressed++

                when(sharedViewModel.backButtonPressed) {
                    1 -> {
                        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        finish()
                    }

                }

            }
            else -> super.onBackPressed()
        }
    }
    
}