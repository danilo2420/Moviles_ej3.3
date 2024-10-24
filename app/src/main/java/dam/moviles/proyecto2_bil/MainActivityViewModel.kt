package dam.moviles.proyecto2_bil

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var estado:Estado = Estado.NO_INICIADO
    var spinnerPosicion:Int = 0
    var textoAsignaturas:String = ""
    var textoInput:String = ""

}