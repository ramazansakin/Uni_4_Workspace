<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title>Yorum Gönderme</title>
    <link href="style/style.css" rel="stylesheet" />
</head>
<body>
    <!-- Üst Alan -->
<?php
	require_once "Header.php";
?>
    <div id="Content">
        <div class="Container clear">
            <!-- İçerik -->
            <!-- Sol Alan -->
            <div class="Left">
			
				<?php
					$Author=htmlspecialchars($_POST["txtAuthor"]);
					$Comment=htmlspecialchars($_POST["txtMessage"]);
					$ArticleId=htmlspecialchars($_POST["ArticleId"]);
					$kaydet = mysql_query("insert into comments (Author, Comment,ArticleId) values ('$Author', '$Comment', '$ArticleId')") or die("Hata: kayıt işlemi gerçekleşemedi.");
					if($kaydet)
					{
						echo 'yorumunuz başarılı bir şekilde gönderilmiştir.<br /><a href="/Detail.php?ArticleId='.$ArticleId.'">Makaleye geri gitmek için buraya tıklayınız</a>';
					}
				?>
            </div>
            <!-- Sağ Alan -->
            <div class="Right">
			<?php require_once "MostWatched.php"; ?>
			<?php require_once "LastComments.php"; ?>
            </div>
        </div>
    </div>
    <div id="Footer">
        <div class="Container">
            Powered By Sabri Meviş
        </div>
    </div>
</body>
</html> 