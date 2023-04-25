<#ftl encoding="utf-8">

<body xmlns="http://www.w3.org/1999/html">
<a href="/users/${main_user.id}">
  ${main_user.id}
  ${main_user.pseudo}
</a>

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

<a href="/disconnect">Se déconnecter</a>
</body>

</html>