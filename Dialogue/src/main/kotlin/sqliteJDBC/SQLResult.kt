package sqliteJDBC

data class SQLResult<T>(val error: String?, val data: List<T>)