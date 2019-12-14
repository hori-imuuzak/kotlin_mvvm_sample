package jp.imuuzak.kotin_mvvm_sample.view.adapter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DateJsonAdapter: JsonAdapter<Date>() {
    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private val sdFormat = SimpleDateFormat(dateFormat, Locale.JAPAN)

    @Synchronized
    @Throws(Exception::class)
    override fun fromJson(reader: JsonReader): Date? {
        val str = reader.nextString()
        return sdFormat.parse(str)
    }

    @Synchronized
    @Throws(Exception::class)
    override fun toJson(writer: JsonWriter, value: Date?) {
        writer.value(sdFormat.format(value))
    }
}