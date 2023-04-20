<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

${main_user.id}
${main_user.pseudo}

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
