import Juego.Distrito
import Juego.Factorias


fun main() {
    var map = Factorias.generarMapa(10, 10)
    var cap = Factorias.generarCapitolio(100, map)
    var contDistrito = 1
    var distritos = Array<Distrito>(5) {
        Factorias.generarDistrito("D${contDistrito++}", 2, map)
    }
    var tiempo = 1
    while (!map.isFin()) {
        if (tiempo % 2 == 0) {
            map.moverJugadores()
        }
        if (tiempo % 5 == 0) {
            cap.enviarItems(4)
            println(map)
        }
        //Thread.sleep(1000)
        tiempo++
    }
    var ganador = map.ganador()
    println("**********************************************RESULTADO FINAL****************************************")
    println("El ganador es:\n${ganador ?: "NADIE, HAN MUERTO TODOS"}\n")
    println(map)
    println(cap)


}

