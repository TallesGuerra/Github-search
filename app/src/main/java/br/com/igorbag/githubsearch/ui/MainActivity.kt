package br.com.igorbag.githubsearch.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.data.GitHubService
import br.com.igorbag.githubsearch.data.RetrofitHelper
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.adapter.RepositoryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var nomeUsuario: EditText
    lateinit var btnConfirmar: Button
    lateinit var listaRepositories: RecyclerView
    lateinit var githubApi: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        setupListeners()
        showUserName()
        setupRetrofit()
        getAllReposByUserName()
    }

    // Metodo responsavel por realizar o setup da view e recuperar os Ids do layout
    fun setupView() {
        nomeUsuario = findViewById(R.id.et_nome_usuario)
        btnConfirmar = findViewById(R.id.btn_confirmar)
        listaRepositories = findViewById(R.id.rv_lista_repositories)
    }

    //metodo responsavel por configurar os listeners click da tela
    private fun setupListeners() {
        btnConfirmar.setOnClickListener {
            saveUserLocal()
            getAllReposByUserName()
        }
    }

    // salvar o usuario preenchido no EditText utilizando uma SharedPreferences
    private fun saveUserLocal() {
        val prefs = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        prefs.edit()
            .putString("USERNAME", nomeUsuario.text.toString())
            .apply()
    }

    private fun showUserName() {
        val prefs = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        val savedUser = prefs.getString("USERNAME", "")

        if (!savedUser.isNullOrEmpty()) {
            nomeUsuario.setText(savedUser)
        }
    }

    //Metodo responsavel por fazer a configuracao base do Retrofit
    fun setupRetrofit() {
        githubApi = RetrofitHelper.getRetrofit().create(GitHubService::class.java)
    }

    //Metodo responsavel por buscar todos os repositorios do usuario fornecido
    fun getAllReposByUserName() {
        val user = nomeUsuario.text.toString()

        githubApi.getAllRepositoriesByUser(user).enqueue(object : Callback<List<Repository>> {
            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if (response.isSuccessful) {
                    val lista = response.body() ?: emptyList()
                    setupAdapter(lista)
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

          fun setupAdapter(list: List<Repository>) {
        val adapter = RepositoryAdapter(list).apply {
            carItemLister = { repo -> openBrowser(repo.htmlUrl) }
            btnShareLister = { repo -> shareRepositoryLink(repo.htmlUrl) }
        }
        listaRepositories.layoutManager = LinearLayoutManager(this)
        listaRepositories.adapter = adapter
    }

    // Metodo responsavel por compartilhar o link do repositorio selecionado
    fun shareRepositoryLink(urlRepository: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, null))
    }

    // Metodo responsavel por abrir o browser com o link informado do repositorio
    fun openBrowser(urlRepository: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlRepository)))
    }
}