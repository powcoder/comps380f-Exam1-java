<!DOCTYPE html>
<html>
<head>
    <title>Guessing Game</title>
</head>
<body>
    <h1>Guessing Game</h1>
    
    <h2>Game No. ${gameNum}:</h2>
    <p>${result}</p>
    <form method="POST">
        Your guess (any integer from 1 to 50): <input type="text" name="guess" /><br />
        <input type="submit" value="Submit" />
    </form>
    <p><a href="<c:url value="/guess?restart" />">Start a new game</a></p>
    <p><a href="<c:url value="/history" />">History</a></p>
</body>
</html>
