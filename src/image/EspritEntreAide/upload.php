<?php
  $filename=uniqid("_img",".png");
  $fileData=file_get_contents('php://input');
  $fhandle=fopen("images/".$filename, 'wb');
  fwrite($fhandle, $fileData);
  fclose($fhandle);
  echo($filename);
?>