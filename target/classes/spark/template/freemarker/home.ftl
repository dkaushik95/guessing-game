<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/styles/main.css">
</head>
<body>
    <h1>${title}</h1>
    
    <div class="body">
    
      <h2>Application Stats</h2>
      <p>
        ${gameStatsMessage}
      </p>
      <p>
        ${gameStatsAvgWins}
      </p>

      <h3> Local Stats </h3>
      <p>
        ${localStatsMessage}
      </p>
        <select id="difficulty" onchange="changeDifficulty()">
            <option value="0" selected>Standard level</option>
            <option value="1">Moderate level</option>
            <option value="2">Difficult level</option>
        </select>
      <#if newSession>
        <p>
          <a id="linkToGame" href="/game?difficulty=0">Want to play a game?!?</a>
        </p>
      <#else>
        <#if youWon>
          <p>
            Congratulations!  You must have read my mind.
            <br/><br/>
            <a id="linkToGame" href="/game?difficulty=1">Do it again</a>
          </p>
        <#else>
          <p>
            Aww, too bad.  Better luck next time.
            <br/><br/>
            <a id="linkToGame" href="/game?difficulty=2">How about it?</a>
          </p>
        </#if>
      </#if>
        <script>
//            document.getElementById("linkToGame").href = "/game?difficulty=" + document.getElementById("difficulty").value;
            function changeDifficulty(){
                document.getElementById("linkToGame").href = "/game?difficulty=" + document.getElementById("difficulty").value;
            }
        </script>
    </div>
  </div>
</body>
</html>
