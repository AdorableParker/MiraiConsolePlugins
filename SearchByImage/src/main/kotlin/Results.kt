import kotlinx.serialization.Serializable

@Serializable
data class Results(
    val data: ResultsData,
    val header: ResultsHead
)