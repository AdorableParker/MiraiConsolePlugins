import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Header(
    @SerialName("user_id")
    val uid: Int = 0,
    @SerialName("long_remaining")
    val remain: Int? = null,
    @SerialName("results_requested")
    val numberOfResults: Int = 0,
    val status: Int,
    val message: String = ""
)