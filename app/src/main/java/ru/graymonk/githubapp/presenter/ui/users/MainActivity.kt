package ru.graymonk.githubapp.presenter.ui.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.graymonk.githubapp.R
import ru.graymonk.githubapp.app
import ru.graymonk.githubapp.databinding.ActivityMainBinding
import ru.graymonk.githubapp.domain.entities.UserEntity
import ru.graymonk.githubapp.domain.repository.UsersRepository
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() , UsersContract.View {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    //private val usersRepository: UsersRepository by lazy { app.userRepository }

    private val presenter: UsersContract.Presenter by lazy { UsersPresenter(app.userRepository) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //showProgress(false)

        initRecyclerView()

        //userListUpdate()
        setOnClickListener()
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    //region menu init
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuExit -> {
                exitProcess(0)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setOnClickListener() {
        binding.mainActivityRefreshButton.setOnClickListener {
            Log.d("@@@","click")
            presenter.onRefresh()
            Toast.makeText(this, "Refresh!", Toast.LENGTH_SHORT).show()
        }
    }
    //endregion



    //region Contract
    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showUsers(users: List<UserEntity>) {
        adapter.setData(users)
    }

    override fun showProgress(inProgress: Boolean) {
        binding.mainActivityUsersRecyclerView.isVisible = !inProgress
        binding.mainActivityUsersProgressBar.isVisible = inProgress
    }
    //endregion

    private fun initRecyclerView() {
        //LayoutManager отвечает за измерение и позиционирование представлений элементов внутри RecyclerView
        binding.mainActivityUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainActivityUsersRecyclerView.adapter = adapter
    }


}