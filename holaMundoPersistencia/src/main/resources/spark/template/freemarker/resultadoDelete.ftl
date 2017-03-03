<#import "layout/layout.ftl" as layout>
<@layout.myLayout>
	<#if error??>
    	<div class="linea error">${error}</div><br/>
	</#if>
    <div class="linea">${result!""}</div>

    <div class="label"><a href="/">Volver</a></div>

</@layout.myLayout>