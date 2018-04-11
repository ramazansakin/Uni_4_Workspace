<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <title></title>
    <link href="style/style.css" rel="stylesheet" />
	
	<script src="scripts/jquery.js"></script>
	<script src="scripts/jquery.validate.min.js"></script>
	<script src="scripts/detail.js"></script>
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
			
	if ($_POST){
		
					$Category=htmlspecialchars($_POST["Category"]);
					$Title=htmlspecialchars($_POST["Title"]);
					$Body=$_POST["Body"];
					$Image=htmlspecialchars($_POST["Image"]);
					$kaydet = mysql_query("insert into articles (Title, Body, UserId, CategoryId, ImagePath) values ('$Title', '$Body', 1,'$Category','$Image')") or die("Hata: kayıt işlemi gerçekleşemedi.");
					if($kaydet)
					{
						echo 'Kayıtlı başarıyla eklenmiştir.';
					}
	}
			?>
                    <form id="Commentform" method="post" action="AddArticle.php">
                        <table>
                            <tr>
                                <td class="formtd">Kategori</td>
                                <td>
								<select name="Category">
								
				<?php
				$sonuc = mysql_query("select * from categories");
				if(mysql_num_rows($sonuc)!=0)
				{
					while($oku = mysql_fetch_array($sonuc,MYSQL_BOTH))
					{
						echo '<option value="'.$oku['Id'].'">'.$oku['Name'].'</option>';
					}
				}
				?>
								</select>
								
								</td>
                            </tr>
                            <tr>
                                <td class="formtd">Başlık</td>
                                <td>
								<input type="text" class="txtAuthor" name="Title" />
								</td>
                            </tr>
                            <tr>
                                <td class="formtd">Açıklama</td>
                                <td>
								<textarea  class="txtMessage" name="Body"></textarea>
								</td>
                            </tr>
                            <tr>
                                <td class="formtd">Resim</td>
                                <td><input type="text" class="txtAuthor" name="Image" /></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="submit" value="Makale Ekle" /></td>
                            </tr>
                        </table>
                    </form>
		</div>
            <!-- Sağ Alan -->
        <div class="Right">
			<?php require_once "MostWatched.php"; ?>
			<?php require_once "LastComments.php"; ?>
        </div>
    </div>
    <div id="Footer">
        <div class="Container">
            Powered By Sabri Meviş
        </div>
    </div>
</body>
</html> 