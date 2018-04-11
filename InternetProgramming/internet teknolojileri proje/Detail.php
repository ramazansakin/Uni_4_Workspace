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
	$ArticleId=$_GET['ArticleId'];
?>
    <div id="Content">
        <div class="Container clear">
            <!-- İçerik -->
            <!-- Sol Alan -->
            <div class="Left">
			
				<?php
				mysql_query("UPDATE articles SET views=views+1 WHERE ArticleId=".$ArticleId);
				$sonuc = mysql_query("select a.*, c.name, u.Username from articles a left join users u on a.UserId = u.UserId left join categories c on a.CategoryId = c.id where a.ArticleId=".$ArticleId);
				if(mysql_num_rows($sonuc)!=0)
				{
					while($oku = mysql_fetch_array($sonuc,MYSQL_BOTH))
					{
						echo '<div class="Article">';
						echo '        <span class="h2">'.$oku['Title'].'</span>';
						echo '        <span class="Head">';
						echo '            <span class="Date">'.$oku['TranDate'].'</span>';
						echo '            <span class="Author">'.$oku['Username'].'</span>';
						echo '            <span class="Category">'.$oku['name'].'</span>';
						echo '            <span class="Views">'.$oku['Views'].'</span>';
						echo '        </span>';
						echo '        <span class="Content clear">';
						echo '            <img  onerror="this.onerror=null;this.src=\'images/default.png\';"  src="'.$oku['ImagePath'].'" alt="'.$oku['Title'].'" title="'.$oku['Title'].'" />';
						echo '            <span class="p">';
						echo '                '.$oku['Body'].'';
						echo '            </span>';
						echo '        </span>';
						echo '</div>';
					}
				}else{
					echo "Aradığınız kategoriye ait içerik bulunamadı!";
				}
				?>
                <!-- Yorumlar -->
                <div class="Comments">
                    <h2>Yorumlar</h2>
                    <div class="Content">
					
				<?php
				$sonuc = mysql_query('select * from comments where ArticleId='.$ArticleId);
				if(mysql_num_rows($sonuc)!=0)
				{
					while($oku = mysql_fetch_array($sonuc,MYSQL_BOTH))
					{
						
                        echo '<div class="Comment clear">';
                        echo '    <img src="images/testthumb.PNG" />';
                        echo '    <h3>'.$oku['Author'].'</h3>';
                        echo '    <p>'.$oku['Comment'].'</p>';
                        echo '</div>';
					}
				}else{
					echo "Henüz bu makeleye yorum yapılmamış";
				}
				?>
                    </div>
                </div>
                <div class="SubmitComment">
                    <form id="Commentform" method="post" action="SubmitComment.php">
                        <table>
                            <tr>
                                <td class="formtd">Adınız</td>
                                <td>
								<input type="text" class="txtAuthor" name="txtAuthor" />
								<input type="hidden" value="<?php echo $ArticleId;  ?>" name="ArticleId" />
								
								</td>
                            </tr>
                            <tr>
                                <td class="formtd">Mesajınız</td>
                                <td><textarea class="txtMessage" name="txtMessage"></textarea></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="submit" value="Yorum Yaz" /></td>
                            </tr>
                        </table>
                    </form>
                </div>
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