<?php

require __DIR__ . '/defaults.php';

if (!empty($_POST)) {
 switch($_POST['action']) {
    case 'register':
        $query = new Parse\ParseQuery("Parent");
        $query->equalTo("username", $_POST['username']);
        $results = $query->find();
        if (count($results) > 0 ) {
         print "Username already taken!";
        } else {
         $newParent = new Parse\ParseObject("Parent");
         $newParent->set("username", $_POST['username']);
         // Save:
         $newParent->save();
        }
        header('Location: /login.php');
        break;
    case 'login':
        $query = new Parse\ParseQuery("Parent");
        $query->equalTo("username", $_POST['username']);
        $results = $query->find();
        if (count($results) > 0 ) {
         session_start();
         $_SESSION['username'] = $_POST['username'];
        } else {
         print "Invalid username!";
        }
        header('Location: /index.php');
        break;
    case 'logout':
        session_destroy();
        header('Location: /login.php');
        break;
    default:
        print 'error';
 }
}

//echo  $_SESSION['username'];

?>

<!DOCTYPE html>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>BIZYBEES</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/cover/cover.css" rel="stylesheet">
  </head>
  <body>

   <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix">
            <div class="inner">
              <h3 class="masthead-brand">BIZYBEES</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li><a href="index.php">Dashboard</a></li>
                  <li><a href="manage.php">Manage</a></li>
                  <li><a href="goals.php">Goals</a></li>
                  <li class="active"><a href="login.php">Login</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Welcome<?php if (isset($_SESSION['username'])) { echo ', ' . $_SESSION['username']; } echo '!'; ?></h1>




<br><br>

<form action="login.php" method="post">
  <input type="hidden" name="action" value="register">
  <b>Username:</b> <br><input type="text" name="username" style="color: black"><br>
  <b>Password:</b> <br><input type="password" name="password" style="color: black"><br>
  <button type="submit" class="btn btn-success">Register</button>
<!--  <input type="submit" value="Register"> -->
</form>

<br><br>
<form action="login.php" method="post">
  <input type="hidden" name="action" value="login">
  <b>Username:</b> <br><input type="text" name="username" style="color: black"><br>
  <b>Password:</b> <br><input type="password" name="password" style="color: black"><br>
  <button type="submit" class="btn btn-success">Login</button>
<!--  <input type="submit" value="Login"> -->
</form>

<br><br>

<form action="login.php" method="post">
  <input type="hidden" name="action" value="logout">
  <button type="submit" class="btn btn-success">Logout</button>
<!--  <input type="submit" value="Logout"> -->
</form>











          <div class="mastfoot">
            <div class="inner">
<!-- footer? -->
            </div>
          </div>

        </div>

      </div>

    </div>





    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  </body>
</html>

