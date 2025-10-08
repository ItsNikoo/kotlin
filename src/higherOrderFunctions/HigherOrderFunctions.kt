package higherOrderFunctions

data class Query(val type: Int, val message: String) {
    companion object {
        const val ERROR = 0
        const val WARNING = 1
    }
}

class QueryHandler {

    fun handle(
        queriesList: List<Query>,
        onError: (String) -> Unit,
        onWarning: (String) -> Unit,
        onOther: (Int, String) -> Unit
    ) {
        for (query in queriesList) {
            when (query.type) {
                Query.ERROR -> onError(query.message)
                Query.WARNING -> onWarning(query.message)
                else -> onOther(query.type, query.message)
            }
        }
    }
}