package com.drewcore.taller2appsmoviles

import com.drewcore.taller2appsmoviles.services.GestorUsuarios

fun main() {
    val gestor = GestorUsuarios()

    // APPLY - crear usuarios
    val user1 = gestor.crearUsuario(1, "Ana", "ana@email.com", 22)
    val user2 = gestor.crearUsuario(2, "Luis", "luis@email.com", 30)

    // LET - configurar
    gestor.configurarUsuario(user1)
    gestor.configurarUsuario(user2)

    // ALSO - crear perfiles
    gestor.crearPerfil(user1)
    gestor.crearPerfil(user2)

    // WITH - generar reportes
    println("\n--- REPORTES ---")
    println(gestor.generarReporte(1))

    // RUN - procesar actividad
    println("\n--- ACTIVIDADES ---")
    println(gestor.procesarActividad(1))

    println("\n--- ESTADISTICAS ---")
    println(gestor.mostrarEstadisticas())
}