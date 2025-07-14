package org.springframework.security.kt.docs.servlet.test.testmethodwithsecuritycontext

import org.springframework.security.test.context.support.WithSecurityContext

// tag::snippet[]
@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory::class)
annotation class WithMockCustomUser(val username: String = "rob", val name: String = "Rob Winch")
// end::snippet[]
