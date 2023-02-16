package org.nymph

import com.beust.klaxon.JsonArray
import com.beust.klaxon.Parser
import org.jsoup.Jsoup

object MDApi{
     fun getUserList(userName:String): Pair<List<String>, List<String>> {
        val doc = Jsoup.connect("https://api.musedash.moe/search/$userName")
            .ignoreContentType(true)
            .execute().body().toString()
        val jsonObj = Parser.default().parse(StringBuilder(doc)) as JsonArray<*>
        return Pair(
            List(jsonObj.size){ (jsonObj[it] as JsonArray<*>)[0] as String },
            List(jsonObj.size){ (jsonObj[it] as JsonArray<*>)[1] as String })
    }
}