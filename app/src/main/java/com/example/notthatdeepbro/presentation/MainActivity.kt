/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var barometerSensor: Sensor? = null
    private lateinit var pressureTextView: TextView

    // Rest of your code...

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize SensorManager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Get the barometer sensor
        barometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)

        // Reference to the TextView in your layout
        pressureTextView = findViewById(R.id.pressureTextView)

        if (barometerSensor != null) {
            // Barometer sensor is available, register a listener
            sensorManager.registerListener(this, barometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            // Barometer sensor is not available on this device
        }

        // Rest of your code...
    }

    override fun onSensorChanged(event: SensorEvent?) {
        // Handle sensor data changes here
        if (event?.sensor == barometerSensor) {
            val pressureValue = event.values[0]
            // Update the TextView with the pressure value
            pressureTextView.text = "Pressure: $pressureValue hPa"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Handle accuracy changes if needed
    }

    fun onPause() {
        super.onPause()
        // Unregister the sensor listener when the activity is paused
        sensorManager.unregisterListener(this)
    }
}
