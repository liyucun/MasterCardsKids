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
    <title>BIZYBEES</title>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- Custom styles for this template -->
    <link href="http://getbootstrap.com/examples/carousel/carousel.css" rel="stylesheet">
    <link href="http://getbootstrap.com/examples/cover/cover.css" rel="stylesheet">
  </head>
  <body>


   <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="masthead clearfix" style="position: relative">
            <div class="inner">
              <h3 class="masthead-brand">BIZYBEES</h3>
              <nav>
                <ul class="nav masthead-nav">
                  <li><a href="index.php">Dashboard</a></li>
                  <li><a href="manage.php">Manage</a></li>
                  <li class="active"><a href="goals.php">Goals</a></li>
                  <li><a href="login.php">Login</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <div class="inner cover">




<br><br>
<br><br>




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

echo '      <div class="row" style="text-align: center">';
            echo "<h1>" . $username . "'s EduCash Balance: " . $educash . "<br>";
//            echo "<h1>" . $username . "'s Goals<br>";
echo '</div>';


echo '      <div class="row" style="text-align: center">';
echo '        <div class="col-lg-4 col-lg-offset-4">';
            for ($i = 0; $i < count($goals); $i++) {
             $goal = $goals[$i];
             $name = $goal->get('name');
             $amount = $goal->get('amount');
echo '<br><img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">';
//             echo "<h2>" . $name . "</h2>";
             echo "<br><br>Goal: " . $name . "<br>";
             echo "Amount: " . $amount . "<br>";
?>
            <div class="progress">
             <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="<?php echo round(($educash / $amount) * 100); ?>" aria-valuemin="0" aria-valuemax="100" style="width: <?php echo round(($educash / $amount) * 100); ?>%">
              <span class="sr-only"><?php echo round(($educash / $amount) * 100); ?>% Complete</span>
             </div>
            </div>

<?php if (false !== stripos($name, 'bizybees')) {

echo '<script type="text/javascript" src="https://www.simplify.com/commerce/simplify.pay.js"></script>' . 
     '<button data-sc-key="sbpb_YzY0YWIwZWEtMmI1ZC00MjRiLWE1YTgtZjNhY2FmNGY5YTdj"' . 
     '   data-name="BIZYBEES"' .
     '   data-description="' . $name . '"' .
     '   data-reference="99999"' .
     '   data-amount="' . $amount * 100 . '"' .
     '   data-color="#12B830"' .
     '   data-masterpass="true">' .
     '   Purchase' .
     '</button>' ;

             echo "<br><br>";

}

?>

<?php
//             echo "<br><br>";
            }
echo '</div>';
echo '</div>';
           }
          }
         }
        }
?>
