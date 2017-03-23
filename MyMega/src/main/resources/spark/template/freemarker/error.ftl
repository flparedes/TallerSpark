<#import "/layout/layout.ftl" as layout>
<@layout.myLayout>

<#if error??>
	<div class="label"></div>
	<div class="data error">${error}</div>
</#if>

</@layout.myLayout>