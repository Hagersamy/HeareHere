package com.example.hearehere

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.hearehere.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//   private lateinit var recyclerView: RecyclerView
 //   private lateinit var adapter: HomeAdapter

 // private var _binding: ActivityMainBinding? = null
 //   private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // val navHostFragment = supportFragmentManager.findFragmentById(R.id.bottomNavView) as NavHostFragment
      //  val navController = navHostFragment.navController
        updateNavBarVisibility()
/*
        recyclerView = findViewById(R.id.recyclerView)

        // Set the LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize the adapter
        adapter = YourAdapter()  // Replace with your adapter initialization

        // Set the adapter
        recyclerView.adapter = adapter
 */
    }

    private fun handleNavigationVisibility(destination: NavDestination) {
        val navDist= supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView2
        ) as NavHostFragment // Replace with IDs of fragments where the nav bar should be visible
        val current: NavDestination? = navDist.findNavController().currentDestination

            when (current?.id) {

                R.id.login, R.id.signUp -> {
                    binding.navBar.navBar.visibility = View.GONE
                }

                else -> {
                    binding.navBar.navBar.visibility = View.VISIBLE
                }
            }
        }
    private fun updateNavBarVisibility() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.fragmentContainerView2
        ) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            handleNavigationVisibility(destination)
        }
    }

}