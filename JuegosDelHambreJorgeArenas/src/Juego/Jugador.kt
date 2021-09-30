package Juego

class Jugador(var distrito: Distrito, var vida: Int, var fuerza: Int) {

    override fun toString(): String =
        "Jugador (Distrito: ${distrito.nombre}, Vida: ${this.vida}, Fuerza: ${this.fuerza})"


    fun addFuerza(fuerza: Int) {
        this.fuerza += fuerza
    }

    fun addVida(vida: Int) {
        this.vida += vida
    }

    fun morir() {
        this.vida = 0
    }

    //devuelve 1 si gana el jugador 1 y 2 si gana el jugador 2
    fun pelear(jugador2: Jugador): Int {
        return if (this.fuerza > jugador2.fuerza) {
            1
        } else if (this.fuerza < jugador2.fuerza) {
            2
        } else {
            if (this.vida > jugador2.vida) {
                1
            } else if (this.vida < jugador2.vida) {
                2
            } else {
                (1..2).random()
            }
        }
    }

}