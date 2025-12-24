package com.example.eco2.components

import android.content.ContentValues.TAG
import android.util.Log
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.addTextChangedListener
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import timber.log.Timber

@Composable
fun Searchbar(
    // placesClient: PlacesClient,
    modifier: Modifier = Modifier,
    onPlaceSelected: (String) -> Unit,
) {
    val textColor = if(isSystemInDarkTheme()) Color.White else Color.Black
    AndroidView(
        factory = { context ->
            AutoCompleteTextView(context).apply {

               hint = "Para onde vamos ? :)" // check the component

                // text and hint color
                setTextColor(textColor.toArgb())
                setHintTextColor(textColor.copy(alpha = 0.6f).toArgb())

                // params of Layout
                layoutParams = ViewGroup.LayoutParams( // check de component
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                // initialize places api and autocomplete function

                val placesClient by lazy { Places.createClient(context) }
                val sessionToken = AutocompleteSessionToken.newInstance()

                val autocompleteAdapter = ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line)
                threshold = 1
                // add texto for change a listener and capture and handle use
               addTextChangedListener { editable ->
                    val query = editable?.toString() ?: ""
                    println("DEBUG: Listener ativo. Query: $query") // TESTE 1
                    if (query.length > 2 && Places.isInitialized()) { // Places.isInitialized esta retornando false
                        println("DEBUG: Tentando disparar busca para: $query") // ele não esta passando pelo if
                        // create a request autocomplete find predictions
                        val request = FindAutocompletePredictionsRequest.builder()
                            .setSessionToken(sessionToken)
                            .setQuery(query)
                            .setRegionCode("BR")
                            .build()
                        // search autocomplete places for the api places
                        placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
                            println("DEBUG: API respondeu com ${response.autocompletePredictions.size} itens") // TESTE 2
                            val predictions = response.autocompletePredictions
                            autocompleteAdapter.clear()
                            if (predictions.isEmpty()) {
                                println("DEBUG: API conectou, mas não achou nada para '$query'")
                            } else {
                                predictions.forEach {
                               autocompleteAdapter.add(it.getFullText(null).toString())
                               }
                                autocompleteAdapter.notifyDataSetChanged()
                                showDropDown()
                                println("DEBUG: Comando showDropDown enviado!") // TESTE 3
                            }
                        }
                            .addOnFailureListener { e ->
                                println("DEBUG: Erro na API: ${e.message}")
                            }
                    }
                }
                setAdapter(autocompleteAdapter)
                // set a item click listener to selection users
                setOnItemClickListener { _, _, position, _ ->
                    val selectedPlace = autocompleteAdapter.getItem(position) ?: return@setOnItemClickListener
                    onPlaceSelected(selectedPlace)
                }
            }
        },
        update = {},
        // config paddings
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}