<!DOCTYPE html>


<html lang="fr">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PokExchange</title>
        <link rel="stylesheet" type="text/css" media="all" href="../../static/css/reset.css">
        <link rel="stylesheet" type="text/css" media="all" href="/css/base.css">

    </head>

	
	<body>

            <h1>Créer un compte</h1>

            <form id="form" method="post" action="/register">
                <div class="input">
                    <label for="username">User Name</label>
                    <input id="username" type="text" name="username">
                    <div class="error"></div>
                </div>
                <#if pseudoExists??>
                    Nom d'utilisateur existant
                </#if>
                <div class="input">
                    <label for="useremail">Email</label>
                    <input id="useremail" type="text" name="useremail">
                    <div class="error"></div>
                </div>
                <div class="input">
                    <label for="userpwd">Password</label>
                    <input id="userpwd" type="password" name="userpwd">
                    <div class="error"></div>
                </div>
                <div class="input">
                    <label for="firstname">First Name</label>
                    <input id="firstname" type="text" name="firstname">
                    <div class="error"></div>
                </div>
                <div class="input">
                    <label for="lastname">Last Name</label>
                    <input id="lastname" type="text" name="lastname">
                    <div class="error"></div>
                </div>

                <p><input id="go" type="submit" value="Register" ><input type="reset" value="Clear" ></p>
            </form>

            <footer class="footer">
                <h1>A propos</h1>
                <p>Nous sommes un site indépendant. Toute propriété intellectuelle appartient à Pokemon@Company.</p>
                <div class="container-footer">
                    <div class="column">
                        <h2>Infos</h2>
                        <ul>
                            <li><a href="http://web.com">Info1</a></li>
                            <li><a href="http://web.com">Info2</a></li>
                            <li><a href="http://web.com">Info3</a></li>
                        </ul>
                    </div>
                    <div class="column">
                        <h2>Aide</h2>
                        <ul>
                            <li><a href="http://web.com">Aide1</a></li>
                            <li><a href="http://web.com">Aide2</a></li>
                            <li><a href="http://web.com">Aide3</a></li>
                        </ul>
                    </div>
                </div>

                <p id="author">Site par Rodrigo Ferreira Rodrigues et M'hammed Salman Abibou</p>

            </footer>
	</body>

</html>

