<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/form" method="POST">
    ${errors!""}
    <h2>Nombre</h2>
    <input type="text" name="nombre" size="120" value="${nombre!""}"><br>

    <h2>Texto</h2>
    <textarea name="texto" cols="120" rows="10">${texto!""}</textarea><br>
    
    <#if coches??>
    <h2>Coches</h2>
	<#list coches as coche>
	    <input type='checkbox' name='coches' value='${coche["id"]}'/> ${coche.nombre}<br />
	</#list>
	</#if>

    <input type="submit" value="Guardar">
</form>

</@layout.myLayout>