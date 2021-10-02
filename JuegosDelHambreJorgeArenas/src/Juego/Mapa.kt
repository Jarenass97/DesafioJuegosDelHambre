package Juego

import items.Item
import items.TipoItem

class Mapa(private var mapa: Array<Array<Any?>>) {

    var capitolio: Capitolio? = null
    private var jugadores = ArrayList<Jugador?>(0)

    override fun toString(): String {
        var cad = ""
        for (fila in mapa) {
            cad += "|"
            for (col in fila) {
                cad += if (col is Item) {
                    " ${col.descripcion} |"
                } else if (col is Jugador) {
                    " ${col.distrito.nombre} |"
                } else {
                    " - |"
                }
            }
            cad += "\n"
        }
        return cad
    }

    fun addItem(item: Item?) {
        var fila = 0
        var columna = 0
        do {
            fila = (mapa.indices).random()
            columna = (mapa[0].indices).random()
        } while (mapa[fila][columna] != null)
        mapa[fila][columna] = item
    }

    fun addJugador(jugador: Jugador?) {
        var fila = 0
        var columna = 0
        do {
            fila = (mapa.indices).random()
            columna = (mapa[0].indices).random()
        } while (mapa[fila][columna] != null)
        mapa[fila][columna] = jugador
        jugadores.add(jugador)
    }

    fun moverJugadores() {
        var movidos = ArrayList<Jugador>(0)
        //Recorrer filas y columnas obteniendo el índice
        for (i in mapa.indices)
            for (j in mapa[0].indices) {
                var any = mapa[i][j]
                if (any is Jugador) {
                    if (!movidos.contains(any)) {
                        var fila = 0
                        var columna = 0
                        //Repetir hasta que no se salga de los límites del mapa
                        do {
                            //movimiento aleatorio en casillas adyacentes, Repetir hasta que la fila o la columna sean distintos de 0
                            do {
                                fila = (-1..1).random()
                                columna = (-1..1).random()
                            } while (fila == 0 && columna == 0)
                            //Compruebo que no se salga de los límites
                        } while (i + fila < 0 || i + fila >= mapa[0].size || j + columna < 0 || j + columna >= mapa.size)
                        var elemento = mapa[i + fila][j + columna]
                        if (elemento is Item) {
                            aprovechaItem(elemento, any)
                            if (elemento.tipo != TipoItem.TRAMPA) {
                                mapa[i + fila][j + columna] = any
                            } else {
                                eliminar(any)
                            }
                            mapa[i][j] = null
                        } else if (elemento is Jugador) {
                            var ganador = any.pelear(elemento)
                            //Si gana el jugador 1, se queda en la posicion del jugador 2
                            if (ganador == 1) {
                                mapa[i + fila][j + columna] = any
                                eliminar(elemento)
                            } else {
                                eliminar(any)
                            }
                            mapa[i][j] = null
                        } else {
                            mapa[i + fila][j + columna] = any
                            mapa[i][j] = null
                        }
                        movidos.add(any)
                    }
                }
            }
    }

    private fun eliminar(jugador: Jugador) {
        jugador.morir()
        jugadores.remove(jugador)
        capitolio?.recoger(jugador)
    }


    private fun aprovechaItem(item: Item, jugador: Jugador) {
        when (item.tipo) {
            TipoItem.ARMAS -> jugador.addFuerza(item.nivel)
            TipoItem.MEDICINA -> jugador.addVida(item.nivel)
        }
    }

    fun isFin(): Boolean = jugadores.size <= 1

    fun ganador(): Jugador? = if (jugadores.size == 1) jugadores[0]
    else null


}