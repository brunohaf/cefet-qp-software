<html>
  <head>
    <title>Login :: FHB</title>

    <style>
      body {
        background-color: #efefef;
        height: 100vh;
        width: 100%;
        flex: 1;
      }

      .login-container {
        flex: 1;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
      }

      .submit-button {
        width: 100%;
        margin-top: 40px;
        border: none;
        background-color: #2980b9;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
      }

      .register-button {
        display: flex;
        justify-content: end;
      }
    </style>
  </head>

  <body>
    <div class="login-container">
      <form method="post">
        <div>Email : <input type="text" name="email" /></div>

        <div>Password : <input type="password" name="password" /></div>
        <div>
          <font color="red">${errorMessage}</font>
        </div>
        <div>
          <input class="submit-button" type="submit" value="Login" />
        </div>
        <div class="register-button">
           <a href="/users/register">Register</a>
        </div>
      </form>
    </div>
  </body>
</html>
