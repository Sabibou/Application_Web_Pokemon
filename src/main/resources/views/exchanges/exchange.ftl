<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

${main_user.id}
${main_user.pseudo}

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
        </li>
    </#list>
</ul>

</body>

</html>
