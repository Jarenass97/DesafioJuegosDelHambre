package items

class Item(var descripcion: String, var tipo: TipoItem, var nivel: Int) {

    override fun toString(): String = "Item: (Descripcion: ${this.descripcion}, nivel: ${this.nivel}"
}