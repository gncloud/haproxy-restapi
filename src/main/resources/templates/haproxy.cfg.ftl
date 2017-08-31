global
    deamon

defaults



<#list frontends?keys as k>
${k}
<#--frontend ${frontend.name}-->
    <#--bind ${frontend.bindIp}:${frontend.bindPort}-->
    <#--<#if frontend.mode>-->
        <#--mode ${frontend.mode}-->
    <#--</#if>-->
    <#--<#if frontend.defaultBackend>-->
        <#--defaultBackend ${frontend.defaultBackend}-->
    <#--</#if>-->
    <#--<#if frontend.timeoutConnect>-->
        <#--timeout connect ${frontend.timeoutConnect}-->
    <#--</#if>-->
    <#--<#if frontend.timeoutClient>-->
        <#--timeout client ${frontend.timeoutClient}-->
    <#--</#if>-->
    <#--<#if frontend.timeoutServer>-->
        <#--timeout server ${frontend.timeoutServer}-->
    <#--</#if>-->

    <#--<#if frontend.acls>-->
        <#--<#list frontend.acls as acl>-->
            <#--acl ${acl.name} ${acl.pattern}-->
            <#--use_backend ${acl.backend} if ${acl.name}-->
        <#--</#list>-->
    <#--</#if>-->
</#list>


<#--<#list backends as backend>-->
<#--backend ${backend.name}-->

<#--</#list>-->



