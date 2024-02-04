package es.uniovi.inf_movil.tanteo

class TeamPoints (var de1: Int, var de2: Int, var de3: Int) {

    val total get() = de1 + 2 * de2 + 3 * de3

}