<?php
	
	## Hataları Gizle ##
	error_reporting(0);
	
	## Bağlantı Değişkenleri ##
	$host 	= "localhost";
	$user 	= "root";
	$pass 	= "";
	$db		= "ingilizcece";
	
	## Mysql Bağlantısı ##
	$baglan = mysql_connect($host, $user, $pass) or die (mysql_Error());
	
	## Veritabanı Seçimi ##
	mysql_select_db($db, $baglan) or die (mysql_Error());
	
	## Karakter Sorunu ##
	mysql_query("SET CHARACTER SET 'utf8'");
	mysql_query("SET NAMES 'utf8'");
	
	## Genel Ayarları ##
	##$query = mysql_query("SELECT * FROM ayarlar");
	##$ayar = mysql_fetch_array($query);
	

?>