package Juego


class Distrito(var nombre: String?, var jugadores: ArrayList<Jugador?>, var mapa: Mapa?) {

    fun enviarJugadores() {
        for (i in jugadores.indices) {
            var jug: Jugador? = jugadores[(jugadores.indices).random()]
            mapa?.addJugador(jug)
            this.jugadores.remove(jug)
        }
    }

    override fun toString(): String = "Distrito ${this.nombre}, ${jugadores.size} jugadores"

    class Builder(
        var nombre: String? = null,
        var jugadores: ArrayList<Jugador?> = ArrayList<Jugador?>(0),
        var mapa: Mapa? = null
    ) {
        fun nombre(nombre: String): Builder {
            this.nombre = nombre
            return this
        }

        fun jugadores(jugadores: ArrayList<Jugador?>): Builder {
            this.jugadores = jugadores
            return this
        }

        fun mapa(mapa: Mapa): Builder {
            this.mapa = mapa
            return this
        }

        fun build(): Distrito {
            return Distrito(this.nombre, this.jugadores, this.mapa)
        }
    }
}