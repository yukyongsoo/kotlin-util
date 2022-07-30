package com.yuk.common.stringtemplate.group

data class GroupId(
    val value: String
) {
    companion object {
        val NONE = GroupId("")
    }
}
