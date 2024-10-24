package dam.moviles.proyecto2_bil

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dam.moviles.proyecto2_bil.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // este objeto contiene todos los controles del archivo activity_main.xml
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // esta línea crea el objeto "contorles" con los objetos que hay
        // en el archivo activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
        // la interfaz de MainActivity es el FrameLayout que es el padre
        // de activity_main.xml
        setContentView(binding.root)

        inicializarViewModel()

        this.inicializarBotones()
    }

    override fun onStart(){
        super.onStart()
        if(viewModel.estado == Estado.INICIADO){
            binding.spiCurso.setSelection(viewModel.spinnerPosicion)
            binding.txtAsignaturas.text = viewModel.textoAsignaturas
            binding.txtObservaciones.setText(viewModel.textoInput)
        }

        viewModel.estado = Estado.INICIADO
    }

    override fun onStop() {
        super.onStop()
        viewModel.spinnerPosicion = binding.spiCurso.selectedItemPosition
        viewModel.textoAsignaturas = binding.txtAsignaturas.text.toString()
        viewModel.textoInput = binding.txtObservaciones.text.toString()
    }

    fun inicializarViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    fun inicializarBotones(){
        binding.btnSeleccionarCurso.setOnClickListener{
            val curso:String = binding.spiCurso.selectedItem.toString()
            val asignaturas:List<String> = this.getAsignaturas(curso)
            binding.txtAsignaturas.text = getListaAsignaturasBonica(asignaturas)
        }

        binding.btnEnviar.setOnClickListener{
            val texto:String = binding.txtObservaciones.text.toString()
            Toast.makeText(
                this,
                getString(R.string.mensajeToast, texto),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun getListaAsignaturasBonica(lista:List<String>):String =
        lista
            .map { dato -> "- $dato \n" }
            .joinToString("")

    /*fun getListaAsignaturasBonica(lista:List<String>):String{
        var texto:String = ""
        for(asignatura in lista) {
            texto += "- ${asignatura}\n"
        }

        return texto
    }*/

    fun getAsignaturas(curso:String):List<String> =
        when(curso){
            "1º DAM" -> listOf(getString(R.string.programacion),
                getString(R.string.entornos),
                getString(R.string.marcas),
                getString(R.string.basesDeDatos),
                getString(R.string.sistemas))
            "2º DAM" -> listOf(getString(R.string.moviles),
                getString(R.string.interfaces),
                getString(R.string.accesoADatos),
                getString(R.string.ServiciosYProcesos),
                getString(R.string.SGE))
            else -> throw Exception("curso no admitido")
        }

}