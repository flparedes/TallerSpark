<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

<h1>${saludo}</h1>
<h1>Este texto se ha generado usando Freemarker</h1>
<br/>
<h1><a href="/form">Ir al formulario de pruebas</a></h1>
<p>${texto}</p>

</@layout.myLayout>