<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">

${main_user.id}
${main_user.pseudo}

<ul>
    <#list users as user>
        <li>${user.id} - ${user.pseudo} </li>
    </#list>
</ul>

<ul>
        <#list pokemons as pokemon>
                <li>${pokemon.name}</li>
        </#list>
</ul>

</body>

</html>
