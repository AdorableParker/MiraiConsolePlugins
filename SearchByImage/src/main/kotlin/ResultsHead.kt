import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsHead(
    @SerialName("index_id")
    val source: Int,
    val similarity: String,
) {
    fun source() = when (source) {
        0 -> "h-mags"
        1 -> "h-anime"
        2 -> "hcg"
        5 -> "pixiv"
        6 -> "pixiv历史记录"
        8 -> "seige"
        9 -> "danbooru"
        10 -> "drawr"
        11 -> "nijie"
        12 -> "yande.re"
        16 -> "FAKKU"
        18 -> "H-MISC安全"
        19 -> "2d_market"
        20 -> "medibang"
        21 -> "Anime"
        22 -> "H-Anime"
        23 -> "Movies"
        24 -> "Shows"
        25 -> "gelbooru"
        26 -> "konachan"
        27 -> "sankaku"
        28 -> "anime-pictures"
        29 -> "e621"
        30 -> "idol complex"
        31 -> "半次元插画版面"
        32 -> "半次元COS版面"
        33 -> "portalgraphics"
        34 -> "dA"
        35 -> "pawoo"
        36 -> "madokami"
        37 -> "mangadex"
        38 -> "H-Misc非安全"
        39 -> "ArtStation"
        40 -> "FurAffinity"
        41 -> "Twitter"
        42 -> "Furry Network"
        else -> "未知索引"
    }
}