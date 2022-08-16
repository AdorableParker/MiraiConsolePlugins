import kotlinx.serialization.Serializable

@Serializable
data class DocData(val results: List<Results> = listOf(), val header : Header)