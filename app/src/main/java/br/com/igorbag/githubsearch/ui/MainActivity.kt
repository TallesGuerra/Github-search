package br.com.igorbag.githubsearch.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.data.GitHubService
import br.com.igorbag.githubsearch.domain.Repository

class MainActivity : AppCompatActivity() {

    lateinit var nomeUsuario: EditText
    lateinit var btnConfirmar: Button
    lateinit var listaRepositories: RecyclerView
    lateinit var githubApi: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        showUserName()
        setupRetrofit()
        getAllReposByUserName()
    }

 // Metodo responsavel por realizar o setup da view e recuperar os Ids do layout
    fun setupView() { 
        //@TODO 1 - Recuperar os Id's da tela para a Activity com o findViewById
            nomeUsuario = findViewById(R.id.et_username)
            btnConfirmar = findViewById(R.id.btn_confirmar)
            listaRepositories = findViewById(R.id.rv_lista_repos)

    }

    //metodo responsavel por configurar os listeners click da tela
    private fun setupListeners() {
        //@TODO 2 - colocar a acao de click do botao confirmar
            btnConfirmar.setOnClickListener {
                        saveUserLocal()
                        getAllReposByUserName()
                    }
    }


    // salvar o usuario preenchido no EditText utilizando uma SharedPreferences
    private fun saveUserLocal() {
        //@TODO 3 - Persistir o usuario preenchido na editText com a SharedPref no listener do botao salvar

        val prefs = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
                prefs.edit()
                    .putString("USERNAME", nomeUsuario.text.toString())
                    .apply()

    
    }

    private fun showUserName() {
        //@TODO 4- depois de persistir o usuario exibir sempre as informacoes no EditText  se a sharedpref possuir algum valor, exibir no proprio editText o valor salvo
        val prefs = getSharedPreferences("APP_PREFS", Context.MODE_PRIVATE)
        val savedUser = prefs.getString("USERNAME", "")

        if (!savedUser.isNullOrEmpty()) {
            nomeUsuario.setText(savedUser)
        }

    
    }

    //Metodo responsavel por fazer a configuracao base do Retrofit
    fun setupRetrofit() {
        /*
           @TODO 5 -  realizar a Configuracao base do retrofit
           Documentacao oficial do retrofit - https://square.github.io/retrofit/
           URL_BASE da API do  GitHub= https://api.github.com/
           lembre-se de utilizar o GsonConverterFactory mostrado no curso
        */

           githubApi = RetrofitHelper.getRetrofit().create(GitHubService::class.java)
    }

    //Metodo responsavel por buscar todos os repositorios do usuario fornecido
    fun getAllReposByUserName() {
        // TODO 6 - realizar a implementacao do callback do retrofit e chamar o metodo setupAdapter se retornar os dados com sucesso
        val user = nomeUsuario.text.toString()

                githubApi.getReposByUser(user).enqueue(object : Callback<List<Repository>> {
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
   

    // Metodo responsavel por realizar a configuracao do adapter
    fun setupAdapter(list: List<Repository>) {
        /*
            @TODO 7 - Implementar a configuracao do Adapter , construir o adapter e instancia-lo
            passando a listagem dos repositorios
         */
        val adapter = RepositoryAdapter(list).apply{
            carItemLister = {repo -> openBrowser(repo.htmmlUrl)}

            btnShareLister = { repo -> shareRepositoryLink(repo.htmlUrl)}
        }
            listaRepositories.layoutManager = LinearLayoutManager(this)
            listaRepositories.adapter = adapter
    }


    // Metodo responsavel por compartilhar o link do repositorio selecionado
    // @Todo 11 - Colocar esse metodo no click do share item do adapter
    fun shareRepositoryLink(urlRepository: String) {        
        val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, urlRepository)
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(sendIntent, null))
            }


    // Metodo responsavel por abrir o browser com o link informado do repositorio

    // @Todo 12 - Colocar esse metodo no click item do adapter
    fun openBrowser(urlRepository: String) {
       startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlRepository)))
    }

}