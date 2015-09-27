<?php

require __DIR__ . '/defaults.php';

if (!empty($_POST)) {
 switch($_POST['action']) {
    case 'linkcard': 
        $query = new Parse\ParseQuery("Parent");
        $query->equalTo("username", $_SESSION['username']);
        $results = $query->find();
        if (count($results) > 0 ) {
         $object = $results[0];
         $cards = $object->get("cards");
         $updatedCards = array();
         if (!is_null($cards)) {
          foreach ($cards as $key => $value) {
           array_push($updatedCards, $value);
          }
         }
         if (!in_array($_POST['cardnumber'], $updatedCards)) {
          array_push($updatedCards, $_POST['cardnumber']);
         }
         $object->setArray("cards", $updatedCards);
        }
        // Save:
        $user = new Parse\ParseUser();
        $user->setUsername($_POST['childusername']);
        $user->setPassword($_POST['childusername']);
        try {
            $user->signUp();
            $object->save();
        } catch (ParseException $ex) {
            // error in $ex->getMessage();
        }

        $query = new Parse\ParseQuery("Profile");
        $query->includeKey("user");
        $query->equalTo("username", $_POST['childusername']);
        $results = $query->find();
        if (count($results) == 0 ) {
         $object = Parse\ParseObject::create("Profile");
         $object->set("user", $user);
         $object->set("card", $_POST['cardnumber']);
         // Save:
         $object->save();
        }


//        $relation = $parent->getRelation("children");
//        $relation->add($newChild);
//        $parent->save();
        break;
    case 'addgoal':
        $goal = Parse\ParseObject::create("Goal");
        $goal->set("name", $_POST['name']);
        $goal->set("amount", intval($_POST['amount']));
        // Save:
        $goal->save();

        $profile = new Parse\ParseQuery("Profile");
        $profile->equalTo("card", $_POST['childcard']);
        $results = $profile->find();
        $profile = $results[0];
        $relation = $profile->getRelation("goals");
        $relation->add($goal);
        // Save:
        $profile->save();

        break;
    default:
        print 'error';
 }
}

//echo $_SESSION['username'];

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
                  <li class="active"><a href="manage.php">Manage</a></li>
                  <li><a href="goals.php">Goals</a></li>
                  <li><a href="login.php">Login</a></li>
                </ul>
              </nav>
            </div>
          </div>


          <div class="inner cover">
<br><br>

<?php if (!isset($_SESSION['username'])) { die(); } ?>

            <h1 class="cover-heading">Manage your children and their cards
</h1>



<br><br>


<div class="list-group">
  <a href="#" class="list-group-item active">
    <h4 class="list-group-item-heading">Linking Cards</h4>
    <p class="list-group-item-text">1. Create your child a unique username<br>2. Assign them their corresponding card number</p>
  </a>
</div>

<form action="manage.php" method="post">
  <input type="hidden" name="action" value="linkcard">
  <b>Child Username:</b> <br><input type="text" name="childusername" style="color: black"><br>
  <b>Card Number:</b> <br><input type="text" name="cardnumber" style="color: black"><br>
  <button type="submit" class="btn btn-success">Link</button>
</form>


<br><br><br>


<div class="list-group">
  <a href="#" class="list-group-item active">
    <h4 class="list-group-item-heading">Setting Goals</h4>
    <p class="list-group-item-text">1. Select your child / card<br>2. Enter a name for this goal<br>3. Assign this goal an amount in EduCash</p>
  </a>
</div>

<form action="manage.php" method="post">
  <input type="hidden" name="action" value="addgoal">
  <label for="childcard"><b>Child / Card</b></label>
  <select name="childcard" id="childcard">
  <option value=""></option>;
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
            $user = $object->get("user");
            $username = $user->get("username");
            echo '<option value="' . $value . '">' . $username . ' / ' . $value . '</option>';
           }
          }
         }
        }        

?>

  </select><br>
  <b>Name:</b> <br><input type="text" name="name" style="color: black"><br>
  <b>Amount:<b> <br><input type="number" name="amount" style="color: black"><br>
  <button type="submit" class="btn btn-success">Set Goal</button>
</form>

