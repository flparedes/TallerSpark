<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<form action="/car" method="POST">
    ${result!""}
    <h2>Nombre</h2>
    <input type="text" name="nombre" size="120" value="${nombre!""}"><br>

    <input type="submit" value="Guardar">
    <a href="/hola">Volver</a>
</form>

</@layout.myLayout>