package com.yuk.common.stringtemplate.group

data class GroupId(
    val id: String
) {
    companion object {
        val NONE = GroupId("")
    }
}
