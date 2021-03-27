package com.delta22.rentateamtest.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delta22.rentateamtest.MainActivity
import com.delta22.rentateamtest.R
import com.delta22.rentateamtest.adapter.UsersAdapter
import com.delta22.rentateamtest.data.Repository
import com.delta22.rentateamtest.data.json.User
import com.delta22.rentateamtest.di.Injector
import com.delta22.rentateamtest.viewmodel.UsersModelFactory
import com.delta22.rentateamtest.viewmodel.UsersViewModel
import javax.inject.Inject

class UsersFragment : Fragment() {

    private lateinit var usersViewModel: UsersViewModel
    private lateinit var adapter: UsersAdapter

    private lateinit var progress: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var textError: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val usersModelFactory = Injector.appComponent.getUsersModelFactory()
        usersViewModel = ViewModelProvider(this, usersModelFactory)
            .get(UsersViewModel::class.java)
        usersViewModel.loadUsers()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = UsersAdapter((requireActivity() as MainActivity).navController)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_users, container, false)
        initViews(view)
        usersViewModel.state.observe(viewLifecycleOwner, { state ->
            renderState(state)
        })
        return view
    }

    private fun renderState(state: LoadingState) {
        when (state) {
            is LoadingState.Loading -> showLoading()
            is LoadingState.Error -> showError(state.error)
            is LoadingState.Success -> showSuccess(state.users)
        }
    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE
        textError.visibility = View.GONE
        recyclerView.visibility = View.GONE
    }

    private fun showError(error: Throwable) {
        progress.visibility = View.GONE
        textError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        val errorMessage = requireContext().getString(R.string.error_loading_users_message)
        textError.text = errorMessage
    }

    private fun showSuccess(users: List<User>) {
        progress.visibility = View.GONE
        textError.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE

        adapter.updatePlaylists(users)
        recyclerView.adapter = adapter
    }

    private fun initViews(view: View) {
        progress = view.findViewById(R.id.progress_users)
        recyclerView = view.findViewById(R.id.recycler_users)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        textError = view.findViewById(R.id.text_error_users)
    }

    sealed class LoadingState {
        object Loading : LoadingState()
        class Error(val error: Throwable) : LoadingState()
        class Success(val users: List<User>) : LoadingState()
    }
}
