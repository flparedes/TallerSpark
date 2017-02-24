<#import "layout/layout.ftl" as layout>
<@layout.myLayout>
    <h2>Nombre: ${nombre}</h2><br>
    <h2>Texto: ${texto}</h2><br>
    
    <#if cochesSeleccionados??>
    	<h2>Coches: ${cochesSeleccionados}<br /></h2>
    <#else>
    	<h2>No ha seleccionado ning&uacute;n coche.<br /></h2>
	</#if>

    <a href="/hola">Volver</a>

</@layout.myLayout>