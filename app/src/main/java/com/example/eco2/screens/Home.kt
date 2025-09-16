package com.example.eco2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.example.eco2.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home() {
    var isToggledfav  by rememberSaveable { mutableStateOf(false) } // variavel is toggled armazenara o estado do botão inicialmente falso toggled significa alternado
    // testar se isToggled é uma variavel so de favoritos ou se pode ser usada em todos os icones
    var isTogglednews  by rememberSaveable { mutableStateOf(false) }

    var isToggledmap by rememberSaveable { mutableStateOf(false) }

    var isToggledsave  by rememberSaveable { mutableStateOf(false) }

    var isToggledperfil  by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        bottomBar = { BottomAppBar(
            modifier = Modifier.height(67.dp),
            containerColor = MaterialTheme.colorScheme.background
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton (// define um icone clicavel
                    onClick = {isToggledfav = !isToggledfav}, // define true ou false do button função lambda
                ) {
                    Icon( // define que icone sera mostrado na tela
                        painter = if (isToggledfav) painterResource(R.drawable.favoritos_prenchido) else painterResource(R.drawable.favoritos),
                        contentDescription = if(isToggledfav) "Select icon button" else "Unselected icon button.",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton (// define um icone clicavel
                    onClick = {isTogglednews = !isTogglednews} // define true ou false do button função lambda
                ) {
                    Icon( // define que icone sera mostrado na tela
                        painter = if (isTogglednews) painterResource(R.drawable.noticias_prechido) else painterResource(R.drawable.noticias),
                        contentDescription = if(isTogglednews) "Select icon button" else "Unselected icon button.",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton (// define um icone clicavel
                    onClick = {isToggledmap = !isToggledmap}, // define true ou false do button função lambda
                ) {
                    Icon( // define que icone sera mostrado na tela
                        painter = if (isToggledmap) painterResource(R.drawable.map_prenchido) else painterResource(R.drawable.map),
                        contentDescription = if(isToggledmap) "Select icon button" else "Unselected icon button.",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(32.dp),
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton (// define um icone clicavel
                    onClick = {isToggledsave = !isToggledsave} // define true ou false do button função lambda
                ) {
                    Icon( // define que icone sera mostrado na tela
                        painter = if (isToggledsave) painterResource(R.drawable.salvos_prenchido) else painterResource(R.drawable.salvos),
                        contentDescription = if(isToggledsave) "Select icon button" else "Unselected icon button.",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton (// define um icone clicavel
                    onClick = {isToggledperfil = !isToggledperfil} // define true ou false do button função lambda
                ) {
                    Icon( // define que icone sera mostrado na tela
                        painter = if (isToggledperfil) painterResource(R.drawable.perfil_prenchido) else painterResource(R.drawable.perfil),
                        contentDescription = if(isToggledperfil) "Select icon button" else "Unselected icon button.",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }


           }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
        }
    }
}