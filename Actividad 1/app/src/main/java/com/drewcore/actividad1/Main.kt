package com.drewcore.actividad1

/**
 * ACTIVIDAD 1: Dos ejemplos por cada pilar de programacion funcional
 */

// ========== 1. INMUTABILIDAD ==========

// Ejemplo 1: Carrito de compras
data class Item(val id: String, val qty: Int)
data class Cart(val items: List<Item>, val total: Int)

fun addItem(cart: Cart, item: Item): Cart {
    val newItems = cart.items + item
    val newTotal = cart.total + item.qty
    return cart.copy(items = newItems, total = newTotal)
}

// Ejemplo 2: Perfil de usuario
data class Profile(
    val name: String,
    val tags: List<String>,
    val settings: Map<String, String>
)

fun addTag(profile: Profile, tag: String): Profile {
    val newTags = profile.tags + tag
    return profile.copy(tags = newTags)
}

fun updateSetting(profile: Profile, key: String, value: String): Profile {
    val newSettings = profile.settings + (key to value)
    return profile.copy(settings = newSettings)
}

// ========== 2. FUNCIONES PURAS ==========

// Ejemplo 1: Operaciones matematicas
fun calcularAreaCirculo(radio: Double): Double = Math.PI * radio * radio
fun convertirTemperatura(celsius: Double): Double = celsius * 9/5 + 32

// Ejemplo 2: Validaciones de datos
fun validarEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".") && email.length > 5
}

fun contarCaracteres(texto: String): Int = texto.length

// ========== 3. EXPRESIONES LAMBDA ==========

// Ejemplo 1: Operaciones con listas de numeros
fun procesarNumeros() {
    val numeros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    val pares = numeros.filter { it % 2 == 0 }
    val cuadrados = numeros.map { it * it }
    val suma = numeros.reduce { acc, n -> acc + n }

    println("Numeros pares: $pares")
    println("Cuadrados: $cuadrados")
    println("Suma total: $suma")
}

// Ejemplo 2: Transformacion de strings
fun procesarTextos() {
    val palabras = listOf("kotlin", "java", "swift", "python")

    val mayusculas = palabras.map { it.uppercase() }
    val largas = palabras.filter { it.length > 4 }

    println("En mayusculas: $mayusculas")
    println("Palabras largas: $largas")
}

// ========== 4. FUNCIONES DE ORDEN SUPERIOR ==========

// Ejemplo 1: Creador de saludos personalizados
fun crearSaludo(mensaje: String, destinatario: String, tipoSaludo: (String, String) -> String): String {
    return tipoSaludo(mensaje, destinatario)
}

fun demoSaludos() {
    val saludo1 = crearSaludo("Buenos dias", "Ana") { mensaje, nombre -> "$mensaje, $nombre" }
    val saludo2 = crearSaludo("Hola", "Carlos") { mensaje, nombre -> "$mensaje $nombre!" }

    println(saludo1)
    println(saludo2)
}

// Ejemplo 2: Calculadora con descuentos
fun calcularConDescuento(precio: Double, cantidad: Int, calculadorDescuento: (Double, Int) -> Double): Double {
    val subtotal = precio * cantidad
    val descuento = calculadorDescuento(subtotal, cantidad)
    return subtotal - descuento
}

fun descuentoPorCantidad(subtotal: Double, cantidad: Int): Double {
    return if (cantidad > 10) subtotal * 0.10 else 0.0
}

fun descuentoPorMonto(subtotal: Double, cantidad: Int): Double {
    return if (subtotal > 500) subtotal * 0.15 else 0.0
}

fun demoDescuentos() {
    val total1 = calcularConDescuento(100.0, 3, ::descuentoPorCantidad)
    println("3 items de $100: $$total1")

    val total2 = calcularConDescuento(100.0, 8, ::descuentoPorMonto)
    println("8 items de $100: $$total2")
}

// ========== 5. EVALUACION PEREZOSA ==========

// Ejemplo 1: Calculo lazy con by lazy
fun calcularSumaPequena() {
    val suma: Int by lazy {
        println("Calculando suma...")
        (1..100).sum()
    }

    println("Inicio")
    println(suma)
    println(suma)
}

// Ejemplo 2: Secuencia lazy para procesamiento eficiente
fun procesarNumerosLazy() {
    val numerosProcesados = (1..10).asSequence()
        .map { it * 2 }
        .filter { it > 10 }
        .take(3)

    println("Numeros procesados: ${numerosProcesados.toList()}")
}

// ========== 6. COMPOSICION DE FUNCIONES ==========

// Ejemplo 1: Funciones de extension para numeros
fun Int.esPar(): Boolean = this % 2 == 0
fun Int.alCuadrado(): Int = this * this

fun procesarNumero(numero: Int): Boolean {
    return numero.alCuadrado().esPar()
}

// Ejemplo 2: Composicion de transformaciones de texto
val transformarTexto = { texto: String ->
    texto.trim().uppercase()
}

fun aplicarTransformaciones(input: String): String {
    return transformarTexto(input)
}

// ========== DEMOSTRACION DE LOS EJEMPLOS ==========
fun main() {
    println("=== PILARES DE PROGRAMACION FUNCIONAL EN KOTLIN ===\n")

    // 1. INMUTABILIDAD
    println("1. INMUTABILIDAD")
    println("-".repeat(40))

    val cart0 = Cart(items = emptyList(), total = 0)
    val cart1 = addItem(cart0, Item("A", 2))
    val cart2 = addItem(cart1, Item("B", 1))
    println("Cart inicial: $cart0")
    println("Cart con A: $cart1")
    println("Cart con A y B: $cart2")

    val p0 = Profile("Juan", listOf("android"), mapOf("theme" to "dark"))
    val p1 = addTag(p0, "kotlin")
    val p2 = updateSetting(p1, "notifications", "enabled")
    println("Perfil inicial: $p0")
    println("Con tag kotlin: $p1")
    println("Con notificaciones: $p2")
    println()

    // 2. FUNCIONES PURAS
    println("2. FUNCIONES PURAS")
    println("-".repeat(40))
    val radio = 5.0
    println("Area del circulo (radio $radio): ${calcularAreaCirculo(radio)}")
    println("25C en Fahrenheit: ${convertirTemperatura(25.0)}")
    println("Email valido 'test@mail.com': ${validarEmail("test@mail.com")}")
    println("Caracteres en 'Kotlin': ${contarCaracteres("Kotlin")}")
    println()

    // 3. EXPRESIONES LAMBDA
    println("3. EXPRESIONES LAMBDA")
    println("-".repeat(40))
    procesarNumeros()
    println()
    procesarTextos()
    println()

    // 4. FUNCIONES DE ORDEN SUPERIOR
    println("4. FUNCIONES DE ORDEN SUPERIOR")
    println("-".repeat(40))
    demoSaludos()
    println()
    demoDescuentos()
    println()

    // 5. EVALUACION PEREZOSA
    println("5. EVALUACION PEREZOSA")
    println("-".repeat(40))
    calcularSumaPequena()
    println()
    procesarNumerosLazy()
    println()

    // 6. COMPOSICION DE FUNCIONES
    println("6. COMPOSICION DE FUNCIONES")
    println("-".repeat(40))
    val numero = 5
    println("Numero $numero al cuadrado es par: ${procesarNumero(numero)}")
    val texto = "  hola mundo  "
    println("Texto '$texto' transformado: '${aplicarTransformaciones(texto)}'")
}