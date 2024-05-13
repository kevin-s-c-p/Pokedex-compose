package mx.com.pokemonprueba.utils

sealed class DataNavigation {
    data object NavigateUp: DataNavigation()
    data class NavigateTo(val route: String): DataNavigation()
    data class NavigateToCleanStash(
        val route: String,
        val destinyStart: String = "",
        val inclusive: Boolean = true
    ): DataNavigation()
}