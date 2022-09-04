package org.nymph

object AI {
    fun dialogue(group: Long, content: String): String? {
        // 模板查询
        if (3 >= (1..10).random()) {
            val sheet = DialogueData.QASheet.getOrPut(group) { mutableSetOf() }
            for (qaPair in sheet) {
                if (qaPair.question.toRegex().containsMatchIn(content)) {
                    return qaPair.question.toRegex().replace(content, qaPair.answer)
                }
            }
        }
        // 查询
        val result =
            Dialogue.SQLiteLink.executeDQLorDCL<CorpusData> { "SELECT * FROM Corpus WHERE question = '$content' AND(fromGroup = $group OR fromGroup = 0 AND);" }
        return when {
            result.error != null -> result.error!!
            result.data.isEmpty() -> null
            else -> result.data.random().answer
        }
    }
}