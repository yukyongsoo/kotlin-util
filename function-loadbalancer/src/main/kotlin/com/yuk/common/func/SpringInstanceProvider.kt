package com.yuk.common.func

import org.springframework.aop.framework.AopProxyUtils
import org.springframework.context.ApplicationContext

class SpringInstanceProvider(
    private val context: ApplicationContext,
    private val serviceClass: Class<out Any>,
) : InstanceProvider {
    override fun getInstance(): Any =
        getTargetBean()
            ?: throw IllegalArgumentException("No Spring bean found")

    private fun getTargetBean(): Any? {
        val className =
            serviceClass.simpleName
                .replaceFirstChar { it.lowercase() }
                .replace(Regex($$"\\$\\$SpringCGLIB\\$\\$\\d+"), "")

        if (context.containsBean(className)) {
            return AopProxyUtils.getSingletonTarget(context.getBean(className))
        }

        if (context.containsBean(serviceClass.name)) {
            return AopProxyUtils.getSingletonTarget(context.getBean(serviceClass.name))
        }

        throw IllegalArgumentException("${serviceClass.name} is not a Spring bean")
    }
}
