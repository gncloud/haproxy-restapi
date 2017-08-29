<#--<#macro myMacro>foo</#macro>-->
<#--<#assign x>-->
    <#--<#list 1..3 as n>-->
    <#--${n} <@myMacro />-->
    <#--</#list>-->
<#--</#assign>-->
<#--Number of words: ${x?word_list?size}-->
<#--${x}-->
<#--${ggdata}-->

<#if test1?exists>
    ${test1}
</#if>
<#if test2?exists>
${test2}
</#if>
<#if test3?exists>
${test3}
</#if>
<#if test4?exists>
${test4}
</#if>