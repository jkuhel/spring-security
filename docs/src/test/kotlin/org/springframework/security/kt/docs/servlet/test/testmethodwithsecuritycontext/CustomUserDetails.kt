package org.springframework.security.kt.docs.servlet.test.testmethodwithsecuritycontext

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    name: String,
    username: String,
    authorities: MutableCollection<GrantedAuthority> = AuthorityUtils.createAuthorityList("ROLE_USER")) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun getPassword(): String {
        TODO("Not yet implemented")
    }

    override fun getUsername(): String {
        return username
    }
}
