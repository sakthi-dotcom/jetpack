package com.example.jetpack.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigation
import com.example.jetpack.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginPage(navController: NavController  ){
    var username by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    val (focusUsername,focusPassword) = remember { FocusRequester.createRefs()}
    val keyboardController = LocalSoftwareKeyboardController.current
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.30f),
                Alignment.TopEnd,
            ){
               Image(painter = painterResource(id = R.drawable.background), contentDescription = "",
                   modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp, vertical = 50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Image(painter = painterResource(id = R.drawable.title), contentDescription = "",
                        modifier = Modifier
                            .weight(1f)
                            .size(300.dp),
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                    Text(text = "Welcome", fontSize = 25.sp, color = Color.White, style = MaterialTheme.typography.h1)
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 40.dp)) {
                Text(text = "Log In", style = MaterialTheme.typography.h1)
                OutlinedTextField(value = username , onValueChange ={username = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusUsername),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {focusPassword.requestFocus()}),
                    singleLine = true,
                    label = { Text(text = "Username") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusPassword),
                    value = password,
                    onValueChange ={password = it},
                    label = { Text(text = "Password") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {keyboardController?.hide()}),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None  else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = {isPasswordVisible = !isPasswordVisible}) {
                            Icon(imageVector = if (isPasswordVisible) Icons.Default.LockOpen else Icons.Default.Lock,
                                contentDescription ="Password Toggle" )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                    }
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Forgot Password ?", fontSize = 12.sp)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Log in")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    Arrangement.Center, verticalAlignment = Alignment.CenterVertically ) {
                    Text(text = "Don't have an account?", fontSize = 14.sp)
                    TextButton(onClick = {
                        navController.navigate("register_page"){
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }) {
                        Text(text = "Register")

                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}