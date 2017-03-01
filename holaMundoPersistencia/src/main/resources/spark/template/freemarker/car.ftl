<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/car" method="POST">
    ${result!""}
    <h2>Nombre</h2>
    <input type="text" name="nombre" size="120" value="${nombre!""}"><br>
    
    <h2>Fabricante</h2>
    <select id = "fabricante" name = "fabricante">
	    <#list fabricantes as fabricante>
	        <option value="${fabricante.id}">${fabricante.name}</option>
	    </#list>
	</select>

    <input type="submit" value="Guardar">
    <a href="/hola">Volver</a>
</form>

</@layout.myLayout>