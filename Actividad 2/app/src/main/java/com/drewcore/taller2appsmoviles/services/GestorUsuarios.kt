package com.drewcore.taller2appsmoviles.services

import com.drewcore.taller2appsmoviles.models.Configuracion
import com.drewcore.taller2appsmoviles.models.Perfil
import com.drewcore.taller2appsmoviles.models.Usuario

class GestorUsuarios {
    private val usuarios = mutableListOf<Usuario>()
    private val configuraciones = mutableMapOf<Int, Configuracion>()
    private val perfiles = mutableMapOf<Int, Perfil>()

    // 1. APPLY - configurar objeto después de creación
    fun crearUsuario(id: Int, nombre: String, email: String, edad: Int): Usuario {
        return Usuario(id, nombre, email, edad).apply {
            usuarios.add(this)
            println("Usuario creado: $nombre")
        }
    }

    // 2. LET - transformar objeto
    fun configurarUsuario(usuario: Usuario): Configuracion {
        return usuario.let { usr ->
            val config = if (usr.edad < 25) {
                Configuracion("oscuro", true)
            } else {
                Configuracion("claro", false)
            }
            configuraciones[usr.id] = config
            println("Configurado tema: ${config.tema}")
            config
        }
    }

    // 3. ALSO - acciones adicionales sin cambiar objeto
    fun crearPerfil(usuario: Usuario): Perfil {
        return Perfil("Usuario nuevo", "2024-01-15", 1).also { perfil ->
            perfiles[usuario.id] = perfil
            enviarEmail(usuario.email)
            println("Perfil creado para: ${usuario.nombre}")
        }
    }

    // 4. WITH - operar sin repetir referencia
    fun generarReporte(usuarioId: Int): String {
        val usuario = usuarios.find { it.id == usuarioId }
        return with(usuario) {
            if (this == null) {
                "Usuario no encontrado"
            } else {
                val config = configuraciones[id]
                val perfil = perfiles[id]
                """
                Usuario: $nombre
                Email: $email  
                Edad: $edad
                Tema: ${config?.tema ?: "Sin configurar"}
                Accesos: ${perfil?.numeroAccesos ?: 0}
                """.trimIndent()
            }
        }
    }

    // 5. RUN - ejecutar bloque y devolver resultado
    fun procesarActividad(usuarioId: Int): String {
        return run {
            val usuario = usuarios.find { it.id == usuarioId }
            when {
                usuario == null -> "Usuario no existe"
                !usuario.activo -> "Usuario inactivo"
                else -> {
                    val perfil = perfiles[usuarioId]
                    if (perfil != null) {
                        val nuevoPerfil = perfil.copy(numeroAccesos = perfil.numeroAccesos + 1)
                        perfiles[usuarioId] = nuevoPerfil
                    }
                    "Actividad procesada para ${usuario.nombre}"
                }
            }
        }
    }

    private fun enviarEmail(email: String) {
        println("Email enviado a: $email")
    }

    fun mostrarEstadisticas(): String {
        return "Total usuarios: ${usuarios.size}, Configurados: ${configuraciones.size}"
    }
}