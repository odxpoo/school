<?php
// php nie otwarte od odpowiedniego znaku.
require_once("etc/conf-1.php");
require_once("etc/conf-2.php");
require_once("libs/lib-1.php");
require_once("libs/lib-2.php");
require_once("libs/lib-3.php");
require_once("libs/lib-4.php");
$f = new Form('Logowanie');
$l1 = new Label('Login');
$l2 = new Label('Hasło');
$i1 = new Input('user');
$i2 = new Input('pass');
$button = new Button('Wyślij');
$f->pack($l1, $i1, $l2, $i2, $button);
echo $f->display();
?>
