<#import "layout/layout.ftl" as layout>
<@layout.myLayout>

    <div class="linea">${result!""}</div>
    
    <div class="label">Nombre</div>
    <div class="data">${nombre}</div>

    <div class="label">País</div>
    <div class="data">${pais}</div>

    <a href="/">Volver</a>

</@layout.myLayout>