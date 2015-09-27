<?php

date_default_timezone_set('America/New_York');

require __DIR__ . '/autoload.php';
$app_id = 'L7fz5E8GBRwomEclplEO97Nkb9rIdOPsUtixJBCN';
$rest_key = 'Sb6rbJjyCmfb9wpMDje5Sj7XwCb2Oqdjre72py3t';
$master_key = 'mTYKk52pz38poLBCUZYf6so7QAfJPEaT8vqhdQ4D';

use Parse\ParseObject;
use Parse\ParseQuery;
use Parse\ParseACL;
use Parse\ParsePush;
use Parse\ParseUser;
use Parse\ParseInstallation;
use Parse\ParseException;
use Parse\ParseAnalytics;
use Parse\ParseFile;
use Parse\ParseCloud;
use Parse\ParseClient;

ParseClient::initialize( $app_id, $rest_key, $master_key );

session_start();
