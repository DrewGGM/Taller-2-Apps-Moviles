package com.drewcore.taller2appsmoviles.models

data class Usuario(
    val id: Int,
    val nombre: String,
    val email: String,
    val edad: Int,
    val activo: Boolean = true
)