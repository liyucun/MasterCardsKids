<?php

require __DIR__ . '/defaults.php';

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
    <title>Bootstrap 101 Template</title>

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
                  <li class="active"><a href="index.php">Dashboard</a></li>
                  <li><a href="manage.php">Manage</a></li>
                  <li><a href="goals.php">Goals</a></li>
                  <li><a href="login.php">Login</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">
            <h1 class="cover-heading">Welcome<?php if (isset($_SESSION['username'])) { echo ', ' . $_SESSION['username']; } echo '!'; ?></h1>

<?php 

        $query = new Parse\ParseQuery("Parent");
        $query->equalTo("username", $_SESSION['username']);
        $results = $query->find();
        if (count($results) > 0 ) {
         $object = $results[0];
         $cards = $object->get("cards");
         if (!is_null($cards)) {
          foreach ($cards as $key => $value) {
           $query = new Parse\ParseQuery("Profile");
           $query->includeKey("user");
           $query->equalTo("card", $value);
           $results = $query->find();
           if (count($results) > 0 ) {
            $object = $results[0];
            $educash = $object->get("educash");
            $user = $object->get("user");
            $username = $user->get("username");
            $relation = $object->getRelation("goals");
            $goals = $relation->getQuery()->find();
            for ($i = 0; $i < count($goals); $i++) {
             $goal = $goals[$i];
             $amount = $amount + $goal->get('amount');
            }
echo '<br><br>';
echo             '<p class="lead">Overall progress for: ' . $username . '</p>';
echo             '<div class="progress">';
echo              '<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="' . round(($educash / $amount) * 100) . '" aria-valuemin="0" aria-valuemax="100" style="width: ' . round(($educash / $amount) * 100) . '%">';
echo               '<span class="sr-only">' . round(($educash / $amount) * 100) . '% Complete</span>';
echo              '</div>';
echo            '</div>';
           }
          }
         }
        }


?>


<br><br>
            <p class="lead"><?php if (isset($_SESSION['username'])) { echo '<a href="goals.php" class="btn btn-success">See All Goals</a>'; } ?>
            </p>
          </div>

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
