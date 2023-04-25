<#ftl encoding="utf-8">

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Site Pokemon</title>
    <link rel="stylesheet" type="text/css" media="all" href="/css/reset.css">
</head>

<body xmlns="http://www.w3.org/1999/html">

<a href="/users/${main_user.id}">
    ${main_user.id}
    ${main_user.pseudo}
</a>

<#if exchanges?size == 0>
    <h1>Aucun Echange Disponible</h1>
<#else>
    <h1>Liste d'Echanges</h1>

    <ul>
        <#list exchanges as exchange>
            <li>
                <a href="/echanges/${exchange.id}">Exchange id : ${exchange.id}</a>
                Pokedex id : ${exchange.pokemon.pokedexId}
                id : ${exchange.pokemon.id}
                name : ${exchange.pokemon.name}
                level : ${exchange.pokemon.level}
                sprite : <img src=${exchange.pokemon.sprite}>
            </li>
        </#list>
    </ul>
</#if>


</body>

</html>
