<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

<a href="/users/${main_user.id}">
    ${main_user.id}
    ${main_user.pseudo}
</a>

<h1>Exchange</h1>

Exchange id : ${exchange.id}
Pokedex id : ${pokemon.pokedexId}
name : ${pokemon.name}
level : ${pokemon.level}
sprite : <img src=${pokemon.sprite}>

<h2>Pokemons voulus</h2>

<ul>
    <#list pokemonsWanted as pokemonWanted>
        <li>
            Pokedex id : ${pokemonWanted.pokedexId}
            <a href="/pokemon/${pokemonWanted.id}">name : ${pokemonWanted.name}</a>
            level : ${pokemonWanted.level}
            sprite : <img src=${pokemonWanted.sprite}>
            <#if main_user.id != pokemon.userId && pokemonWanted.id != -1>
                <form action="/echanges/${exchange.id}/accept/${pokemonWanted.id}" method="post">
                    <input type="submit" value="Echanger">
                </form>
            </#if>
        </li>
    </#list>
</ul>

<#if main_user.id == exchange.pokemon.userId>
    <div>
        Ajouter pokemon voulu :
        <form action="/echanges/${exchange.id}/add" method="post">
            <input type="text" name="pokemonName" id="pokemonName">
            <input type="submit" value="Ajouter">
        </form>
    </div>
    Annuler l'échange :
    <form action="/echanges/${exchange.id}/cancel" method="post">
        <input type="submit" value="Cancel">
    </form>
</#if>

</body>

</html>
