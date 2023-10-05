package com.bharatintern.temperatureconverter;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextTemperature;
    Spinner spinnerFromUnit;
    Button buttonConvert;
    TextView textViewCelsius, textViewFahrenheit, textViewKelvin, textViewRankine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTemperature = findViewById(R.id.editTextTemperature);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewCelsius = findViewById(R.id.textViewCelsius);
        textViewFahrenheit = findViewById(R.id.textViewFahrenheit);
        textViewKelvin = findViewById(R.id.textViewKelvin);
        textViewRankine = findViewById(R.id.textViewRankine);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.temperature_units,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromUnit.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temperatureInputStr = editTextTemperature.getText().toString();
                if (temperatureInputStr.isEmpty()) {
                    editTextTemperature.setError("Please enter a temperature.");
                    return;
                }

                double temperatureInput = Double.parseDouble(temperatureInputStr);
                String selectedFromUnit = spinnerFromUnit.getSelectedItem().toString();

                // Perform conversions
                double celsius, fahrenheit, kelvin, rankine;

                switch (selectedFromUnit) {
                    case "Celsius":
                        celsius = temperatureInput;
                        fahrenheit = celsiusToFahrenheit(celsius);
                        kelvin = celsiusToKelvin(celsius);
                        rankine = fahrenheitToRankine(fahrenheit);
                        break;
                    case "Fahrenheit":
                        fahrenheit = temperatureInput;
                        celsius = fahrenheitToCelsius(fahrenheit);
                        kelvin = celsiusToKelvin(celsius);
                        rankine = fahrenheitToRankine(fahrenheit);
                        break;
                    case "Kelvin":
                        kelvin = temperatureInput;
                        celsius = kelvinToCelsius(kelvin);
                        fahrenheit = celsiusToFahrenheit(celsius);
                        rankine = fahrenheitToRankine(fahrenheit);
                        break;
                    case "Rankine":
                        rankine = temperatureInput;
                        fahrenheit = rankineToCelsius(rankine);
                        celsius = fahrenheitToCelsius(fahrenheit);
                        kelvin = celsiusToKelvin(celsius);
                        break;
                    default:
                        celsius = fahrenheit = kelvin = rankine = 0.0;
                        break;
                }

                // Display the results
                String resultText = "Celsius: " + celsius + "\n" +
                        "Fahrenheit: " + fahrenheit + "\n" +
                        "Kelvin: " + kelvin + "\n" +
                        "Rankine: " + rankine;

                textViewCelsius.setText("Celsius: " + celsius);
                textViewFahrenheit.setText("Fahrenheit: " + fahrenheit);
                textViewKelvin.setText("Kelvin: " + kelvin);
                textViewRankine.setText("Rankine: " + rankine);
            }
        });
    }

    // Conversion functions
    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double fahrenheitToRankine(double fahrenheit) {
        return fahrenheit + 459.67;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double rankineToCelsius(double rankine) {
        return (rankine - 491.67) * 5 / 9;
    }
}
