package org.nymph

object AI {
    fun dialogue(group: Long, content: String): String? {
        // 模板查询
        if (3 >= (1..10).random()) {
            DialogueData.groupConfiguration.getOrPut(group) {
                GroupSet(33, mutableSetOf(), mutableSetOf(), mutableSetOf(), mutableSetOf())
            }.qaSheet.forEach { qaPair ->
                if (qaPair.question.toRegex().containsMatchIn(content)) {
                    return qaPair.question.toRegex().replace(content, qaPair.answer)
                }
            }
        }
        // 查询
//        val result = Dialogue.SQLiteLink.executeDQLorDCL<CorpusData> { """SELECT * FROM Corpus WHERE question = "$content" AND(fromGroup = $group OR fromGroup = 0);""" }
        val result = Dialogue.SQLiteLink.safeExecuteDQLorDCL<CorpusData>(
            "SELECT * FROM Corpus WHERE question = ? AND (fromGroup = ? OR fromGroup = 0);",
            content,
            group
        )

        return when {
            result.error != null -> result.error
            result.data.isEmpty() -> null
            else -> result.data.random().answer
        }
    }
}