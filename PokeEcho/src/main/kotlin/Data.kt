package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

// 定义插件数据
object Data : AutoSavePluginData("Data") { // "name" 是保存的文件名 (不带后缀)

    @ValueDescription("被戳回复")
    val echoDataSet:MutableSet<String> by value(
        mutableSetOf(
            "指挥官，请不要做出这种行为",
            "这只是全息交互界面",
            "指挥官，请专心于工作",
            "全息投影是不会被接触到的",
            "指挥官，我一直陪着你哦",
            "可望不可及",
            "请不要试图干扰全息投影",
            "传输...信.号...数据...干扰..."
        )
    )
}