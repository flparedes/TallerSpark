<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

    ${errors!""}
    <h2>Nombre: ${nombre}</h2><br>

    <h2>Texto: ${texto}</h2><br>
    
    <#if coches??>
    	<h2>Coches: ${coches}<br /></h2>
    <#else>
    	<h2>No ha seleccionado ning&uacute;n coche.<br /></h2>
	</#if>

    <a href="/hola">Volver</a>

</@layout.myLayout>