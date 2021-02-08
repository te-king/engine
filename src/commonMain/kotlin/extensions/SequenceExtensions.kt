package extensions


fun <T> Sequence<T>.pairedPermutations(): Sequence<Pair<T, T>> =
    sequence {
        forEachIndexed { index, t ->
            yieldAll(drop(index + 1).map { t to it })
        }
    }