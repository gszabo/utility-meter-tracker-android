package com.example.utilitymetertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.utilitymetertracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        TableDisplay(
            binding.grid,
            arrayOf("Date", "Gas [m3]", "Electricity [kWh]"),
            Data.measurements,
            { m: Measurement -> arrayOf(m.date, m.gas, m.electricity) }
        )
    }
}

class TableDisplay<T>(
    el: RecyclerView,
    private val headers: Array<String>,
    private val data: Array<T>,
    private val dataMapper: (T) -> Array<String>
) {
    private val columnCount = headers.size

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    inner class TableAdapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_text_view, parent, false) as TextView
            return ViewHolder(textView)
        }

        override fun getItemCount(): Int = (data.size + 1) * columnCount

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val row = position / columnCount
            val col = position % columnCount

            val asString = if (row == 0) headers[col] else dataMapper(data[row - 1])[col]
            holder.textView.text = asString
        }
    }

    init {
        val viewManager = GridLayoutManager(el.context, columnCount)
        val adapter = TableAdapter()
        el.layoutManager = viewManager
        el.adapter = adapter
    }
}

data class Measurement(val date: String, val electricity: String, val gas: String)

object Data {
    val measurements = arrayOf(
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A"),
        Measurement("2020-02-06", "3248", "8645.152"),
        Measurement("2020-02-13", "3290", "8710.023"),
        Measurement("2020-02-20", "N/A", "N/A")
    )
}