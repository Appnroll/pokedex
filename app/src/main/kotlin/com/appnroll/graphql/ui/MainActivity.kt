package com.appnroll.graphql.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appnroll.graphql.R
import com.appnroll.graphql.data.PokedexBackend
import com.appnroll.graphql.model.Pokemon
import com.appnroll.graphql.model.PokemonData
import com.appnroll.graphql.ui.recyclerview.EvolutionAdapter
import com.appnroll.graphql.utils.CustomTextWatcher
import com.appnroll.graphql.utils.hideKeyboard
import io.github.wax911.library.model.body.GraphContainer
import io.github.wax911.library.model.request.QueryContainerBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity: AppCompatActivity() {

    private val pokedexBackend: PokedexBackend by inject()

    private val adapter = EvolutionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameInput.addTextChangedListener(object: CustomTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                refreshButtonGo()
            }
        })

        buttonGo.setOnClickListener {
            fetchPokemonData(nameInput.text.toString())
        }

        refreshButtonGo()

        initTasksRecyclerView()
        refreshProgress(false)
        renderPokemon(null)
    }

    private fun refreshButtonGo() {
        buttonGo.isEnabled = !nameInput.text.isBlank()
    }

    private fun initTasksRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
        recyclerView.adapter = adapter
    }

    private fun fetchPokemonData(pokemonName: String) {
        nameInput.hideKeyboard()
        refreshProgress(true)
        val query = QueryContainerBuilder().putVariable("name", pokemonName)

        pokedexBackend.getPokemon(query).enqueue(object: Callback<GraphContainer<PokemonData>> {

            override fun onFailure(call: Call<GraphContainer<PokemonData>>, t: Throwable) {
                refreshProgress(false)
                Toast.makeText(this@MainActivity, "Error occurred: ${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<GraphContainer<PokemonData>>, response: Response<GraphContainer<PokemonData>>) {
                refreshProgress(false)
                val pokemon = response.body()?.data?.pokemon
                renderPokemon(pokemon)
                if (pokemon == null) {
                    Toast.makeText(this@MainActivity, "Pokemon not found", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun refreshProgress(isProgress: Boolean) {
        progressBar.visibility = if (isProgress) View.VISIBLE else View.GONE
        buttonGo.visibility = if (isProgress) View.INVISIBLE else View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun renderPokemon(pokemon: Pokemon?) {
        if (pokemon != null) {
            nameText.text = "Name: ${pokemon.name}"
            numberText.text = "Number: ${pokemon.number}"
        } else {
            nameText.text = ""
            numberText.text = ""
        }

        val evolutions = pokemon?.evolutions ?: emptyList()
        evolutionsText.visibility = if (evolutions.isEmpty()) View.INVISIBLE else View.VISIBLE

        adapter.updateList(evolutions)
    }
}
