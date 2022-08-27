package org.nymph

import okhttp3.OkHttpClient
import okhttp3.Request
import ws.schild.jave.Encoder
import ws.schild.jave.MultimediaObject
import ws.schild.jave.encode.AudioAttributes
import ws.schild.jave.encode.EncodingAttributes
import java.io.File
import java.net.SocketTimeoutException

object FFMPEG {
    private fun oog2mp3(source: ByteArray): RequestData {
        return runCatching {

            val cacheFile = File("cache").apply { writeBytes(source) }

            //音频属性
            val audio = AudioAttributes()
            audio.setCodec("libopencore_amrnb")
            audio.setChannels(1)
            audio.setSamplingRate(8000)

            //编码属性
            val attrs = EncodingAttributes().setAudioAttributes(audio).setOutputFormat("amr")

            //编码
            val target = File("input.amr")
            Encoder().encode(MultimediaObject(cacheFile), target, attrs)
            AudioData(target.readBytes())
        }.onFailure {
            return ErrData("转码异常\t${it.message}")
        }.getOrThrow()
    }


    fun getAudioData(url: String): RequestData {
        return runCatching {
            val client = OkHttpClient().newBuilder().build()
            val request = Request.Builder().url(url).addHeader("Content-Type", "audio/ogg").method("GET", null).build()
            val response = client.newCall(request).execute()
            val byteArray = response.body?.bytes() ?: return ErrData("音频获取失败")
            oog2mp3(byteArray)
        }.onFailure {
            when (it) {
                is SocketTimeoutException -> "远端服务器超时或无响应,请稍后再试"
                else -> "请求异常\t${it.message}"
            }.let(::ErrData)
        }.getOrThrow()
    }
}