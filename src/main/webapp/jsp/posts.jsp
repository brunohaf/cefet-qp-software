<html>
  <head>
    <title>Posts :: FHB</title>

    <style>
        .post-content {
            margin-top: 64px;
            padding: 0 32px;
        }
        .post {
            border: 1px solid black;
            padding: 10px;
            margin: 24px;
        }
    </style>
  </head>

  <body>
    <header>
        <h1>FHB Home</h1>
    </header>

    <div>
        <a href="javascript:history.back()">Home</a>
    </div>

    <div class="post-content">
        <h1>Posts</h1>
        <div class="post">
            <h2>${postList[0].title}</h2>
            <h3>${postList[0].content}</h3>
        </div>
    
        <div class="post">
            <h2>${postList[1].title}</h2>
            <h3>${postList[1].content}</h3>
        </div>
    </div>
  </body>
</html>
