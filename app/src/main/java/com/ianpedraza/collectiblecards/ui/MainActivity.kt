package com.ianpedraza.collectiblecards.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ianpedraza.collectiblecards.BuildConfig
import com.ianpedraza.collectiblecards.R
import com.ianpedraza.collectiblecards.databinding.ActivityMainBinding
import com.ianpedraza.collectiblecards.databinding.DrawerHeaderBinding
import com.ianpedraza.collectiblecards.ui.common.BaseLifecycleObserverActivity
import com.ianpedraza.collectiblecards.utils.Constants
import com.ianpedraza.collectiblecards.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseLifecycleObserverActivity(TAG) {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navHostFragment.navController
    }

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val fragments = setOf(
        R.id.listFragment,
        R.id.gridListFragment,
        R.id.favoritesFragment,
    )

    private val drawerGravity = GravityCompat.START

    private lateinit var headerBinding: DrawerHeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (BuildConfig.FLAVOR == Constants.FLAVOR_SUPERHEROES) {
            setTheme(R.style.Theme_CollectibleCards_Superheroes)
        } else {
            setTheme(R.style.Theme_CollectibleCards_Animals)
        }

        val headerView = binding.navigationView.getHeaderView(0)
        headerBinding = DrawerHeaderBinding.bind(headerView)

        setupUI()
    }

    private fun setupUI() {
        setupActionBar()
        setupNavigationDrawer()
        setupBottomNavigationBar()
    }

    private fun setupActionBar() {
        appBarConfiguration = AppBarConfiguration(fragments, binding.drawerLayout)
        NavigationUI.setupWithNavController(
            binding.topAppBarDetail,
            navController,
            appBarConfiguration,
        )
        setSupportActionBar(binding.topAppBarDetail)
    }

    private fun setupBottomNavigationBar() {
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
    }

    private fun setupNavigationDrawer() {
        NavigationUI.setupWithNavController(binding.navigationView, navController)
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
        onBackPressedDispatcher.addCallback(backPressedCallBack)

        headerBinding.root.setOnClickListener { goToProfile() }
        headerBinding.textViewDrawerHeaderName.text = getString(R.string.fake_username)
        headerBinding.textViewDrawerHeaderId.text = getString(R.string.fake_user_id)
    }

    private val backPressedCallBack = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (binding.drawerLayout.isDrawerOpen(drawerGravity)) {
                closeDrawer()
            } else {
                finish()
            }
        }
    }

    private val onDestinationChangedListener: NavController.OnDestinationChangedListener by lazy {
        NavController.OnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.profileFragment -> {
                    hideBottomNavView(true)
                    clearSelection()
                }
                else -> hideBottomNavView(false)
            }
        }
    }

    private fun goToProfile() {
        closeDrawer()
        clearSelection()
        navController.navigate(R.id.profileFragment)
    }

    private fun clearSelection() {
        binding.navigationView.checkedItem?.isChecked = false
    }

    private fun hideBottomNavView(hide: Boolean) {
        binding.bottomNavigationView.visibility = if (hide) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun closeDrawer() = binding.drawerLayout.closeDrawer(drawerGravity)

    override fun onSupportNavigateUp(): Boolean =
        NavigationUI.navigateUp(navController, appBarConfiguration)

    companion object {
        private const val TAG = "MainActivity"
    }
}
