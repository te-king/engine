package extensions


inline fun <T, reified R> Array<T>.mapArray(transform: (T) -> R) =
    Array(size) { transform(this[it]) }