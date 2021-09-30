package Juego

import items.Item
import items.TipoItem

object Factorias {
    fun generarMapa(filas: Int, columnas: Int): Mapa {
        var mapa: ArrayList<Array<Any?>> = ArrayList<Array<Any?>>()
        for (i in 1..filas) {
            mapa.add(Array<Any?>(columnas) { null })
        }
        return Mapa(mapa)
    }

    fun generarCapitolio(numItems: Int, mapa: Mapa): Capitolio {
        var items = ArrayList<Item?>(0)
        for (i in 1..numItems) {
            var it = when ((1..3).random()) {
                1 -> Item("Armas", TipoItem.ARMAS, (50..100).random())
                2 -> Item("Trampa", TipoItem.TRAMPA, (50..100).random())
                3 -> Item("Medicina", TipoItem.MEDICINA, (50..100).random())
                else -> null
            }
            items.add(it)
        }
        return Capitolio(items, mapa)
    }

    fun generarDistrito(nombre: String, numJugadores: Int, mapa: Mapa): Distrito {
        var distrito = Distrito.Builder()
            .nombre(nombre)
            .mapa(mapa)
            .build()
        var jugadores = ArrayList<Jugador?>(0)
        for (i in 1..numJugadores) {
            jugadores.add(Jugador(distrito, (100..200).random(), (5..200).random()))
        }
        distrito.jugadores = jugadores
        distrito.enviarJugadores()
        return distrito
    }
}