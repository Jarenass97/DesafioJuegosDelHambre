package Juego

import items.Item
import items.TipoItem

class Capitolio(private var items: ArrayList<Item?>, private var mapa: Mapa) {

    private var recogidos = ArrayList<Jugador>(0)

    init {
        mapa.capitolio = this
        enviarItems(10)
    }

    fun enviarItems(numItems: Int) {
        if(items.size>=numItems) {
            for (i in 1..numItems) {
                var item: Item? = items[(items.indices).random()]
                mapa.addItem(item)
                this.items.remove(item)
            }
        }
    }

    override fun toString(): String {
        var armas=0;var trampa=0;var medicina=0
        var cad = "lista de Items:\n"
        for (i in items) {
            if (i != null) {
                when (i.tipo) {
                    TipoItem.TRAMPA -> trampa++
                    TipoItem.MEDICINA -> medicina++
                    TipoItem.ARMAS -> armas++
                }
            }
        }
        cad+="$trampa Trampa\n$medicina Medicina\n$armas Armas\n"
        cad += "\nJugadores recogidos:\n"
        for (j in recogidos) {
            cad += "$j\n"
        }
        return cad
    }

    fun recoger(jugador: Jugador) {
        recogidos.add(jugador)
    }


}