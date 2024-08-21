package com.yuk.common.codesample.bitoperation

enum class BitOperation(
    private val bit: Int,
) {
    A(0b000001),
    B(0b000010),
    C(0b000100),
    ;

    fun has(bit: Int): Boolean = bit and this.bit != 0

    companion object {
        fun allOperation(flag: Int): List<BitOperation> {
            val list = mutableListOf<BitOperation>()

            for (bit in values()) {
                if (bit.has(flag)) {
                    list.add(bit)
                }
            }

            return list
        }
    }
}
