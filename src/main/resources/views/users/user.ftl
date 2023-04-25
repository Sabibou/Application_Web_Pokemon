<#ftl encoding="utf-8">

<html lang="fr">
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

<#if user_page.id == main_user.id>
    <h1>Votre Compte</h1>
<#else>
    <h1>Page utilisateur de ${user_page.pseudo}</h1>
</#if>


<h2>Autres Utilisateurs</h2>

<ul>
    <#list users as user>
        <li><a href="/users/${user.id}">${user.id} - ${user.pseudo} </a>
            ${user.lastConnection}
            ${user.nbPokemonXP}
            ${user.password}
        </li>
    </#list>
</ul>

<a href="/users">Accèder à la page des utilisateurs</a>

<#if user_page.id == main_user.id>
    <h2>Mes Pokemons</h2>
<#else>
    <h2>Ses Pokemons</h2>
</#if>

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
<#if user_page.id == main_user.id>
    <h2>Mes Echanges</h2>
<#else>
    <h2>Ses Echanges</h2>
</#if>

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
