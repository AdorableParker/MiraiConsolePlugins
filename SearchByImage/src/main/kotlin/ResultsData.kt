import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsData(
    @SerialName("ext_urls")
    val extUrls: List<String> = listOf(),
    val creator: List<String> = listOf(),
    val source: String = "",
    @SerialName("eng_name")
    val engName: String = "",
    @SerialName("jp_name")
    val jpName: String = ""
)