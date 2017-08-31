<#--<#compress>-->

#haproxy.cfg
#========================================================================================================================

global

    daemon
    stats socket /var/lib/haproxy/stats
<#if global?exists>
    <#list global?keys as gkey>
        <#if gkey != "etc">
            <#if global[gkey]?exists>
    ${gkey} ${global[gkey]}
            </#if>
        <#else>
            <#if global["etc"]?exists>
                <#list global["etc"] as getc>
    ${getc}
                </#list>
            </#if>
        </#if>
    </#list>
</#if>


<#if defaults?exists>
defaults

    <#list defaults?keys as dkey>
    <#assign use="Y">
        <#if dkey != "etc" && defaults[dkey]?exists>
            <#if dkey = "timeoutConnect">
    timeout connect ${defaults[dkey]}
                <#assign use="N">
            </#if>
            <#if dkey = "timeoutClient">
    timeout client ${defaults[dkey]}
                <#assign use="N">
            </#if>
            <#if dkey = "timeoutServer">
    timeout server ${defaults[dkey]}
                <#assign use="N">
            </#if>
        <#if use = "Y">
    ${dkey} ${defaults[dkey]}
        </#if>
        <#else>
            <#if defaults["etc"]?exists>
                <#list defaults["etc"] as detc>
    ${detc}
                </#list>
            </#if>
        </#if>
    </#list>
</#if>

------------------------------------------------------------
#frontend web80
#       bind *:80
#       acl acl1 hdr_beg(host) www.
#       acl acl2 hdr_beg(host) blog.

#       use_backend back2 if acl1
#       use_backend back2 if acl2

#backend back1
#       server name1 127.0.0.1:9999 check
------------------------------------------------------------


<#list frontends?keys as fkey>
frontend ${fkey}
<#if frontends[fkey]?exists>
    <#list frontends[fkey]?keys as finKey>
    ${finKey}
    </#list>
</#if>



</#list>

#========================================================================================================================
<#--</#compress>-->