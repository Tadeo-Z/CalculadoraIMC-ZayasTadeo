package zayas.tadeo.asignacion4_calculadoraimc_zayas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    // Variables
    val heightEditText: EditText = findViewById(R.id.editTextNumberDecimal)
    val weightEditText: EditText = findViewById(R.id.editTextNumberDecimal2)
    val calculate: Button = findViewById(R.id.btnCalcular)
    val result: TextView = findViewById(R.id.textView5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Eventos button click o ClickListener
        calculate.setOnClickListener {
            val height = heightEditText.text.toString().toDoubleOrNull()
            val weight = weightEditText.text.toString().toDoubleOrNull()

            if(height != null && weight != null) {
                val imc = weight / (height * height)
                val (category, color) = when {
                    imc < 18.5 -> "Bajo peso" to R.color.colorOrange
                    imc < 24.9 -> "Saludable" to R.color.colorGreen
                    imc < 29.9 -> "Sobrepeso" to R.color.colorYellow
                    imc < 34.9 -> "Obesidad grado 1" to R.color.colorOrange
                    imc < 39.9 -> "Obesidad grado 2" to R.color.colorRed
                    else -> "Obesidad grado 3" to R.color.colorBrown
                }
                result.text = "IMC: %.2f - %s".format(imc, category)
                result.setBackgroundColor(color)
            } else {
                result.text = "Por favor, ingresa valores validos"
            }
        }
    }
}