<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

${main_user.id}
${main_user.pseudo}

<h2>Utilisateurs</h2>

<ul>
    <#list users as user>
        <li><a href="/${user.id}">${user.id} - ${user.pseudo} </a>
            ${user.lastConnection}
            ${user.nbPokemonXP}
            ${user.password}
        </li>
    </#list>
</ul>

<h2>Pokemons</h2>

<ul>
        <#list pokemons as pokemon>
                <li>user id : ${pokemon.userId}
                    id : ${pokemon.id}
                    pokedex id : ${pokemon.pokedexId}
                    <a href="/pokemon/${pokemon.id}">name : ${pokemon.name}</a>
                    level : ${pokemon.level}
                    sprite : <img src=${pokemon.sprite}>
                    <form action="/pokemon/${pokemon.id}/lvl_up" method="post">
                        <input type="submit" value="Lvl Up">
                    </form>
                </li>
        </#list>
</ul>

<h2>Echanges</h2>

<ul>
    <#list exchanges as exchange>
        <li><a href="/echanges/${exchange.id}">echange id : ${exchange.id}</a>
            pokedex_id : ${exchange.pokemon.pokedexId}
            sprite : <img src=${exchange.pokemon.sprite}>
        </li>
    </#list>
</ul>

<a href="/echanges/all">Voir tous les échanges</a>

<a href="/disconnect">Se déconnecter</a>
</body>

</html>
