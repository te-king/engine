package graphics


class IndexBufferObject(val buffer: Buffer<IndexKind, *>, val indexCount: Int, val primitiveType: PrimitiveType)